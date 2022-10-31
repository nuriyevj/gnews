package com.gnews.service.support;

import com.gnews.exception.AppsException;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Component
public class GNewsFetchAPI {

    public String fetchSearchData(String searchURL) throws AppsException {
        String responseBody;

        RestTemplate restTemplate = new RestTemplate();

        //Create headers
        HttpHeaders headers = this.generateHeaders();

        HttpEntity request = new HttpEntity(headers);

        //Send GET request
        //Make an HTTP GET request with headers
        ResponseEntity<String> response = restTemplate.exchange(
                searchURL,
                HttpMethod.GET,
                request,
                String.class,
                1
        );

        //Check response
        if (response.getStatusCode() == HttpStatus.OK) {
            //Get response body
            responseBody = response.getBody();
            return responseBody;
        } else {
            throw new AppsException(
                    "Communication with external API is failed, Response Status Code : ".concat(response.getStatusCode().toString()));
        }
    }

    private HttpHeaders generateHeaders() {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-Request-Source", "Desktop");

        return headers;
    }
}
