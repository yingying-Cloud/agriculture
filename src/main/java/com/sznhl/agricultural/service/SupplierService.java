package com.sznhl.agricultural.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sznhl.agricultural.dao.yiwu.LoginDao;
import com.sznhl.agricultural.dao.yiwu.SupplierDao;
import com.sznhl.agricultural.handler.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {
    @Autowired
    private SupplierDao supplierDao;

    @Autowired
    private LoginDao loginDao;

    public PageInfo<String> listAllSupplier(String userId, String apiKey, Integer pageNum, Integer pageSize, String name) {
        List<Integer> enterpriseIdList = loginDao.selectByUserIdAndApiKey(userId, apiKey);
        if (enterpriseIdList.size() == 0) {
            throw new BusinessException("账号异常");
        }
        PageHelper.startPage(pageNum, pageSize);
        List<String> list = supplierDao.selectByName(enterpriseIdList, name);
        PageInfo<String> pageInfo = new PageInfo<String>(list);
        return pageInfo;
    }

}
