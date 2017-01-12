package com.ekart.springbootjetty.sample.apis;


import com.ekart.springbootjetty.sample.apis.dtos.CreateUserRequest;
import com.ekart.springbootjetty.sample.apis.util.ApiUtil;
import com.google.common.collect.ImmutableMap;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

public class ApiInterface {

    private RestTemplate client;
    private String host;

    public ApiInterface() {

    }

    public void setClient(RestTemplate client) {

        this.client = client;
    }

    public void setHost(String host) {

        this.host = host;
    }

    public <T> ResponseEntity<T> genericRequest(String urlPath, HttpMethod httpMethod,
                                                Class<T> responseClass, HttpHeaders httpHeaders){
        HttpEntity<?> httpRequest = new HttpEntity<>(httpHeaders);
        return client.exchange(url(urlPath), httpMethod, httpRequest, responseClass);
    }


    public <T> ResponseEntity<T> createRequest(Class<T> responseClass, HttpHeaders httpHeaders) {

        HttpEntity<?> httpRequest = new HttpEntity<>(httpHeaders);
        return client.exchange(url("/api/createUser/"), HttpMethod.PUT, httpRequest,
              responseClass);
    }

    public <T> ResponseEntity<T> createRequestWithBody(CreateUserRequest createUserRequest,
                                               Class<T> responseClass, HttpHeaders httpHeaders) {
        HttpEntity<?> httpRequest;
        if (createUserRequest == null) httpRequest = new HttpEntity<>(httpHeaders);
        else httpRequest = new HttpEntity<>(createUserRequest, httpHeaders);
        return client.exchange(url("/api/createUserFromBody/"), HttpMethod.PUT, httpRequest,
                responseClass);
    }

    public <T> ResponseEntity<T> testRequest(Class<T> responseClass, HttpHeaders httpHeaders) {
        HttpEntity<?> httpRequest;
        httpRequest = new HttpEntity<>(httpHeaders);
        return client.exchange(url("/api/createUserFromBody/"), HttpMethod.PUT, httpRequest,
                responseClass);
    }


//    public <T> ResponseEntity<T> getReceiveRequestByFacilityIdAndSourceDocumentTypeAndSourceDocumentId(
//          String facilityId, String sourceDocumentType, String sourceDocumentId, Class<T> responseClass) {
//        HttpEntity<?> httpRequest = new HttpEntity<>(apiHeadersNameContact());
//        return client.exchange(url("/api/v1/{facilityId}/receive_request/source_document/{sourceDocumentType}/"
//              + "{sourceDocumentId}"),
//              HttpMethod.GET, httpRequest, responseClass, facilityId, sourceDocumentType, sourceDocumentId);
//    }
//
//    public <T> ResponseEntity<T> modifyRequest(String facilityId, String receiveRequestId, ModifyRequest modifyRequest,
//          Class<T> responseClass) {
//
//        HttpEntity<?> httpRequest = new HttpEntity<>(modifyRequest, apiHeadersNameContact());
//        return client.exchange(url("/api/v1/{facilityId}/receive_request/{receiveRequestId}/modify"), HttpMethod.PUT,
//              httpRequest, responseClass, facilityId, receiveRequestId);
//    }
//
//    public <T> ResponseEntity<T> getExpectedItems(String receiveRequestId, Class<T> responseClass) {
//
//        HttpEntity<?> httpRequest = new HttpEntity<>(apiHeadersNameContact());
//        return client.exchange(url("/api/v1/receive_request/test/{receiveRequestId}"), HttpMethod.GET, httpRequest,
//              responseClass, receiveRequestId);
//    }
//
    public HttpHeaders apiHeadersNameContact() {
        String userName = TestUtils.getRandomName(), contact = TestUtils.genRandomContactNo();
        Map<String, String> map = new HashMap<>();
        map.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        map.put(ApiUtil.userName, userName);
        map.put(ApiUtil.contactNo, contact);
        return newHeader(map);
    }

    public HttpHeaders apiHeaders(){
        Map<String, String> map = new HashMap<>();
        map.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        return newHeader(map);
    }

    public HttpHeaders apiHeadersUserId(String userID){
        Map<String, String> map = new HashMap<>();
        map.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        map.put(ApiUtil.userID, userID);
        return newHeader(map);
    }

    public HttpHeaders apiHeadersUserIdProduct(String userID, int productID){
        Map<String, String> map = new HashMap<>();
        map.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        map.put(ApiUtil.userID, userID);
        map.put(ApiUtil.productID, String.valueOf(productID));
        return newHeader(map);
    }

    public HttpHeaders apiHeadersUserIDProductQuantity(String userID, int productID, int reqQuantity){
        Map<String, String> map = new HashMap<>();
        map.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        map.put(ApiUtil.userID, userID);
        map.put(ApiUtil.productID, String.valueOf(productID));
        map.put(ApiUtil.reqQuantity, String.valueOf(reqQuantity));
        return newHeader(map);

    }

    private static HttpHeaders newHeader(Map<String, String> headerContent) {
        HttpHeaders header = new HttpHeaders();
        headerContent.forEach(header::add);
        return header;
    }



    private String url(String urlPath) {
        return url(urlPath, ImmutableMap.of());
    }

    private String url(String urlPath, Map<String, Object> requestParams) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        requestParams.forEach((k, v) -> params.add(k, v.toString()));

        return UriComponentsBuilder.newInstance().scheme("http").host(host).path(urlPath).queryParams(params)
                .build().toUriString();
    }
}
