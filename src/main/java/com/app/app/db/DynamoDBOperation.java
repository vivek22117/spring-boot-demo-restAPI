package com.app.app.db;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.app.app.entity.LastRecordPerCompanyId;

@Component
public class DynamoDBOperation {
	
	private static final String INDEX = "TableIndx";
	private DynamoDBMapper dbMapper;

	@Autowired
	public DynamoDBOperation(DynamoDBMapper dbMapper) {
		this.dbMapper = dbMapper;
	}

	public void saveInDDB(HashSet<LastRecordPerCompanyId> lastRecordSet) {
		lastRecordSet.parallelStream().forEach(record -> save(record));
	}

	private Boolean save(LastRecordPerCompanyId record) {
		dbMapper.save(record);
		return true;
	}

	public List<String> getReportedDates(Long comapnyId, Long deptId, String startTime, String endTime,Integer ddbLimit) {
		
		return get(comapnyId+"="+deptId, INDEX,startTime,endTime,ddbLimit);
	}

	private List<String> get(String key, String index, String startTime, String endTime, Integer ddbLimit) {
		
		return null;
	}
	
	

}
