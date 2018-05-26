package com.app.app.configuration;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

@SpringBootConfiguration
public class AppConfiguration {
	
	
	@Bean
	public DynamoDBMapper getDBMapper(){
		return new DynamoDBMapper(AmazonDynamoDBClientBuilder.standard()
				.withRegion("us-east-1")
				.withCredentials(new ProfileCredentialsProvider("myProfile")).build());
	}

}
