package com.sznhl.agricultural.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sznhl.agricultural.dao.common.*;
import com.sznhl.agricultural.dao.yiwu.*;
import com.sznhl.agricultural.entity.*;
import com.sznhl.agricultural.handler.BusinessException;
import com.sznhl.agricultural.vo.ToolPriceVo;
import com.sznhl.agricultural.vo.ToolVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ToolService {

    @Autowired
    private LoginDao loginDao;

    @Autowired
    private ToolPoolDao toolPoolDao;

    @Autowired
    private ResToolFilePoolDao resToolFilePoolDao;

    @Autowired
    private FilePoolDao filePoolDao;

    @Autowired
    private ToolYxcfPoolDao toolYxcfPoolDao;

    @Autowired
    private ToolDao toolDao;

    @Autowired
    private ResToolFileDao resToolFileDao;

    @Autowired
    private FileDao fileDao;

    @Autowired
    private ToolYxcfDao toolYxcfDao;

    @Autowired
    private ToolHistoryDao toolHistoryDao;

    @Autowired
    private ResToolFileHistoryDao resToolFileHistoryDao;

    @Autowired
    private FileHistoryDao fileHistoryDao;

    @Autowired
    private ToolYxcfHistoryDao toolYxcfHistoryDao;

    public PageInfo<ToolVo> listAllTool(String userId, String apiKey, Integer pageNum, Integer pageSize, String name, String specification,
                                        String registrationCertificateNumber, String productionUnits) {
        List<Integer> enterpriseIdList = loginDao.selectByUserIdAndApiKey(userId, apiKey);
        if (enterpriseIdList.size() == 0) {
            throw new BusinessException("账号异常");
        }
        PageHelper.startPage(pageNum, pageSize);
        List<ToolVo> list = toolPoolDao.selectAllTool(name, specification, registrationCertificateNumber, productionUnits);
        PageInfo<ToolVo> pageInfo = new PageInfo<ToolVo>(list);
        return pageInfo;
    }

    //店库处理
    private void toolHandler(TbTool tbTool, List<TbFile> fileList, List<TbToolYxcf> yxcfList) {
        int res = toolDao.insert(tbTool);
        if (res == 0) {
            throw new BusinessException("店库新增农资失败");
        }
        int toolId = tbTool.getId();
        for (TbFile file : fileList) {
            res = fileDao.insert(file);
            if (res == 0) {
                throw new BusinessException("店库新增文件失败");
            }
            TbResToolFile tbResToolFile = new TbResToolFile();
            tbResToolFile.setToolId(toolId);
            tbResToolFile.setFileId(file.getId());
            res = resToolFileDao.insert(tbResToolFile);
            if (res == 0) {
                throw new BusinessException("店库新增农资-文件失败");
            }
        }
        for (TbToolYxcf yxcf : yxcfList) {
            yxcf.setToolId(toolId);
            res = toolYxcfDao.insert(yxcf);
            if (res == 0) {
                throw new BusinessException("店库新增有效成分失败");
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public int saveTool(ToolVo toolVo) throws JsonProcessingException {
        List<Integer> enterpriseIdList = loginDao.selectByUserIdAndApiKey(toolVo.getUserId(), toolVo.getApiKey());
        if (enterpriseIdList.size() == 0) {
            throw new BusinessException("账号异常");
        }

        int res = 0;
        //id不存在，需要插入总库和店库
        if (toolVo.getId() == null) {
            if (toolVo.getEnterpriseId() == null) {
                throw new BusinessException("企业id不能为空");
            }
            if (StringUtils.isEmpty(toolVo.getName())) {
                throw new BusinessException("农资名称不能为空");
            }
            if (StringUtils.isEmpty(toolVo.getRemark())) {
                throw new BusinessException("备注不能为空");
            }

            ObjectMapper mapper = new ObjectMapper();
            List<TbFile> fileList = new ArrayList<>();
            if (StringUtils.isNotEmpty(toolVo.getFile())) {
                fileList = Arrays.asList(mapper.readValue(toolVo.getFile(), TbFile[].class));
            }
            List<TbToolYxcf> yxcfList = new ArrayList<>();
            if (StringUtils.isNotEmpty(toolVo.getYxcfJa())) {
                yxcfList = Arrays.asList(mapper.readValue(toolVo.getFile(), TbToolYxcf[].class));
            }
            //店库
            TbTool tbTool = new TbTool();
            BeanUtils.copyProperties(toolVo, tbTool);
            toolHandler(tbTool, fileList, yxcfList);
            //总库
            TbToolPool tbToolPool = new TbToolPool();
            BeanUtils.copyProperties(toolVo, tbToolPool);
            res = toolPoolDao.insert(tbToolPool);
            if (res == 0) {
                throw new BusinessException("总库新增农资失败");
            }
            int toolPoolId = tbToolPool.getId();
            for (TbFile file : fileList) {
                res = filePoolDao.insert(file);
                if (res == 0) {
                    throw new BusinessException("总库新增文件失败");
                }
                TbResToolFile tbResToolFile = new TbResToolFile();
                tbResToolFile.setToolId(toolPoolId);
                tbResToolFile.setFileId(file.getId());
                res = resToolFilePoolDao.insert(tbResToolFile);
                if (res == 0) {
                    throw new BusinessException("总库新增农资-文件失败");
                }
            }
            for (TbToolYxcf yxcf : yxcfList) {
                yxcf.setToolId(toolPoolId);
                res = toolYxcfPoolDao.insert(yxcf);
                if (res == 0) {
                    throw new BusinessException("总库新增有效成分失败");
                }
            }
        } else {
            TbToolPool tbToolPool = toolPoolDao.selectById(toolVo.getId());
            if (tbToolPool == null) {
                throw new BusinessException("农资ID错误");
            }
            List<TbFile> fileList = filePoolDao.selectByToolId(tbToolPool.getId());
            List<TbToolYxcf> yxcfList = toolYxcfPoolDao.selectByToolId(tbToolPool.getId());
            TbTool tbTool = new TbTool();
            BeanUtils.copyProperties(tbToolPool, tbTool);
            toolHandler(tbTool, fileList, yxcfList);
        }
        return res;
    }

    @Transactional(rollbackFor = Exception.class)
    public int updateTool(ToolVo toolVo) throws JsonProcessingException {
        List<Integer> enterpriseIdList = loginDao.selectByUserIdAndApiKey(toolVo.getUserId(), toolVo.getApiKey());
        if (enterpriseIdList.size() == 0) {
            throw new BusinessException("账号异常");
        }

        if (toolVo.getId() == null) {
            throw new BusinessException("id不能为空");
        }
        if (toolVo.getEnterpriseId() == null) {
            throw new BusinessException("企业id不能为空");
        }
        if (StringUtils.isEmpty(toolVo.getName())) {
            throw new BusinessException("农资名称不能为空");
        }
        if (StringUtils.isEmpty(toolVo.getRemark())) {
            throw new BusinessException("备注不能为空");
        }
        int res = 0;
        ObjectMapper mapper = new ObjectMapper();
        List<TbFile> fileList = new ArrayList<>();
        if (StringUtils.isNotEmpty(toolVo.getFile())) {
            fileList = Arrays.asList(mapper.readValue(toolVo.getFile(), TbFile[].class));
        }
        List<TbToolYxcf> yxcfList = new ArrayList<>();
        if (StringUtils.isNotEmpty(toolVo.getYxcfJa())) {
            yxcfList = Arrays.asList(mapper.readValue(toolVo.getFile(), TbToolYxcf[].class));
        }
        //店库
        TbTool tbTool = new TbTool();
        BeanUtils.copyProperties(toolVo, tbTool);
        toolHandler(tbTool, fileList, yxcfList);
        //总库备份
        TbToolPool tbToolPoolHis = toolPoolDao.selectById(toolVo.getId());
        if (tbToolPoolHis == null) {
            throw new BusinessException("农资ID错误");
        }
        List<TbFile> fileListHis = filePoolDao.selectByToolId(tbToolPoolHis.getId());
        List<TbToolYxcf> yxcfListHis = toolYxcfPoolDao.selectByToolId(tbToolPoolHis.getId());
        TbToolHistory tbToolHistory = new TbToolHistory();
        BeanUtils.copyProperties(tbToolPoolHis, tbToolHistory);
        tbToolHistory.setToolId(tbToolPoolHis.getId());
        res = toolHistoryDao.insert(tbToolHistory);
        if (res == 0) {
            throw new BusinessException("总库备份新增农资失败");
        }
        int toolHistoryId = tbToolHistory.getId();
        for (TbFile file : fileListHis) {
            res = fileHistoryDao.insert(file);
            if (res == 0) {
                throw new BusinessException("总库备份新增文件失败");
            }
            TbResToolFile tbResToolFile = new TbResToolFile();
            tbResToolFile.setToolId(toolHistoryId);
            tbResToolFile.setFileId(file.getId());
            res = resToolFileHistoryDao.insert(tbResToolFile);
            if (res == 0) {
                throw new BusinessException("总库备份新增农资-文件失败");
            }
        }
        for (TbToolYxcf yxcf : yxcfListHis) {
            yxcf.setToolId(toolHistoryId);
            res = toolYxcfHistoryDao.insert(yxcf);
            if (res == 0) {
                throw new BusinessException("总库备份新增有效成分失败");
            }
        }

        //总库
        TbToolPool tbToolPool = new TbToolPool();
        BeanUtils.copyProperties(toolVo, tbToolPool);
        res = toolPoolDao.update(tbToolPool);
        if (res == 0) {
            throw new BusinessException("修改失败");
        }
        int toolPoolId = toolVo.getId();
        resToolFilePoolDao.delete(toolPoolId);
        toolYxcfPoolDao.delete(toolPoolId);
        for (TbFile file : fileList) {
            res = filePoolDao.insert(file);
            if (res == 0) {
                throw new BusinessException("总库新增文件失败");
            }
            TbResToolFile tbResToolFile = new TbResToolFile();
            tbResToolFile.setToolId(toolPoolId);
            tbResToolFile.setFileId(file.getId());
            res = resToolFilePoolDao.insert(tbResToolFile);
            if (res == 0) {
                throw new BusinessException("总库新增农资-文件失败");
            }
        }
        for (TbToolYxcf yxcf : yxcfList) {
            yxcf.setToolId(toolPoolId);
            res = toolYxcfPoolDao.insert(yxcf);
            if (res == 0) {
                throw new BusinessException("总库新增有效成分失败");
            }
        }
        return res;
    }

    @Transactional(rollbackFor = Exception.class)
    public int updatePrice(ToolPriceVo toolPriceVo) {
        if (toolPriceVo.getToolId() == null) {
            throw new BusinessException("toolId不能为空");
        }
        if (StringUtils.isEmpty(toolPriceVo.getPrice())) {
            throw new BusinessException("价格不能为空");
        }
        List<Integer> enterpriseIdList = loginDao.selectByUserIdAndApiKey(toolPriceVo.getUserId(), toolPriceVo.getApiKey());
        if (enterpriseIdList.size() == 0) {
            throw new BusinessException("账号异常");
        }

        int res = toolDao.updatePrice(toolPriceVo.getToolId(), toolPriceVo.getPrice());
        if (res == 0) {
            throw new BusinessException("修改失败");
        }
        return res;
    }

    @Transactional(rollbackFor = Exception.class)
    public int updateVipPrice(ToolPriceVo toolPriceVo) {
        if (toolPriceVo.getToolId() == null) {
            throw new BusinessException("toolId不能为空");
        }
        if (StringUtils.isEmpty(toolPriceVo.getPrice())) {
            throw new BusinessException("价格不能为空");
        }
        if (toolPriceVo.getVipId() == null) {
            throw new BusinessException("vipId不能为空");
        }
        List<Integer> enterpriseIdList = loginDao.selectByUserIdAndApiKey(toolPriceVo.getUserId(), toolPriceVo.getApiKey());
        if (enterpriseIdList.size() == 0) {
            throw new BusinessException("账号异常");
        }
        int res = 0;
        if (toolDao.checkIsExist(toolPriceVo.getToolId(), toolPriceVo.getVipId()) == null) {
            res = toolDao.insertVipPrice(toolPriceVo.getToolId(), toolPriceVo.getVipId(), toolPriceVo.getPrice());
        } else {
            res = toolDao.updateVipPrice(toolPriceVo.getToolId(), toolPriceVo.getVipId(), toolPriceVo.getPrice());
        }
        if (res == 0) {
            throw new BusinessException("修改失败");
        }
        return res;
    }

    public List<Map<String, Object>> listVipPrice(String userId, String apiKey, List<Integer> toolIdList) {
        List<Integer> enterpriseIdList = loginDao.selectByUserIdAndApiKey(userId, apiKey);
        if (enterpriseIdList.size() == 0) {
            throw new BusinessException("账号异常");
        }
        if (toolIdList == null || toolIdList.isEmpty()) {
            throw new BusinessException("toolIdList不能为空");
        }
        List<Map<String, Object>> reslut = new ArrayList<>();
        List<TbResVipToolPrice> vipToolPriceList = toolDao.selectVipPrice(toolIdList);
        for (Integer toolId : toolIdList) {
            List<TbResVipToolPrice> filter = vipToolPriceList.parallelStream().filter(i -> i.getToolId().equals(toolId)).collect(Collectors.toList());
            List<Map<String, String>> list = new ArrayList<>();
            for(TbResVipToolPrice vipToolPrice : filter){
                Map<String, String> map = new HashMap<>();
                map.put("vipId",vipToolPrice.getVipId().toString());
                map.put("price",vipToolPrice.getPrice());
                list.add(map);
            }
            Map<String, Object> toolMap = new HashMap<>();
            toolMap.put("toolId",toolId);
            toolMap.put("vipPrice",list);
            reslut.add(toolMap);
        }
        return reslut;
    }

}
