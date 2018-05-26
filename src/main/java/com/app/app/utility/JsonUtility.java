package com.app.app.utility;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.app.app.domain.Record;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;


@Component
public class JsonUtility {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtility.class);
	private ObjectMapper objectMapper;

	public JsonUtility(){
		this((new ObjectMapper()).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false));
	}
	public JsonUtility(ObjectMapper mapper) {
		this.objectMapper = mapper;
	}
	
	
	 public <T> String convertToJson(T object) throws JsonProcessingException {
	        try{
	            return objectMapper.writeValueAsString(object);
	        } catch (JsonProcessingException jx) {
	        	LOGGER.error("Unable to convert object into Json string: ", jx);
	        }
	        return null;
	    }

	    public <T> T convertFromJson(String json, Class<T> object) throws IOException {
	        try{
	             return objectMapper.readValue(json, object);
	        } catch (JsonProcessingException jx){
	        	LOGGER.error("Unable to conver json string to object: ", jx);
	        }
	        return null;
	    }

	    public <T> T converCollectionFromJson(String json, Class<T> object){

	        ObjectMapper objectMapper = new ObjectMapper();
	        TypeFactory typeFactory = objectMapper.getTypeFactory();
	        try {
	            return objectMapper.readValue(json, typeFactory.constructCollectionType(List.class, Record.class));
	        } catch (IOException e) {
	        	LOGGER.error("Unable to conver json string to list of object: ", e);
	        }
	        return null;
	    }
	
}
