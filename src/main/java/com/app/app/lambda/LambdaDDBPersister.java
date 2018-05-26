package com.app.app.lambda;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.lambda.runtime.events.KinesisEvent;
import com.app.app.domain.Record;
import com.app.app.utility.JsonUtility;
import com.app.app.utility.LastRecordPersister;

public class LambdaDDBPersister {

	private static final Logger LOGGER = LoggerFactory.getLogger(LambdaDDBPersister.class);
	private LastRecordPersister persister;
	private JsonUtility utility;

	public LambdaDDBPersister(LastRecordPersister persister, JsonUtility utility) {
		this.persister = persister;
		this.utility = utility;
	}

	public void process(KinesisEvent event) {
		List<Record> collectedRecords = event.getRecords().parallelStream()
				.map(record -> record.getKinesis().getData().array().toString()).map(this::convertToObject)
				.collect(Collectors.toList());
		
		persister.saveToDDB(collectedRecords);

	}

	private Record convertToObject(String data) {

		try {
			return utility.convertFromJson(data, Record.class);
		} catch (IOException e) {
			LOGGER.error("unable to parese data into Record type", e);
		}
		return null;

	}

}
