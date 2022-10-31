package com.gnews.model.dto;

import com.gnews.constants.AppsConstants;

public class GNewsSearchRQ {

    //q
    private String querySyntax;

    //lang
    private String language;

    //in
    private AppsConstants.SearchParam searchParam;

    //SortBy
    private AppsConstants.SortBy sortBy;

    //max
    private String max;

    //source.name
    private String sourceName;

    public String getQuerySyntax() {
        return querySyntax;
    }

    public void setQuerySyntax(String querySyntax) {
        this.querySyntax = querySyntax;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public AppsConstants.SearchParam getSearchParam() {
        return searchParam;
    }

    public void setSearchParam(AppsConstants.SearchParam searchParam) {
        this.searchParam = searchParam;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public AppsConstants.SortBy getSortBy() {
        return sortBy;
    }

    public void setSortBy(AppsConstants.SortBy sortBy) {
        this.sortBy = sortBy;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }
}
