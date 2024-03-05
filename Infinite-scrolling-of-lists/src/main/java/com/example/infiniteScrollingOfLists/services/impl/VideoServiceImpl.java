package com.example.infiniteScrollingOfLists.services.impl;

import com.example.infiniteScrollingOfLists.dao.VideoDAO;
import com.example.infiniteScrollingOfLists.entities.VideoData;
import com.example.infiniteScrollingOfLists.services.VideoService;
import com.mongodb.client.FindIterable;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class VideoServiceImpl implements VideoService {
    private final VideoDAO videoDAO;

    public VideoServiceImpl(VideoDAO videoDAO) {
        this.videoDAO = videoDAO;
    }

    @Override
    public List<VideoData> getListOfVideoData(int pageNumber, int pageSize) {
        FindIterable<Document> documents = videoDAO.getDocuments(pageNumber, pageSize);
        return buildVideoData(documents);
    }

    private List<VideoData> buildVideoData(FindIterable<Document> documents) {
        List<VideoData> videoDataList = new ArrayList<>();
        for (Document document : documents) {
            VideoData videoData = new VideoData(document.getString("videoName"),
                    document.getDouble("size"),
                    document.getString("content"));
            videoDataList.add(videoData);
        }
        return videoDataList;
    }
}
