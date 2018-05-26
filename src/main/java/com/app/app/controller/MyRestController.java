package com.app.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import com.app.app.domain.MyRequest;
import com.app.app.domain.Response;
import com.app.app.service.CompanyService;
import com.app.app.utility.RequestValidator;

@Controller
public class MyRestController {
	
	private CompanyService companyService;
	private RequestValidator validator;
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	public MyRestController(CompanyService companyService, RequestValidator validator) {
		this.companyService = companyService;
		this.validator = validator;
	}

	@GetMapping(value="/mystatereport", produces ={MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> getMystateReportData(@RequestParam(name="companyId",required=true) String comapnyId,
												   @RequestParam(name="deptId", required=true) String deptId,
												   @RequestParam(name="lastKnown", defaultValue="false") Boolean lastKnown,
												   @RequestParam(name="startTime", required=false) String startTime,
												   @RequestParam(name="endTime", required=false) String endTime,
												   @RequestParam(name="count", required=false) Integer count){
		
		MyRequest request = context.getBean(MyRequest.class);
		request.setComapnyId(Long.valueOf(comapnyId));
		request.setDeptId(Long.valueOf(deptId));
		request.setStartTime(startTime);
		request.setEndTime(endTime);
		request.setCount(count);
		request.setLastKnow(lastKnown);
		
		validator.validate(request);
		Response companyResponse = companyService.getCompanyResponse(request);
		return new ResponseEntity<Response>(companyResponse,HttpStatus.OK);
				
	}
	
}
