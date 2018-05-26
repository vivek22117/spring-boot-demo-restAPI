package com.app.app.entity;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="MyDDBTable")
public class LastRecordPerCompanyId {
	
	@DynamoDBHashKey(attributeName="companyIdDeptId")
	private String companyIdDeptId;
	
	@DynamoDBAttribute(attributeName="deptId")
	private String deptId;
	
	@DynamoDBAttribute(attributeName="employeeId")
	private String employeeId;
	
	@DynamoDBAttribute(attributeName="timeIntervals")
	private List<String> timeIntervals;
	
	@DynamoDBRangeKey(attributeName="reportedDate")
	private String reportedDate;

	public String getCompanyIdDeptId() {
		return companyIdDeptId;
	}

	public void setCompanyIdDeptId(String companyIdDeptId) {
		this.companyIdDeptId = companyIdDeptId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public List<String> getTimeIntervals() {
		return timeIntervals;
	}

	public void setTimeIntervals(List<String> timeIntervals) {
		this.timeIntervals = timeIntervals;
	}

	public String getReportedDate() {
		return reportedDate;
	}

	public void setReportedDate(String reportedDate) {
		this.reportedDate = reportedDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyIdDeptId == null) ? 0 : companyIdDeptId.hashCode());
		result = prime * result + ((deptId == null) ? 0 : deptId.hashCode());
		result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
		result = prime * result + ((reportedDate == null) ? 0 : reportedDate.hashCode());
		result = prime * result + ((timeIntervals == null) ? 0 : timeIntervals.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LastRecordPerCompanyId other = (LastRecordPerCompanyId) obj;
		if (companyIdDeptId == null) {
			if (other.companyIdDeptId != null)
				return false;
		} else if (!companyIdDeptId.equals(other.companyIdDeptId))
			return false;
		if (deptId == null) {
			if (other.deptId != null)
				return false;
		} else if (!deptId.equals(other.deptId))
			return false;
		if (employeeId == null) {
			if (other.employeeId != null)
				return false;
		} else if (!employeeId.equals(other.employeeId))
			return false;
		if (reportedDate == null) {
			if (other.reportedDate != null)
				return false;
		} else if (!reportedDate.equals(other.reportedDate))
			return false;
		if (timeIntervals == null) {
			if (other.timeIntervals != null)
				return false;
		} else if (!timeIntervals.equals(other.timeIntervals))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LastRecordPerCompanyId [companyIdDeptId=" + companyIdDeptId + ", deptId=" + deptId + ", employeeId="
				+ employeeId + ", timeIntervals=" + timeIntervals + ", reportedDate=" + reportedDate + "]";
	}

}
