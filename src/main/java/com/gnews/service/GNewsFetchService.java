package com.gnews.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gnews.exception.AppsException;
import com.gnews.model.dto.GNewsArticleRS;
import com.gnews.model.dto.GNewsSearchRQ;
import com.gnews.model.dto.GNewsSearchRS;
import com.gnews.service.support.GNewsFetchAPI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GNewsFetchService {

    @Value("${gnews.api.base.url}")
    private String baseURL;

    @Value("${gnews.api.key}")
    private String apiKey;

    @Autowired
    private GNewsFetchAPI gNewsFetchAPI;

    public GNewsSearchRS getGNewsSearchData(GNewsSearchRQ gNewsSearchRQ) throws AppsException {
        String searchURL = this.buildSearchURL(gNewsSearchRQ);

        String resultJSON = this.gNewsFetchAPI.fetchSearchData(searchURL);

        ObjectMapper objectMapper = new ObjectMapper();

        GNewsSearchRS gNewsSearchRS;

        try {
            gNewsSearchRS = objectMapper.readValue(resultJSON, GNewsSearchRS.class);
        } catch (JsonProcessingException e) {
            throw new AppsException("JSON processing failed");
        }

        //Filter by source name
        if (!StringUtils.isEmpty(gNewsSearchRQ.getSourceName())) {
            List<GNewsArticleRS> filteredArticles = new ArrayList<>();

            for (GNewsArticleRS articleRS : gNewsSearchRS.getArticles()) {
                if (articleRS.getSource() != null && !StringUtils.isEmpty(articleRS.getSource().getName())) {
                    if (articleRS.getSource().getName().contains(gNewsSearchRQ.getSourceName())) {
                        filteredArticles.add(articleRS);
                    }
                }
            }

            gNewsSearchRS.setArticles(filteredArticles);
            gNewsSearchRS.setTotalArticles(String.valueOf(filteredArticles.size()));
        }

        return gNewsSearchRS;
    }

    /**
     * @param searchRQ
     * @return
     * @throws AppsException
     */
    private String buildSearchURL(GNewsSearchRQ searchRQ) throws AppsException {
        String URL = baseURL;
        if (StringUtils.isEmpty(apiKey)) {
            throw new AppsException("Can't find API key");
        }

        URL = URL.replace("{apiKey}", apiKey);

        if (StringUtils.isEmpty(searchRQ.getQuerySyntax())) {
            throw new AppsException("Query Syntax can't be null or empty");
        } else {
            URL = URL.replace("{q}", searchRQ.getQuerySyntax());
        }

        if (searchRQ.getSearchParam() != null) {
            URL = URL.concat("&in=").concat(searchRQ.getSearchParam().getSearchParam());
        }

        if (!StringUtils.isEmpty(searchRQ.getMax())) {
            URL = URL.concat("&max=").concat(searchRQ.getMax());
        }

        if (searchRQ.getSortBy() != null) {
            URL = URL.concat("&").concat(searchRQ.getSortBy().getSortBy()).concat("=sort");
        }

        System.out.println(URL);

        return URL;
    }
}
