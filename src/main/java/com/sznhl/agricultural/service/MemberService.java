package com.sznhl.agricultural.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sznhl.agricultural.dao.yiwu.LoginDao;
import com.sznhl.agricultural.dao.yiwu.MemberDao;
import com.sznhl.agricultural.handler.BusinessException;
import com.sznhl.agricultural.vo.MemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private LoginDao loginDao;

    public PageInfo<MemberVo> listAllMember(String userId, String apiKey, Integer pageNum, Integer pageSize, String key) {
        List<Integer> enterpriseIdList = loginDao.selectByUserIdAndApiKey(userId, apiKey);
        if (enterpriseIdList.size() == 0) {
            throw new BusinessException("账号异常");
        }
        PageHelper.startPage(pageNum, pageSize);
        List<MemberVo> list = memberDao.selectByKey(enterpriseIdList, key);
        PageInfo<MemberVo> pageInfo = new PageInfo<MemberVo>(list);
        return pageInfo;
    }

}
