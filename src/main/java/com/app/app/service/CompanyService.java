package com.app.app.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.app.db.DynamoDBOperation;
import com.app.app.domain.MyRequest;
import com.app.app.domain.Response;
import com.app.app.interfaces.MyAPIService;

@Service
public class CompanyService implements MyAPIService {
	
	private MyLastKnownService myLastKnownService;
	private MyHistoricService myHistoricService;
	
	@Autowired
	public CompanyService(MyLastKnownService myLastKnownService, MyHistoricService myHistoricService) {
		this.myLastKnownService = myLastKnownService;
		this.myHistoricService = myHistoricService;
	}

	public Response getCompanyResponse(MyRequest request) {	
		if(request.getLastKnow()){
			myLastKnownService.getModelRecords(request);
		}
		long millis = ChronoUnit.HOURS.between(Instant.parse(request.getEndTime()), Instant.parse(request.getStartTime()));
		if( millis < 43200 * 1000 ){
			return myHistoricService.get12HoursHistoricRecords(request);
		}
		return null;
		
		
		// TODO Auto-generated method stub
		
	}

}
