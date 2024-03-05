package com.example.infiniteScrollingOfLists.controllers;

import com.example.infiniteScrollingOfLists.entities.VideoData;
import com.example.infiniteScrollingOfLists.services.VideoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/infinite-scroll")
public class VideoController extends HttpServlet {
    private ObjectMapper objectMapper;
    private VideoService videoService;

    @Override
    public void init() {
        objectMapper = (ObjectMapper) getServletContext().getAttribute("objectMapper");
        videoService = (VideoService) getServletContext().getAttribute("videoService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int pageNumber = Integer.parseInt(request.getParameter("page"));
        int pageSize = Integer.parseInt(request.getParameter("size"));

        List<VideoData> data = videoService.getListOfVideoData(pageNumber, pageSize);

        response.setContentType("application/json");
        objectMapper.writeValue(response.getWriter(), data);
    }
}
