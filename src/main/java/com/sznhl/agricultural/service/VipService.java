package com.sznhl.agricultural.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sznhl.agricultural.dao.yiwu.LoginDao;
import com.sznhl.agricultural.dao.yiwu.VipDao;
import com.sznhl.agricultural.entity.TbResVipLinkorderinfo;
import com.sznhl.agricultural.entity.TbResVipToolPrice;
import com.sznhl.agricultural.entity.TbVip;
import com.sznhl.agricultural.handler.BusinessException;
import com.sznhl.agricultural.vo.BindVo;
import com.sznhl.agricultural.vo.VipVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VipService {

    @Autowired
    private VipDao vipDao;

    @Autowired
    private LoginDao loginDao;

    public PageInfo<TbVip> listAllVip(String userId, String apiKey, Integer pageNum, Integer pageSize, Integer enterpriseId) {
        List<Integer> enterpriseIdList = loginDao.selectByUserIdAndApiKey(userId, apiKey);
        if (enterpriseIdList.size() == 0) {
            throw new BusinessException("账号异常");
        }

        PageHelper.startPage(pageNum, pageSize);
        List<TbVip> list = vipDao.selectByEnterpriseId(enterpriseId);
        PageInfo<TbVip> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Transactional(rollbackFor = Exception.class)
    public int addVip(VipVo vipVo) {
        if (vipVo.getEnterpriseId() == null) {
            throw new BusinessException("农资店不能为空");
        }
        List<Integer> enterpriseIdList = loginDao.selectByUserIdAndApiKey(vipVo.getUserId(), vipVo.getApiKey());
        if (enterpriseIdList.size() == 0) {
            throw new BusinessException("账号异常");
        }
        TbVip tbVip = new TbVip();
        BeanUtils.copyProperties(vipVo, tbVip);
        int res = vipDao.insert(tbVip);
        if (res == 0) {
            throw new BusinessException("新增失败");
        }
        return res;
    }

    @Transactional(rollbackFor = Exception.class)
    public int updateVip(VipVo vipVo) {
        if (vipVo.getId() == null) {
            throw new BusinessException("id不能为空");
        }
        List<Integer> enterpriseIdList = loginDao.selectByUserIdAndApiKey(vipVo.getUserId(), vipVo.getApiKey());
        if (enterpriseIdList.size() == 0) {
            throw new BusinessException("账号异常");
        }
        TbVip tbVip = new TbVip();
        BeanUtils.copyProperties(vipVo, tbVip);
        int res = vipDao.update(tbVip);
        if (res == 0) {
            throw new BusinessException("修改失败");
        }
        return res;
    }

    @Transactional(rollbackFor = Exception.class)
    public int removeVip(VipVo vipVo) {
        if (vipVo.getId() == null) {
            throw new BusinessException("id不能为空");
        }
        List<Integer> enterpriseIdList = loginDao.selectByUserIdAndApiKey(vipVo.getUserId(), vipVo.getApiKey());
        if (enterpriseIdList.size() == 0) {
            throw new BusinessException("账号异常");
        }

        TbResVipLinkorderinfo vipLinkorderinfo = vipDao.checkIsLinkorderinfoUse(vipVo.getId());
        if(vipLinkorderinfo != null){
            throw new BusinessException("会员使用中，无法删除");
        }

        TbResVipToolPrice vipToolPrice = vipDao.checkIsToolUse(vipVo.getId());
        if(vipToolPrice != null){
            throw new BusinessException("农资使用中，无法删除");
        }

        int res = vipDao.delete(vipVo.getId());
        if (res == 0) {
            throw new BusinessException("删除失败");
        }
        return res;
    }

    @Transactional(rollbackFor = Exception.class)
    public int bindVip(BindVo bindVo) {
        if (bindVo.getVipId() == null) {
            throw new BusinessException("vipId不能为空");
        }
        if (bindVo.getLinkOrderInfoId() == null) {
            throw new BusinessException("linkOrderInfoId不能为空");
        }
        List<Integer> enterpriseIdList = loginDao.selectByUserIdAndApiKey(bindVo.getUserId(), bindVo.getApiKey());
        if (enterpriseIdList.size() == 0) {
            throw new BusinessException("账号异常");
        }

        TbResVipLinkorderinfo resVipLinkorderinfo = vipDao.checkIsBind(bindVo.getVipId(), bindVo.getLinkOrderInfoId());
        if (resVipLinkorderinfo != null) {
            throw new BusinessException("已绑定");
        }

        TbResVipLinkorderinfo vipLinkorderinfo = new TbResVipLinkorderinfo();
        vipLinkorderinfo.setVipId(bindVo.getVipId());
        vipLinkorderinfo.setLinkOrderInfoId(bindVo.getLinkOrderInfoId());
        int res = vipDao.bind(vipLinkorderinfo);
        if (res == 0) {
            throw new BusinessException("绑定失败");
        }
        return res;
    }

    @Transactional(rollbackFor = Exception.class)
    public int unbindVip(BindVo bindVo) {
        if (bindVo.getVipId() == null) {
            throw new BusinessException("vipId不能为空");
        }
        if (bindVo.getLinkOrderInfoId() == null) {
            throw new BusinessException("linkOrderInfoId不能为空");
        }
        List<Integer> enterpriseIdList = loginDao.selectByUserIdAndApiKey(bindVo.getUserId(), bindVo.getApiKey());
        if (enterpriseIdList.size() == 0) {
            throw new BusinessException("账号异常");
        }
        int res = vipDao.unbind(bindVo.getVipId(), bindVo.getLinkOrderInfoId());
        if (res == 0) {
            throw new BusinessException("解绑失败");
        }
        return res;
    }

    public TbVip getLinkOrderInfoVip(String userId, String apiKey, Integer enterpriseId, Integer linkOrderInfoId) {
        List<Integer> enterpriseIdList = loginDao.selectByUserIdAndApiKey(userId, apiKey);
        if (enterpriseIdList.size() == 0) {
            throw new BusinessException("账号异常");
        }

        TbVip vip = vipDao.selectByEnterpriseIdAndLinkOrderInfoId(enterpriseId, linkOrderInfoId);
        return vip;
    }

}
