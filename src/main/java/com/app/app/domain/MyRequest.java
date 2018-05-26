package com.app.app.domain;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

@Component
public class MyRequest {

	private static final String COUNT = "pagecount";
	private Long comapnyId;
	private Long deptId;
	private Long empId;
	private Boolean lastKnow;
	private String startTime;
	private String endTime;
	private String actualStartTime;
	private String actualEndTime;
	private Integer count;
	private List<String> reportedDates;
	
	
	
	public List<String> getReportedDates() {
		return reportedDates;
	}
	public void setReportedDates(List<String> reportedDates) {
		this.reportedDates = reportedDates;
	}
	public void initialiMyRequest(){
		if(!Objects.isNull(startTime)){
			actualStartTime = startTime;
		}
		if(!Objects.isNull(endTime)){
			actualEndTime = endTime;
		}
		
		if(count == 0 && Objects.isNull(count)){
			count = Integer.parseInt(System.getenv(COUNT));
		}
		
		if(Objects.isNull(startTime) && !Objects.isNull(endTime)){
			startTime = String.valueOf(Instant.parse(endTime).minus(43200 *2,ChronoUnit.SECONDS));
		}
	}
	public Long getComapnyId() {
		return comapnyId;
	}
	public void setComapnyId(Long comapnyId) {
		this.comapnyId = comapnyId;
	}
	public Long getDeptId() {
		return deptId;
	}
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	public Long getEmpId() {
		return empId;
	}
	public void setEmpId(Long empId) {
		this.empId = empId;
	}
	public Boolean getLastKnow() {
		return lastKnow;
	}
	public void setLastKnow(Boolean lastKnow) {
		this.lastKnow = lastKnow;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getActualStartTime() {
		return actualStartTime;
	}
	public void setActualStartTime(String actualStartTime) {
		this.actualStartTime = actualStartTime;
	}
	public String getActualEndTime() {
		return actualEndTime;
	}
	public void setActualEndTime(String actualEndTime) {
		this.actualEndTime = actualEndTime;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	
}
