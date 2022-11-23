package com.sznhl.agricultural.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageInfo;
import com.sznhl.agricultural.bean.Result;
import com.sznhl.agricultural.service.ToolService;
import com.sznhl.agricultural.vo.ToolPriceVo;
import com.sznhl.agricultural.vo.ToolVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "农资管理")
@RequestMapping("/tool")
public class ToolController {

    @Autowired
    private ToolService toolService;

    @PostMapping("/listAllTool")
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "userId", paramType = "query", required = true, dataType = "string"),
            @ApiImplicitParam(name = "apiKey", value = "apiKey", paramType = "query", required = true, dataType = "string"),
            @ApiImplicitParam(name = "pageNum", value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "name", value = "农资名称", paramType = "query", required = false, dataType = "string"),
            @ApiImplicitParam(name = "specification", value = "规格", paramType = "query", required = false, dataType = "string"),
            @ApiImplicitParam(name = "registrationCertificateNumber", value = "登记证号", paramType = "query", required = false, dataType = "string"),
            @ApiImplicitParam(name = "productionUnits", value = "生产单位", paramType = "query", required = false, dataType = "string"),
    })
    public Result<PageInfo<ToolVo>> listAllTool(@RequestParam(value = "userId", required = true) String userId,
                                                @RequestParam(value = "apiKey", required = true) String apiKey,
                                                @RequestParam(value = "pageNum", required = true) Integer pageNum,
                                                @RequestParam(value = "pageSize", required = true) Integer pageSize,
                                                @RequestParam(value = "name", required = false) String name,
                                                @RequestParam(value = "specification", required = false) String specification,
                                                @RequestParam(value = "registrationCertificateNumber", required = false) String registrationCertificateNumber,
                                                @RequestParam(value = "productionUnits", required = false) String productionUnits) {
        PageInfo<ToolVo> list = toolService.listAllTool(userId, apiKey, pageNum, pageSize, name, specification,
                registrationCertificateNumber, productionUnits);
        return new Result<PageInfo<ToolVo>>().ok(list);
    }

    @PostMapping("/saveTool")
    @ApiOperation("新增")
    public Result<Integer> saveTool(@RequestBody ToolVo toolVo) throws JsonProcessingException {
        toolService.saveTool(toolVo);
        return new Result();
    }

    @PostMapping("/updateTool")
    @ApiOperation("修改")
    public Result<Integer> updateTool(@RequestBody ToolVo toolVo) throws JsonProcessingException {
        toolService.updateTool(toolVo);
        return new Result();
    }

    @PostMapping("/updatePrice")
    @ApiOperation("修改价格")
    public Result<Integer> updatePrice(@RequestBody ToolPriceVo toolPriceVo) {
        toolService.updatePrice(toolPriceVo);
        return new Result();
    }

    @PostMapping("/updateVipPrice")
    @ApiOperation("修改VIP价格")
    public Result<Integer> updateVipPrice(@RequestBody ToolPriceVo toolPriceVo) {
        toolService.updateVipPrice(toolPriceVo);
        return new Result();
    }

    @PostMapping("/listVipPrice")
    @ApiOperation("查询Vip价格")
    public Result<List<Map<String, Object>>> listVipPrice(@RequestParam(value = "userId", required = true) String userId,
                                                          @RequestParam(value = "apiKey", required = true) String apiKey,
                                                          @RequestParam(value = "toolIdList", required = true) List<Integer> toolIdList) {
        List<Map<String, Object>> list = toolService.listVipPrice(userId, apiKey, toolIdList);
        return new Result<List<Map<String, Object>>>().ok(list);
    }
}
