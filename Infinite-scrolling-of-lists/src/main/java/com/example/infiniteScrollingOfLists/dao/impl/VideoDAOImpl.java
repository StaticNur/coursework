package com.example.infiniteScrollingOfLists.dao.impl;

import com.example.infiniteScrollingOfLists.dao.VideoDAO;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class VideoDAOImpl implements VideoDAO {
    private final MongoClient mongoClient;

    public VideoDAOImpl(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Override
    public FindIterable<Document> getDocuments(int pageNumber, int pageSize) {
        MongoDatabase database = mongoClient.getDatabase("my_db");
        MongoCollection<Document> collection = database.getCollection("video");

        int skip = pageNumber * pageSize;
        return collection.find().skip(skip).limit(pageSize);
    }
}
