package com.gnews.controller;

import com.gnews.constants.AppsConstants;
import com.gnews.exception.AppsException;
import com.gnews.model.common.ResponseDTO;
import com.gnews.model.dto.GNewsSearchRQ;
import com.gnews.model.dto.GNewsSearchRS;
import com.gnews.service.GNewsFetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "${api.prefix}/gnews-fetch")
public class GNewsFetchController {

    @Autowired
    private GNewsFetchService gNewsFetchService;

    @GetMapping(value = "/getGNewsSearchData", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<GNewsSearchRS>> getGNewsSearchData(@RequestBody GNewsSearchRQ gNewsSearchRQ) {
        ResponseDTO<GNewsSearchRS> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            GNewsSearchRS gNewsSearchRS = this.gNewsFetchService.getGNewsSearchData(gNewsSearchRQ);
            httpStatus = HttpStatus.OK;
            response.setResult(gNewsSearchRS);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
        } catch (AppsException e) {
            httpStatus = HttpStatus.BAD_REQUEST;
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            response.setAppsErrorMessages(e.getAppsErrorMessages());
        }

        return new ResponseEntity<>(response, httpStatus);
    }
}
