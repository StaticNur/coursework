package com.example.infiniteScrollingOfLists.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class DBMigrationService {
    public void migration(MongoClient mongoClient) {
        MongoDatabase database = mongoClient.getDatabase("my_db");
        MongoCollection<Document> collection = database.getCollection("video");

        if (collection.countDocuments() > 0) {
            System.out.println("Migration aborted! Collection already contains data.");
            return;
        }

        migrateData(collection);
        System.out.println("Migration completed successfully!");
    }

    public void migrateData(MongoCollection<Document> collection) {
        List<Document> documents = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Document document = new Document("videoName", "videoNum" + i)
                    .append("size", Math.pow(2, i))
                    .append("content", "c3ty" + i * 10101010 + "tht534" + i * 10101010 + "4jfd923jd2e" + i * 10101010);
            documents.add(document);
        }
        collection.insertMany(documents);
    }
}
