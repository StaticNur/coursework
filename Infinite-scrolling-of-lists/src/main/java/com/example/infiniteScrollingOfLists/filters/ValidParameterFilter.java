package com.example.infiniteScrollingOfLists.filters;

import com.example.infiniteScrollingOfLists.entities.ExceptionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter(value = "/infinite-scroll")
public class ValidParameterFilter implements Filter {
    private ObjectMapper objectMapper;

    @Override
    public void init(FilterConfig filterConfig) {
        objectMapper = (ObjectMapper) filterConfig.getServletContext().getAttribute("objectMapper");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request.getParameter("page") == null || request.getParameter("size") == null) {
            objectMapper.writeValue(response.getWriter(), new ExceptionResponse("Parameters must not be empty."));
            return;
        } else {
            try {
                int pageNumber = Integer.parseInt(request.getParameter("page"));
                int pageSize = Integer.parseInt(request.getParameter("size"));
                if (pageNumber < 0 || pageSize < 0) {
                    objectMapper.writeValue(response.getWriter(), new ExceptionResponse("Parameters must not be negative."));
                    return;
                }
            } catch (Exception e) {
                objectMapper.writeValue(response.getWriter(), new ExceptionResponse("Parameters must be numbers."));
                return;
            }
        }
        chain.doFilter(request, response);
    }
}
