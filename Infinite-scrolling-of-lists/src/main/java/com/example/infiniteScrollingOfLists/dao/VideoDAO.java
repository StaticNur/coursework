package com.example.infiniteScrollingOfLists.dao;

import com.mongodb.client.FindIterable;
import org.bson.Document;

public interface VideoDAO {
    FindIterable<Document> getDocuments(int pageNumber, int pageSize);
}
