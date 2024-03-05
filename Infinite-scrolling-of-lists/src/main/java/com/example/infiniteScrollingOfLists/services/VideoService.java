package com.example.infiniteScrollingOfLists.services;

import com.example.infiniteScrollingOfLists.entities.VideoData;

import java.util.List;

public interface VideoService {
    List<VideoData> getListOfVideoData(int pageNumber, int pageSize);
}
