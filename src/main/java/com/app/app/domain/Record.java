package com.app.app.domain;

import org.springframework.stereotype.Component;

@Component
public class Record {
	
	private Long companyId;
	private Long deptId;
	private String employeeId;
	private String reportedDate;
	private boolean isActive;
	private String deptType;
	private int numberOfEmployee;
	private String deptCity;
	private String createdDate;
	private String endDate;
	
	
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public Long getDeptId() {
		return deptId;
	}
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getReportedDate() {
		return reportedDate;
	}
	public void setReportedDate(String reportedDate) {
		this.reportedDate = reportedDate;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getDeptType() {
		return deptType;
	}
	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}
	public int getNumberOfEmployee() {
		return numberOfEmployee;
	}
	public void setNumberOfEmployee(int numberOfEmployee) {
		this.numberOfEmployee = numberOfEmployee;
	}
	public String getDeptCity() {
		return deptCity;
	}
	public void setDeptCity(String deptCity) {
		this.deptCity = deptCity;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	

}
