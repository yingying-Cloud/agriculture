package com.sznhl.agricultural.controller;

import com.sznhl.agricultural.bean.Result;
import com.sznhl.agricultural.service.ToolOrderService;
import com.sznhl.agricultural.vo.PaySyncVo;
import com.sznhl.agricultural.vo.PayVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "订单管理")
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private ToolOrderService toolOrderService;

    @PostMapping("/listAllOrder")
    @ApiOperation("分页查询所有订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "userId", paramType = "query", required = true, dataType = "string"),
            @ApiImplicitParam(name = "apiKey", value = "apiKey", paramType = "query", required = true, dataType = "string"),
            @ApiImplicitParam(name = "pageNum", value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "enterpriseName", value = "卖出单位", paramType = "query", required = false, dataType = "string"),
            @ApiImplicitParam(name = "name", value = "买入单位", paramType = "query", required = false, dataType = "string"),
            @ApiImplicitParam(name = "orderNumber", value = "订单号", paramType = "query", required = false, dataType = "string"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", paramType = "query", required = true, dataType = "string"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", paramType = "query", required = true, dataType = "string"),
            @ApiImplicitParam(name = "enterpriseId", value = "农资店Id", paramType = "query", required = false, dataType = "int"),
            @ApiImplicitParam(name = "toolName", value = "农资名称", paramType = "query", required = false, dataType = "string"),
    })
    public Result<Map<String,Object>> listAllOrder(@RequestParam(value = "userId", required = true) String userId,
                                                      @RequestParam(value = "apiKey", required = true) String apiKey,
                                                      @RequestParam(value = "pageNum", required = true) Integer pageNum,
                                                      @RequestParam(value = "pageSize", required = true) Integer pageSize,
                                                      @RequestParam(value = "enterpriseName", required = false) String enterpriseName,
                                                      @RequestParam(value = "name", required = false) String name,
                                                      @RequestParam(value = "orderNumber", required = false) String orderNumber,
                                                      @RequestParam(value = "startTime", required = true) String startTime,
                                                      @RequestParam(value = "endTime", required = true) String endTime,
                                                      @RequestParam(value = "enterpriseId", required = false) Integer enterpriseId,
                                                      @RequestParam(value = "toolName", required = false) String toolName) {
        Map<String,Object> map = toolOrderService.listAllOrder(userId, apiKey, pageNum, pageSize, enterpriseName,
                name, orderNumber, startTime, endTime, enterpriseId, toolName);
        return new Result<Map<String,Object>>().ok(map);
    }

    @PostMapping("/pay")
    @ApiOperation("提交订单")
    public Result<Integer> pay(@RequestBody PayVo payVo) {
        toolOrderService.pay(payVo);
        return new Result();
    }

    @PostMapping("/paySync")
    @ApiOperation("同步订单")
    public Result<List<String>> paySync(@RequestBody PaySyncVo paySyncVo) {
        List<String> list = toolOrderService.paySync(paySyncVo);
        return new Result<List<String>>().ok(list);
    }

}
