package com.app.app.utility;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.app.app.db.DynamoDBOperation;
import com.app.app.domain.Record;
import com.app.app.entity.LastRecordPerCompanyId;

public class LastRecordPersister {
	
	private DynamoDBOperation dbOperation;
	

	public void saveToDDB(List<Record> collectedRecords) {
		List<Record> recordList = new ArrayList<>();
		HashSet<LastRecordPerCompanyId> lastRecordSet = new HashSet<>();
		List<Long> deptIds = Arrays.asList(111L,222L,333L,444L,555L,666L,777L,888L,999L);
		deptIds.stream().forEach(id -> recordList.add(createDummyRecord(id)));
		
		Map<Long, List<Record>> mapOfRecords = collectedRecords.stream()
		.filter(data -> data.getDeptType().contains("Finance"))
		.collect(Collectors.groupingBy(Record::getDeptId));
		
		mapOfRecords.entrySet().parallelStream().forEach(record -> {
			record.getValue().parallelStream().forEach(r -> {
				lastRecordSet.add(createDBRecord(r));
				});
		});
		
		dbOperation.saveInDDB(lastRecordSet);
		System.out.println(mapOfRecords.get(222L));
		
	}


	private LastRecordPerCompanyId createDBRecord(Record r) {
		
		LastRecordPerCompanyId record = new LastRecordPerCompanyId();
		record.setCompanyIdDeptId(r.getCompanyId() +"="+r.getDeptId());
		record.setDeptId(String.valueOf(r.getDeptId()));
		record.setEmployeeId(r.getEmployeeId());
		record.setReportedDate(String.valueOf(Instant.now()));
		record.setTimeIntervals(Arrays.asList(r.getReportedDate()));
		return record;
	}


	public static Record createDummyRecord(Long id) {
		Record record = new Record();
		record.setActive(true);
		record.setCompanyId(101L);
		record.setDeptId(id);
		record.setEmployeeId("1A");
		record.setCreatedDate("2017-01-01T12:00:00.000");
		record.setDeptType("Finance");
		record.setDeptCity("Pune");
		record.setEndDate("2018-01-31T23:00:00.000");
		record.setReportedDate("2018-05-05T12:00:00.000");
		record.setNumberOfEmployee(1000);
		return record;
	}
	

}
