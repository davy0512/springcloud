package com.mooc.house.hsrv.utils;

import com.mongodb.DBCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * mongoDB 模板方法
 */
@Component
public class MongoDBTemplate {
	@Autowired
	private MongoTemplate mongoTemplate;

	public DBCollection getCollection(String collectionName) {
		return mongoTemplate.getCollection(collectionName);
	}
}
