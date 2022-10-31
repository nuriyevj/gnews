package com.gnews.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GNewsSearchRS {

    @JsonProperty("totalArticles")
    private String totalArticles;

    @JsonProperty("articles")
    private List<GNewsArticleRS> articles;

    public String getTotalArticles() {
        return totalArticles;
    }

    public void setTotalArticles(String totalArticles) {
        this.totalArticles = totalArticles;
    }

    public List<GNewsArticleRS> getArticles() {
        if (articles == null) {
            articles = new ArrayList<>();
        }
        return articles;
    }

    public void setArticles(List<GNewsArticleRS> articles) {
        this.articles = articles;
    }
}
