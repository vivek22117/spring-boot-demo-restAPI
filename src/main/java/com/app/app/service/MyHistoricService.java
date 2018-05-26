package com.app.app.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.app.domain.MyRequest;
import com.app.app.domain.Record;
import com.app.app.domain.Response;

@Service
public class MyHistoricService {

	private static final String MODEL_URL = "http://test.tal.componens/api/data/";
	private static final String DATA_TYPE = "deptartment/service?";
	private static final String DELIMITER = "&";
	private RestApiService service;
	
	@Autowired
	public MyHistoricService(RestApiService service) {
		this.service = service;
	}


	public Response get12HoursHistoricRecords(MyRequest request) {
		List<Record> allRecords = new ArrayList<>();
		request.initialiMyRequest();
		String url = buildUrl(request);
		try {
			service.getRestResponse(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}


	private String buildUrl(MyRequest request) {
		StringBuilder builder = new StringBuilder();
		builder.append(MODEL_URL).append(DATA_TYPE).append("companyId=")
		.append(request.getComapnyId()).append(DELIMITER).append("deptId=")
		.append(request.getDeptId()).append(DELIMITER).append("startTime=")
		.append(request.getStartTime()).append(DELIMITER).append("endTime=").append(request.getEndTime());
		return builder.toString();
	}

}
