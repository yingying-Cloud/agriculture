package com.sznhl.agricultural.controller;

import com.github.pagehelper.PageInfo;
import com.sznhl.agricultural.bean.Result;
import com.sznhl.agricultural.entity.TbVip;
import com.sznhl.agricultural.service.MemberService;
import com.sznhl.agricultural.service.VipService;
import com.sznhl.agricultural.vo.BindVo;
import com.sznhl.agricultural.vo.MemberVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "会员管理")
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private VipService vipService;

    @PostMapping("/listAllMember")
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "userId", paramType = "query", required = true, dataType = "string"),
            @ApiImplicitParam(name = "apiKey", value = "apiKey", paramType = "query", required = true, dataType = "string"),
            @ApiImplicitParam(name = "pageNum", value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "key", value = "姓名或身份证", paramType = "query", required = false, dataType = "string"),
    })
    public Result<PageInfo<MemberVo>> listAllMember(@RequestParam(value = "userId", required = true) String userId,
                                                    @RequestParam(value = "apiKey", required = true) String apiKey,
                                                    @RequestParam(value = "pageNum", required = true) Integer pageNum,
                                                    @RequestParam(value = "pageSize", required = true) Integer pageSize,
                                                    @RequestParam(value = "key", required = false) String key) {
        PageInfo<MemberVo> list = memberService.listAllMember(userId, apiKey, pageNum,pageSize,key);
        return new Result<PageInfo<MemberVo>>().ok(list);
    }

    @PostMapping("/bindVip")
    @ApiOperation("会员绑定")
    public Result<Integer> bindVip(@RequestBody BindVo bindVo) {
        vipService.bindVip(bindVo);
        return new Result();
    }

    @PostMapping("/unbindVip")
    @ApiOperation("会员解绑")
    public Result<Integer> unbindVip(@RequestBody BindVo bindVo) {
        vipService.unbindVip(bindVo);
        return new Result();
    }

    @PostMapping("/getVipInfo")
    @ApiOperation("查询vip信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "userId", paramType = "query", required = true, dataType = "string"),
            @ApiImplicitParam(name = "apiKey", value = "apiKey", paramType = "query", required = true, dataType = "string"),
            @ApiImplicitParam(name = "enterpriseId", value = "农资店ID", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "linkOrderInfoId", value = "会员Id", paramType = "query", required = true, dataType = "int"),
    })
    public Result<TbVip> getLinkOrderInfoVip(@RequestParam(value = "userId", required = true) String userId,
                                             @RequestParam(value = "apiKey", required = true) String apiKey,
                                             @RequestParam(value = "enterpriseId", required = true) Integer enterpriseId,
                                             @RequestParam(value = "linkOrderInfoId", required = true) Integer linkOrderInfoId) {
        TbVip vip = vipService.getLinkOrderInfoVip(userId, apiKey, enterpriseId, linkOrderInfoId);
        return new Result<TbVip>().ok(vip);
    }
}
