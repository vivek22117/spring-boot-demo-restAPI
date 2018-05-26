package com.app.app.utility;

import static com.amazonaws.util.StringUtils.isNullOrEmpty;

import com.app.app.domain.MyRequest;

public class RequestValidator {

	public void validate(MyRequest request) {
		if(request.getLastKnow()){
			verifyLastKnow(request);
		}
		verifyHistoric(request);
	}

	private void verifyHistoric(MyRequest request) {
		
	}

	private boolean verifyLastKnow(MyRequest request) {
		if(isNullOrEmpty(request.getStartTime()) && isNullOrEmpty(request.getEndTime())){
			if(request.getCount() > 0){
				return false;
			}
			return true;
		}
		return false;
		
	}

}
