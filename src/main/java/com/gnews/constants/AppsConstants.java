package com.gnews.constants;

import org.apache.commons.lang3.StringUtils;

public class AppsConstants {

    public enum SearchParam {
        TITLE("title"),
        DESCRIPTION("description"),
        CONTENT("content");

        private String searchParam;

        SearchParam(String searchParam) {
            this.searchParam = searchParam;
        }

        public String getSearchParam() {
            return searchParam;
        }
    }

    public enum ResponseStatus {
        SUCCESS, FAILED;

        public static ResponseStatus resolveStatus(String statusStr) {
            ResponseStatus matchingStatus = null;
            if (!StringUtils.isEmpty(statusStr)) {
                matchingStatus = ResponseStatus.valueOf(statusStr.trim());
            }
            return matchingStatus;
        }
    }

    public enum SortBy {
        PUBLISHED_AT("publishedAt"),
        RELEVANCE("relevance");

        private String sortBy;

        SortBy(String sortBy) {
            this.sortBy = sortBy;
        }

        public String getSortBy() {
            return sortBy;
        }
    }
}
