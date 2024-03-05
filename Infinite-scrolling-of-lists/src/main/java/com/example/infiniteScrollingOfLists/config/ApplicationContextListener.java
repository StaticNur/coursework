package com.example.infiniteScrollingOfLists.config;

import com.example.infiniteScrollingOfLists.dao.VideoDAO;
import com.example.infiniteScrollingOfLists.dao.impl.VideoDAOImpl;
import com.example.infiniteScrollingOfLists.services.impl.VideoServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;


@WebListener
public class ApplicationContextListener implements ServletContextListener {
    private MongoClient mongoClient;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        DBMigrationService dbMigrationService = new DBMigrationService();
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        dbMigrationService.migration(mongoClient);

        VideoDAO videoDAO = new VideoDAOImpl(mongoClient);
        servletContext.setAttribute("videoService", new VideoServiceImpl(videoDAO));
        servletContext.setAttribute("objectMapper", new ObjectMapper());
        servletContext.setAttribute("videoDAO", videoDAO);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        mongoClient.close();
    }
}
