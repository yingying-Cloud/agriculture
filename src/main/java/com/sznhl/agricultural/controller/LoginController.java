package com.sznhl.agricultural.controller;

import com.sznhl.agricultural.bean.Result;
import com.sznhl.agricultural.service.LoginService;
import com.sznhl.agricultural.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "登录管理")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    @ApiOperation("登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "手机号", paramType = "query", required = true, dataType = "string"),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "query", required = true, dataType = "string"),
            @ApiImplicitParam(name = "cashRegisterId", value = "设备码", paramType = "query", required = true, dataType = "string"),
    })
    public Result<UserVo> login(@RequestParam(value = "account", required = true) String account,
                                @RequestParam(value = "password", required = true) String password,
                                @RequestParam(value = "cashRegisterId", required = true) String cashRegisterId,
                                @RequestParam(value = "cashRegisterVersion", required = false) String cashRegisterVersion) {
        UserVo userVo = loginService.login(account, password, cashRegisterId, cashRegisterVersion);
        return new Result<UserVo>().ok(userVo);
    }

}
