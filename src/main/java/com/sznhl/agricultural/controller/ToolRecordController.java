package com.sznhl.agricultural.controller;

import com.github.pagehelper.PageInfo;
import com.sznhl.agricultural.bean.Result;
import com.sznhl.agricultural.service.ToolRecordService;
import com.sznhl.agricultural.vo.ToolRecordVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Api(tags = "库存管理")
@RequestMapping("/toolRecord")
public class ToolRecordController {

    @Autowired
    private ToolRecordService toolRecordService;

    @PostMapping("/listCode")
    @ApiOperation("查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "userId", paramType = "query", required = true, dataType = "string"),
            @ApiImplicitParam(name = "apiKey", value = "apiKey", paramType = "query", required = true, dataType = "string"),
            @ApiImplicitParam(name = "pageNum", value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "toolId", value = "二维码", paramType = "query", required = false, dataType = "int"),
    })
    public Result<PageInfo<Map<String, String>>> listCode(@RequestParam(value = "userId", required = true) String userId,
                                                          @RequestParam(value = "apiKey", required = true) String apiKey,
                                                          @RequestParam(value = "pageNum", required = true) Integer pageNum,
                                                          @RequestParam(value = "pageSize", required = true) Integer pageSize,
                                                          @RequestParam(value = "toolId", required = false) Integer toolId) {
        PageInfo<Map<String, String>> codeList = toolRecordService.listCode(userId, apiKey, pageNum, pageSize, toolId);
        return new Result<PageInfo<Map<String, String>>>().ok(codeList);
    }

    @PostMapping("/addToolRecord")
    @ApiOperation("入库")
    public Result<Integer> saveToolRecord(@RequestBody ToolRecordVo toolRecordVo) {
        toolRecordService.saveToolRecord(toolRecordVo);
        return new Result();
    }

    @PostMapping("/updateToolRecord")
    @ApiOperation("出库")
    public Result<Integer> updateToolRecord(@RequestBody ToolRecordVo toolRecordVo) {
        toolRecordService.updateToolRecord(toolRecordVo);
        return new Result();
    }

//    @PostMapping("/updateCodeState")
//    @ApiOperation("修改二维码状态")
//    public Result<Integer> updateCodeState(@RequestBody ToolCodeVo toolCodeVo) {
//        toolRecordService.updateCodeState(toolCodeVo);
//        return new Result();
//    }
}
