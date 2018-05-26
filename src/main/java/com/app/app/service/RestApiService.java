package com.app.app.service;

import static java.lang.String.valueOf;
import static java.time.Instant.parse;
import static java.time.temporal.ChronoUnit.SECONDS;
import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.app.app.domain.MyRequest;
import com.app.app.domain.Record;
import com.app.app.utility.RestClient;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RestApiService {
	
	private RestClient client;
	
	
	public RestApiService(RestClient client) {
		this.client = client;
	}

	private static final Object DELIMITER = "&";

	public List<Record> getRestResponse(String url, MyRequest request, List<String> timeIntervals) throws InterruptedException, ExecutionException {
		ForkJoinPool pool = new ForkJoinPool();
		List<Record> allRecords = new ArrayList<>();
		
		List<String> urlLists = timeIntervals.parallelStream()
				.map(interval -> createUrlList(interval,request, url)).collect(toList());
		
		pool.submit(() -> urlLists.parallelStream().forEach(uri ->{
					try {
						client.get(uri);
					} catch (IOException e) {
						e.printStackTrace();
					}
		})).get();
		
		return allRecords.stream().sorted(Comparator.comparing(Record::getReportedDate)).collect(toList());
	}
	
	private String createUrlList(String interval, MyRequest request, String url){
		String startTime = calculateStartTime(interval,request);
		String endTime = calculateEndTime(interval,request);
		
		StringBuilder builder = new StringBuilder(url);
		builder.append("startTime=").append(startTime).append(DELIMITER).append("endTime=").append(endTime);
		return builder.toString();
	}

	private String calculateEndTime(String interval, MyRequest request) {
		if(!(parse(interval).plus(43199,SECONDS).isAfter(parse(request.getEndTime())))){
			return valueOf(parse(interval).plus(43199,SECONDS));
		} else{
			return request.getEndTime();
		}
	}

	private String calculateStartTime(String interval, MyRequest request) {
		if(Instant.parse(request.getStartTime()).isBefore(Instant.parse(interval))){
			return interval;
		} else {
			return request.getStartTime();
		}
	}

	public Record getRestResponse(String url) throws ClientProtocolException, IOException {
		String string = client.get(url);
		
		ObjectMapper mapper = new ObjectMapper();
		Record readValue = mapper.readValue(string, Record.class);
		
		return readValue;
	}
}
