package com.sznhl.agricultural.service;

import com.sznhl.agricultural.dao.yiwu.*;
import com.sznhl.agricultural.entity.*;
import com.sznhl.agricultural.handler.BusinessException;
import com.sznhl.agricultural.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private EnterpriseDao enterpriseDao;

    @Autowired
    private CashRegisterDao cshRegisterDao;

    @Autowired
    private LogDao logDao;

    public UserVo login(String account, String password, String cashRegisterId, String cashRegisterVersion) {
        TbUser user = userDao.selectByMobileAndPassword(account, password);
        if (user == null) {
            throw new BusinessException("用户名或密码错误");
        }
//        TbUser user2 = userDao.selectByMobileAndPasswordAndCashRegisterId(account, password, cashRegisterId);
//        if (user2 == null) {
//            throw new BusinessException("设备码错误");
//        }
        TbRole role = roleDao.selectByUserId(user.getId());
        if (role == null) {
            throw new BusinessException("请配置用户角色");
        }
        TbEnterprise enterprise = enterpriseDao.selectByUserId(user.getId());
        if (enterprise == null) {
            throw new BusinessException("请配置用户企业");
        }

        int res = userDao.updateCashRegisterId(user.getId(), cashRegisterId);
        if (res == 0) {
            throw new BusinessException("更新设备码失败");
        }
        if(StringUtils.isNotBlank(cashRegisterVersion)){
            res = userDao.updateCashRegisterVersion(user.getId(), cashRegisterVersion);
            if (res == 0) {
                throw new BusinessException("更新设备版本失败");
            }
        }
        TbCashRegister cashRegister = cshRegisterDao.selectByCashRegisterId(cashRegisterId);

        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user,userVo);
        //获取用户角色信息
        if (role.getId() == 5) {
            userVo.setRoleId(11);
        }else if (role.getId() == 14) {
            userVo.setRoleId(12);
        }
        userVo.setRoleName(role.getRoleName());

        userVo.setEnterpriseId(enterprise.getId());
        userVo.setEnterpriseName(enterprise.getName());

        if (cashRegister != null) {
            cshRegisterDao.updateTime(cashRegister.getId());
            userVo.setBaiduAifaceSn(cashRegister.getBaiduAifaceSn());
            userVo.setMachineVersion(cashRegister.getMachineVersion());
        }else{
            cashRegister = new TbCashRegister();
            cashRegister.setCashRegisterId(cashRegisterId);
            res = cshRegisterDao.insert(cashRegister);
            if (res == 0) {
                throw new BusinessException("新增设备码失败");
            }
            userVo.setBaiduAifaceSn("");
            userVo.setMachineVersion(null);
        }

        //更新tb_user的last_time
        userDao.updateTime(user.getId());

        //记录登录信息
        TbLog log = new TbLog();
        log.setUid(user.getId());
        log.setName(user.getName());
        res = logDao.insert(log);
        if (res == 0) {
            throw new BusinessException("记录登录信息失败");
        }
        return userVo;
    }
}
