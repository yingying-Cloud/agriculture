package com.sznhl.agricultural.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sznhl.agricultural.dao.yiwu.LoginDao;
import com.sznhl.agricultural.dao.yiwu.ResToolCodeDao;
import com.sznhl.agricultural.dao.yiwu.ToolDao;
import com.sznhl.agricultural.dao.yiwu.ToolRecordDao;
import com.sznhl.agricultural.entity.TbResToolCode;
import com.sznhl.agricultural.entity.TbTool;
import com.sznhl.agricultural.entity.TbToolRecord;
import com.sznhl.agricultural.handler.BusinessException;
import com.sznhl.agricultural.vo.ToolCodeVo;
import com.sznhl.agricultural.vo.ToolRecordVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ToolRecordService {
    @Autowired
    private ResToolCodeDao resToolCodeDao;

    @Autowired
    private LoginDao loginDao;

    @Autowired
    private ToolDao toolDao;

    @Autowired
    private ToolRecordDao toolRecordDao;

    public PageInfo<Map<String, String>> listCode(String userId, String apiKey, Integer pageNum, Integer pageSize, Integer toolId) {
        List<Integer> enterpriseIdList = loginDao.selectByUserIdAndApiKey(userId, apiKey);
        if (enterpriseIdList.size() == 0) {
            throw new BusinessException("账号异常");
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, String>> list = resToolCodeDao.selectByToolId(toolId);
        PageInfo<Map<String, String>> pageInfo = new PageInfo<Map<String, String>>(list);
        return pageInfo;
    }

    @Transactional(rollbackFor = Exception.class)
    public int saveToolRecord(ToolRecordVo toolRecordVo) {
        Integer toolId = toolRecordVo.getToolId();
        if (toolId == null) {
            throw new BusinessException("toolId不能为空");
        }

        List<Integer> enterpriseIdList = loginDao.selectByUserIdAndApiKey(toolRecordVo.getUserId(), toolRecordVo.getApiKey());
        if (enterpriseIdList.size() == 0) {
            throw new BusinessException("账号异常");
        }

        TbTool tbTool = toolDao.selectById(toolId);
        if(tbTool == null){
            throw new BusinessException("农资不存在");
        }

        int res = 0;

        List<String> codeList = toolRecordVo.getCodeList();
        if (codeList != null && !codeList.isEmpty()) {
            //覆盖code
            resToolCodeDao.delete(codeList);

            List<TbResToolCode> list = new ArrayList<>();
            for (String code : codeList) {
                if(StringUtils.isNotBlank(code)){
                    TbResToolCode resToolCode = new TbResToolCode();
                    resToolCode.setToolId(toolId);
                    resToolCode.setCode(code);
                    list.add(resToolCode);
                }
            }

            res = resToolCodeDao.insert(list);
            if (res != list.size()) {
                throw new BusinessException("新增二维码失败");
            }
        }

        BigDecimal allNumber = tbTool.getNumber() == null ? BigDecimal.ZERO : new BigDecimal(tbTool.getNumber());
        if (StringUtils.isNotBlank(toolRecordVo.getNumber())) {
            allNumber = allNumber.add(new BigDecimal(toolRecordVo.getNumber())).setScale(2);
        }else{
            toolRecordVo.setNumber("0");
        }

        TbToolRecord tbToolRecord = new TbToolRecord();
        BeanUtils.copyProperties(toolRecordVo, tbToolRecord);
        //类型1-入库 2-出库
        tbToolRecord.setRecordType(1);
        tbToolRecord.setAllNumber(allNumber.toString());
        res = toolRecordDao.insert(tbToolRecord);
        if (res == 0) {
            throw new BusinessException("入库失败");
        }

        res = toolDao.updateNumber(toolId, allNumber.toString());
        if (res == 0) {
            throw new BusinessException("修改库存失败");
        }
        return res;
    }

    @Transactional(rollbackFor = Exception.class)
    public int updateToolRecord(ToolRecordVo toolRecordVo) {
        Integer toolId = toolRecordVo.getToolId();
        if (toolId == null) {
            throw new BusinessException("toolId不能为空");
        }

        List<Integer> enterpriseIdList = loginDao.selectByUserIdAndApiKey(toolRecordVo.getUserId(), toolRecordVo.getApiKey());
        if (enterpriseIdList.size() == 0) {
            throw new BusinessException("账号异常");
        }

        TbTool tbTool = toolDao.selectById(toolId);
        if(tbTool == null){
            throw new BusinessException("农资不存在");
        }

        int res = 0;

        List<String> codeList = toolRecordVo.getCodeList();
        if (codeList != null && !codeList.isEmpty()) {
            resToolCodeDao.update(toolId, codeList);
        }

        BigDecimal allNumber = tbTool.getNumber() == null ? BigDecimal.ZERO : new BigDecimal(tbTool.getNumber());
        if (StringUtils.isNotBlank(toolRecordVo.getNumber())) {
            allNumber = allNumber.subtract(new BigDecimal(toolRecordVo.getNumber())).setScale(2);
            if (allNumber.compareTo(BigDecimal.ZERO) == -1) {
                throw new BusinessException("操作数超过库存！");
            }
        }else{
            toolRecordVo.setNumber("0");
        }

        TbToolRecord tbToolRecord = new TbToolRecord();
        BeanUtils.copyProperties(toolRecordVo, tbToolRecord);
        //类型1-入库 2-出库
        tbToolRecord.setRecordType(2);
        tbToolRecord.setAllNumber(allNumber.toString());
        res = toolRecordDao.insert(tbToolRecord);
        if (res == 0) {
            throw new BusinessException("出库失败");
        }

        res = toolDao.updateNumber(toolId, allNumber.toString());
        if (res == 0) {
            throw new BusinessException("修改库存失败");
        }
        return res;
    }

    @Transactional(rollbackFor = Exception.class)
    @Deprecated
    public int updateCodeState(ToolCodeVo toolCodeVo) {
        if (toolCodeVo.getToolId() == null) {
            throw new BusinessException("toolId不能为空");
        }
        List<String> codeList = toolCodeVo.getCodeList();
        if (codeList == null || codeList.isEmpty()) {
            throw new BusinessException("codeList不能为空");
        }

        List<Integer> enterpriseIdList = loginDao.selectByUserIdAndApiKey(toolCodeVo.getUserId(), toolCodeVo.getApiKey());
        if (enterpriseIdList.size() == 0) {
            throw new BusinessException("账号异常");
        }

        int res = resToolCodeDao.update(toolCodeVo.getToolId(), codeList);
        if (res != codeList.size()) {
            throw new BusinessException("修改失败");
        }
        return res;
    }
}
