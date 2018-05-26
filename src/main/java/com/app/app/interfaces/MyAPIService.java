package com.app.app.interfaces;

import java.util.List;

import com.app.app.db.DynamoDBOperation;
import com.app.app.domain.MyRequest;

public interface MyAPIService {
	
	

	default List<String> getReportedDates(DynamoDBOperation dbOperation, Integer ddbLimit, MyRequest request){
		
		return dbOperation.getReportedDates(request.getComapnyId(),request.getDeptId(),request.getStartTime(),request.getEndTime(),ddbLimit);
	}
}
