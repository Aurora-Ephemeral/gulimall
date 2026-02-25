package com.mall.spzx.utils;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RequiredArgsConstructor
public class HttpClientUtil {


    public static  <T> T get(String url, Class<T> responseType, Map<String, String> query, Map<String, String> headers) {
        HttpHeaders httpHeaders = new HttpHeaders();
        if (headers != null) {
            headers.forEach(httpHeaders::set);
        }
        RestTemplate restTemplate = new RestTemplate();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(httpHeaders), responseType, query);

        return responseEntity.getBody();
    }

    public static  <T> T post(String url, Class<T> responseType, Map<String, String> query, Map<String, String> body, Map<String, String> headers) {
        HttpHeaders httpHeaders = new HttpHeaders();
        if (headers != null) {
            headers.forEach(httpHeaders::set);
        }
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(body, httpHeaders), responseType, query);

        return responseEntity.getBody();
    }

    public static <T> T postForm(String url, Class<T> responseType, Map<String, String> query, Map<String, String> body, Map<String, String> headers) {
        HttpHeaders httpHeaders = new HttpHeaders();
        if (headers != null) {
            headers.forEach(httpHeaders::set);
        }
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        if (body != null) {
            body.forEach(map::add);
        }
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(map, httpHeaders), responseType, query);
        return responseEntity.getBody();
    }
}
