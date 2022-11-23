package com.sznhl.agricultural.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sznhl.agricultural.dao.yiwu.*;
import com.sznhl.agricultural.entity.*;
import com.sznhl.agricultural.handler.BusinessException;
import com.sznhl.agricultural.vo.PaySyncVo;
import com.sznhl.agricultural.vo.PayVo;
import com.sznhl.agricultural.vo.ToolOrderVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ToolOrderService {

    @Autowired
    private ToolOrderDao toolOrderDao;

    @Autowired
    private LoginDao loginDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private EnterpriseDao enterpriseDao;

    @Autowired
    private LinkOrderInfoDao linkOrderInfoDao;

    @Autowired
    private ToolDao toolDao;

    @Autowired
    private ShoppingCarDao shoppingCarDao;

    @Autowired
    private ResOrderCarDao resOrderCarDao;

    @Autowired
    private ToolRecordDao toolRecordDao;

    @Autowired
    private ResToolCodeDao resToolCodeDao;

    public Map<String, Object> listAllOrder(String userId, String apiKey, Integer pageNum,
                                            Integer pageSize, String enterpriseName,
                                            String name, String orderNumber,
                                            String startTime, String endTime, Integer enterpriseId, String toolName) {
        if (StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime)) {
            throw new BusinessException("查询时间不能为空");
        }
        List<Integer> enterpriseIdList = loginDao.selectByUserIdAndApiKey(userId, apiKey);
        if (enterpriseIdList.size() == 0) {
            throw new BusinessException("账号异常");
        }
        List<ToolOrderVo> list = toolOrderDao.select(enterpriseName, name, orderNumber, startTime, endTime, enterpriseId, toolName);
        double money = list.parallelStream().mapToDouble(i -> StringUtils.isNotBlank(i.getPrice()) ? Double.parseDouble(i.getPrice()) : 0).sum();
        PageHelper.startPage(pageNum, pageSize);
        list = toolOrderDao.select(enterpriseName, name, orderNumber, startTime, endTime, enterpriseId, toolName);
        PageInfo<ToolOrderVo> pageInfo = new PageInfo<>(list);
        Map<String, Object> map = new HashMap<>();
        map.put("money", money);
        map.put("list", pageInfo.getList());
        map.put("count", pageInfo.getTotal());
        return map;
    }

    @Transactional(rollbackFor = Exception.class)
    public int pay(PayVo payVo) {
        Integer enterpriseId = payVo.getEnterpriseId();
        if (enterpriseId == null) {
            throw new BusinessException("enterpriseId不能为空");
        }
        if (StringUtils.isBlank(payVo.getOrderNumber())) {
            throw new BusinessException("订单编号不能为空");
        }
        if (StringUtils.isBlank(payVo.getToolIds())) {
            throw new BusinessException("toolIds不能为空");
        }
        if (StringUtils.isBlank(payVo.getAccountNums())) {
            throw new BusinessException("accountNums不能为空");
        }
        if (StringUtils.isBlank(payVo.getPrices())) {
            throw new BusinessException("prices不能为空");
        }
        if (StringUtils.isBlank(payVo.getFinalRatioFees())) {
            throw new BusinessException("finalRatioFees不能为空");
        }

        TbUser user = userDao.selectByUserIdAndApiKey(payVo.getUserId(), payVo.getApiKey());
        if (user == null) {
            throw new BusinessException("账号异常");
        }
        TbRole role = roleDao.selectByUserId(user.getId());
        TbEnterprise enterprise = enterpriseDao.selectByUserId(user.getId());
        if (role != null && role.getId() == 5) {
            if (enterprise != null && enterprise.getState() == 0) {
                throw new BusinessException("此账号已停用");
            }
        }

        TbToolOrder toolOrder = toolOrderDao.selectByOrderNumber(payVo.getOrderNumber());
        if (toolOrder != null) {
            throw new BusinessException("订单编号已存在");
        }

        TbLinkOrderInfo linkOrderInfo = null;
        if (StringUtils.isNotEmpty(payVo.getIdcard())) {
            linkOrderInfo = linkOrderInfoDao.selectByIdcard(payVo.getIdcard());
        }

        //保存订单信息
        toolOrder = new TbToolOrder();
        toolOrder.setToolEnterpriseId(enterpriseId);
        toolOrder.setPlantEnterpriseId(linkOrderInfo == null ? null : linkOrderInfo.getId());
        toolOrder.setAddPeople(user.getName());
        toolOrder.setOrderNumber(payVo.getOrderNumber());
        toolOrder.setPrice(payVo.getPrice());
        toolOrder.setPic(payVo.getPic());
        toolOrder.setOrderType(payVo.getOrderType() == null ? 0 : payVo.getOrderType());
        int res = toolOrderDao.insert(toolOrder);
        if (res == 0) {
            throw new BusinessException("新增订单失败");
        }

        //保存订单商品信息
        String[] toolIdArray = payVo.getToolIds().split(",");
        String[] accountNumArray = payVo.getAccountNums().split(",");
        String[] priceArray = payVo.getPrices().split(",");
        String[] finalRatioFeeArray = payVo.getFinalRatioFees().split(",");

        for (int i = 0; i < toolIdArray.length; i++) {
            Integer toolId = Integer.valueOf(toolIdArray[i]);
            TbTool tool = toolDao.selectById(toolId);
            if (tool == null) {
                throw new BusinessException("toolId:" + toolIdArray[i] + "错误");
            }

            TbShoppingCar car = new TbShoppingCar();
            car.setEnterpriseId(enterpriseId);
            car.setToolId(toolId);
            car.setNum(accountNumArray[i]);
            car.setUniformPrice(tool.getUniformPrice());
            String originalPriceStr = StringUtils.isEmpty(priceArray[i]) ? "0" : priceArray[i];
            car.setOriginalPrice(originalPriceStr);

            String priceStr = StringUtils.isEmpty(finalRatioFeeArray[i]) ? "0" : finalRatioFeeArray[i];
            String numStr = StringUtils.isEmpty(accountNumArray[i]) ? "0" : accountNumArray[i];
            BigDecimal price = new BigDecimal(priceStr);
            BigDecimal num = new BigDecimal(numStr);
            car.setPrice(price.divide(num, 2, BigDecimal.ROUND_HALF_UP).toString());
            res = shoppingCarDao.insert(car);
            if (res == 0) {
                throw new BusinessException("新增订单商品失败");
            }

            TbResOrderCar resOrderCar = new TbResOrderCar();
            resOrderCar.setCarId(car.getId());
            resOrderCar.setOrderId(toolOrder.getId());
            res = resOrderCarDao.insert(resOrderCar);
            if (res == 0) {
                throw new BusinessException("关联失败");
            }

            BigDecimal oldNumber = BigDecimal.ZERO;
            if (tool.getNumber() != null) {
                oldNumber = new BigDecimal(tool.getNumber());
            }
            oldNumber = oldNumber.subtract(new BigDecimal(car.getNum())).setScale(2, BigDecimal.ROUND_HALF_UP);
            res = toolDao.updateNumber(toolId, oldNumber.toString());
            if (res == 0) {
                throw new BusinessException("修改库存失败");
            }

            TbToolRecord toolRecord = new TbToolRecord();
            toolRecord.setEnterpriseId(enterpriseId);
            toolRecord.setSupplierName(tool.getSupplierName());
            toolRecord.setUseName(user.getName());
            toolRecord.setToolId(toolId);
            toolRecord.setRecordType(3);
            toolRecord.setAllNumber(oldNumber.toString());
            toolRecord.setNumber(car.getNum());
            toolRecord.setOutName(linkOrderInfo == null ? null : linkOrderInfo.getName());
            toolRecord.setOutMobile(linkOrderInfo == null ? null : linkOrderInfo.getLinkMobile());
            toolRecord.setPrice(price.divide(num, 2, BigDecimal.ROUND_HALF_UP).toString());
            res = toolRecordDao.insert(toolRecord);
            if (res == 0) {
                throw new BusinessException("新增下单记录失败");
            }
        }

        String codeList = payVo.getCodeList();
        if (StringUtils.isNotBlank(codeList)) {
            Gson gson = new Gson();
            List<Map<String, String>> list = gson.fromJson(codeList, new TypeToken<List<Map<String, String>>>() {
            }.getType());
            for (int i = 0; i < list.size(); i++) {
                Integer toolId = Integer.valueOf(list.get(i).get("toolId"));
                List<String> codes = Arrays.asList(list.get(i).get("code").split(","));
                resToolCodeDao.update(toolId, codes);
            }
        }

        return res;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<String> paySync(PaySyncVo paySyncVo) {
        TbUser user = userDao.selectByUserIdAndApiKey(paySyncVo.getUserId(), paySyncVo.getApiKey());
        if (user == null) {
            throw new BusinessException("账号异常");
        }
        TbRole role = roleDao.selectByUserId(user.getId());
        TbEnterprise enterprise = enterpriseDao.selectByUserId(user.getId());
        if (role != null && role.getId() == 5) {
            if (enterprise != null && enterprise.getState() == 0) {
                throw new BusinessException("此账号已停用");
            }
        }

        Gson g = new Gson();
        List<PayVo> dataList = g.fromJson(paySyncVo.getJsonData(), new TypeToken<List<PayVo>>() {
        }.getType());
        List<String> resultList = new ArrayList<>();
        for (int j = 0; j < dataList.size(); j++) {
            PayVo payVo = dataList.get(j);
            //pay start
            Integer enterpriseId = payVo.getEnterpriseId();
            if (enterpriseId == null) {
                throw new BusinessException("enterpriseId不能为空");
            }
            if (StringUtils.isBlank(payVo.getOrderNumber())) {
                throw new BusinessException("订单编号不能为空");
            }

            TbToolOrder toolOrder = toolOrderDao.selectByOrderNumber(payVo.getOrderNumber());
            if (toolOrder != null) {
                throw new BusinessException("订单编号已存在");
            }

            TbLinkOrderInfo linkOrderInfo = null;
            if (StringUtils.isNotEmpty(payVo.getIdcard())) {
                linkOrderInfo = linkOrderInfoDao.selectByIdcard(payVo.getIdcard());
            }

            //保存订单信息
            toolOrder = new TbToolOrder();
            toolOrder.setToolEnterpriseId(enterpriseId);
            toolOrder.setPlantEnterpriseId(linkOrderInfo == null ? null : linkOrderInfo.getId());
            toolOrder.setAddPeople(user.getName());
            toolOrder.setOrderNumber(payVo.getOrderNumber());
            toolOrder.setPrice(payVo.getPrice());
            toolOrder.setPic(payVo.getPic());
            toolOrder.setOrderType(payVo.getOrderType() == null ? 0 : payVo.getOrderType());
            int res = toolOrderDao.insert(toolOrder);
            if (res == 0) {
                throw new BusinessException("新增订单失败");
            }

            //保存订单商品信息
            String[] toolIdArray = payVo.getToolIds().split(",");
            String[] accountNumArray = payVo.getAccountNums().split(",");
            String[] priceArray = payVo.getPrices().split(",");
            String[] finalRatioFeeArray = payVo.getFinalRatioFees().split(",");

            for (int i = 0; i < toolIdArray.length; i++) {
                Integer toolId = Integer.valueOf(toolIdArray[i]);
                TbTool tool = toolDao.selectById(toolId);
                if (tool == null) {
                    throw new BusinessException("toolId:" + toolIdArray[i] + "错误");
                }

                TbShoppingCar car = new TbShoppingCar();
                car.setEnterpriseId(enterpriseId);
                car.setToolId(toolId);
                car.setNum(accountNumArray[i]);
                car.setUniformPrice(tool.getUniformPrice());
                String originalPriceStr = StringUtils.isEmpty(priceArray[i]) ? "0" : priceArray[i];
                car.setOriginalPrice(originalPriceStr);

                String priceStr = StringUtils.isEmpty(finalRatioFeeArray[i]) ? "0" : finalRatioFeeArray[i];
                String numStr = StringUtils.isEmpty(accountNumArray[i]) ? "0" : accountNumArray[i];
                BigDecimal price = new BigDecimal(priceStr);
                BigDecimal num = new BigDecimal(numStr);
                car.setPrice(price.divide(num, 2, BigDecimal.ROUND_HALF_UP).toString());
                res = shoppingCarDao.insert(car);
                if (res == 0) {
                    throw new BusinessException("新增订单商品失败");
                }

                TbResOrderCar resOrderCar = new TbResOrderCar();
                resOrderCar.setCarId(car.getId());
                resOrderCar.setOrderId(toolOrder.getId());
                res = resOrderCarDao.insert(resOrderCar);
                if (res == 0) {
                    throw new BusinessException("关联失败");
                }

                BigDecimal oldNumber = BigDecimal.ZERO;
                if (tool.getNumber() != null) {
                    oldNumber = new BigDecimal(tool.getNumber());
                }
                oldNumber = oldNumber.subtract(new BigDecimal(car.getNum())).setScale(2, BigDecimal.ROUND_HALF_UP);
                res = toolDao.updateNumber(toolId, oldNumber.toString());
                if (res == 0) {
                    throw new BusinessException("修改库存失败");
                }

                TbToolRecord toolRecord = new TbToolRecord();
                toolRecord.setEnterpriseId(enterpriseId);
                toolRecord.setSupplierName(tool.getSupplierName());
                toolRecord.setUseName(user.getName());
                toolRecord.setToolId(toolId);
                toolRecord.setRecordType(3);
                toolRecord.setAllNumber(oldNumber.toString());
                toolRecord.setNumber(car.getNum());
                toolRecord.setOutName(linkOrderInfo == null ? null : linkOrderInfo.getName());
                toolRecord.setOutMobile(linkOrderInfo == null ? null : linkOrderInfo.getLinkMobile());
                toolRecord.setPrice(price.divide(num, 2, BigDecimal.ROUND_HALF_UP).toString());
                res = toolRecordDao.insert(toolRecord);
                if (res == 0) {
                    throw new BusinessException("新增下单记录失败");
                }
            }

            String codeList = payVo.getCodeList();
            if (StringUtils.isNotBlank(codeList)) {
                Gson gson = new Gson();
                List<Map<String, String>> list = gson.fromJson(codeList, new TypeToken<List<Map<String, String>>>() {
                }.getType());
                for (int i = 0; i < list.size(); i++) {
                    Integer toolId = Integer.valueOf(list.get(i).get("toolId"));
                    List<String> codes = Arrays.asList(list.get(i).get("code").split(","));
                    resToolCodeDao.update(toolId, codes);
                }
            }
            //pay end
            resultList.add(payVo.getOrderNumber());
        }

        return resultList;
    }

}
