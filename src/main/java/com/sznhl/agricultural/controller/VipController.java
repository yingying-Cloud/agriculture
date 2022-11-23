package com.sznhl.agricultural.controller;

import com.github.pagehelper.PageInfo;
import com.sznhl.agricultural.bean.Result;
import com.sznhl.agricultural.entity.TbVip;
import com.sznhl.agricultural.service.VipService;
import com.sznhl.agricultural.vo.VipVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "VIP管理")
@RequestMapping("/vip")
public class VipController {

    @Autowired
    private VipService vipService;

    @PostMapping("/listAllVip")
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "userId", paramType = "query", required = true, dataType = "string"),
            @ApiImplicitParam(name = "apiKey", value = "apiKey", paramType = "query", required = true, dataType = "string"),
            @ApiImplicitParam(name = "pageNum", value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "enterpriseId", value = "农资店ID", paramType = "query", required = true, dataType = "int"),
    })
    public Result<PageInfo<TbVip>> listAllVip(@RequestParam(value = "userId", required = true) String userId,
                                              @RequestParam(value = "apiKey", required = true) String apiKey,
                                              @RequestParam(value = "pageNum", required = true) Integer pageNum,
                                              @RequestParam(value = "pageSize", required = true) Integer pageSize,
                                              @RequestParam(value = "enterpriseId", required = true) Integer enterpriseId) {
        PageInfo<TbVip> list = vipService.listAllVip(userId, apiKey, pageNum,pageSize, enterpriseId);
        return new Result<PageInfo<TbVip>>().ok(list);
    }

    @PostMapping("/saveVip")
    @ApiOperation("新增")
    public Result<Integer> saveVip(@RequestBody VipVo vipVo) {
        vipService.addVip(vipVo);
        return new Result();
    }

    @PostMapping("/updateVip")
    @ApiOperation("修改")
    public Result<Integer> updateVip(@RequestBody VipVo vipVo) {
        vipService.updateVip(vipVo);
        return new Result();
    }

    @PostMapping("/removeVip")
    @ApiOperation("删除")
    public Result<Integer> updateTool(@RequestBody VipVo vipVo) {
        vipService.removeVip(vipVo);
        return new Result();
    }

}
