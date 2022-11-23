package com.sznhl.agricultural.controller;

import com.github.pagehelper.PageInfo;
import com.sznhl.agricultural.bean.Result;
import com.sznhl.agricultural.service.SupplierService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "供应商管理")
@RequestMapping("/supplier")
public class SupplierController {

	@Autowired
	private SupplierService supplierService;

	@PostMapping("/listAllSupplier")
	@ApiOperation("分页查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId", value = "userId", paramType = "query", required = true, dataType = "string"),
			@ApiImplicitParam(name = "apiKey", value = "apiKey", paramType = "query", required = true, dataType = "string"),
			@ApiImplicitParam(name = "pageNum", value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
			@ApiImplicitParam(name = "name", value = "农资", paramType = "query", required = true, dataType = "string"),
	})
	public Result<PageInfo<String>> listAllSupplier(@RequestParam(value = "userId", required = true) String userId,
													@RequestParam(value = "apiKey", required = true) String apiKey,
													@RequestParam(value = "pageNum", required = true) Integer pageNum,
													@RequestParam(value = "pageSize", required = true) Integer pageSize,
													@RequestParam(value = "name", required = true) String name) {
		PageInfo<String> list =  supplierService.listAllSupplier(userId, apiKey, pageNum, pageSize,name);
		return new Result<PageInfo<String>>().ok(list);
	}
}
