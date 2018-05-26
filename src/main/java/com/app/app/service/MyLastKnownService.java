package com.app.app.service;

import static java.lang.String.valueOf;
import static java.time.Instant.parse;
import static java.time.temporal.ChronoUnit.SECONDS;
import static java.util.stream.Collectors.toList;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.util.CollectionUtils;
import com.app.app.db.DynamoDBOperation;
import com.app.app.domain.MyRequest;
import com.app.app.domain.Record;
import com.app.app.domain.Response;
import com.app.app.interfaces.MyAPIService;
import com.app.app.utility.AppUtil;

@Service
public class MyLastKnownService implements MyAPIService {

	private static final String URL = "http://test.com/TestComponent?";
	private static final String COMPANYID = "5501";
	private static final String DELIMITER = "&";
	private static final String DEPTID = "2244";
	private DynamoDBOperation dbOperation;
	private RestApiService service;
	

	@Autowired
	public MyLastKnownService(DynamoDBOperation dbOperation, RestApiService service) {
		this.dbOperation = dbOperation;
		this.service = service;
	}

	public Response getModelRecords(MyRequest request) {
		List<Record> allRecords = new ArrayList<>();
		request.initialiMyRequest();
		request.setReportedDates(getReportedDates(dbOperation, 1, request));

		if (!Objects.isNull(request.getReportedDates())) {
			List<String> timeIntervals = getTimeIntervals(request);

			try {
				request.setStartTime(timeIntervals.get(0));
				request.setEndTime(valueOf(parse(timeIntervals.get(timeIntervals.size()-1)).plus(43199, SECONDS)));
				allRecords.addAll(service.getRestResponse(buildUrl(request),request,timeIntervals));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return createPaginateResponse(request,allRecords);

	}

	private Response createPaginateResponse(MyRequest request, List<Record> allRecords) {
		if(!CollectionUtils.isNullOrEmpty(allRecords))
			if(allRecords.size() > request.getCount()){
				request.setEndTime(allRecords.get(request.getCount()).getReportedDate());
			} else {
				request.setEndTime(allRecords.get(allRecords.size() - 1).getReportedDate());
			}
			
		return null;
	}

	private String buildUrl(MyRequest request) {
			return new StringBuffer().append(URL).append("comapnyId=").append(COMPANYID).append(DELIMITER)
			.append("deptId=").append(DEPTID).append(DELIMITER).toString();
	}

	public static List<String> getTimeIntervals(MyRequest request) {
		
		List<String> collect = request.getReportedDates().parallelStream()
				.map(AppUtil::getDateToTimeStamp).map(AppUtil::getTimeIntervals).flatMap(List::stream)
				.filter(i -> !(Instant.parse(i).plus(43199,ChronoUnit.SECONDS).isBefore(Instant.parse(request.getStartTime()))))
				.filter(i -> !(Instant.parse(i).isAfter(Instant.parse(request.getEndTime()))))
				.collect(Collectors.toList());
		System.out.println(collect.toString());
		return collect;
	}
	
	/*public static void main(String[] args) {
		MyLastKnownService service = new MyLastKnownService();
		MyRequest request = new MyRequest();
		request.setComapnyId(115L);
		request.setStartTime("2018-04-01T12:00:10.000Z");
		request.setEndTime("2018-04-03T15:00:10.000Z");
		request.setReportedDates(Arrays.asList("2018/04/03","2018/04/02","2018/04/01"));
		
		service.getTimeIntervals(request);
		
	}
*/
	
}
