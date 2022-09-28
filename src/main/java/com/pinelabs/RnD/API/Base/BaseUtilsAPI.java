package com.pinelabs.RnD.API.Base;
import com.pinelabs.RnD.AndroidUI.Constants.EnumsRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.NotFoundException;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseUtilsAPI {

    static RequestSpecification httpRequest = RestAssured.given().log().all();
    static RestAssuredConfig restAssuredConfig;
    private static final ThreadLocal<ObjectMapper> tlObjectMapper = new ThreadLocal<ObjectMapper>();
    String methodName;
    static RequestSpecBuilder requestSpecBuilder;
    static EncoderConfig encoderConfig;


    public void setupConnection(String baseURL) {
        RestAssured.baseURI = baseURL;
        httpRequest = RestAssured.given().log().all();
        httpRequest.contentType(ContentType.JSON);

    }

    public static ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = tlObjectMapper.get();
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
//            objectMapper.setVisibilityChecker(
//                    objectMapper.getVisibilityChecker().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
//            objectMapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            tlObjectMapper.set(objectMapper);
        }
        return objectMapper;
    }

    public String setURI(String baseURI, String endPoint) {
        return baseURI + endPoint;
    }

    private RequestSpecBuilder getRequestBuilderInstance() {
        if (requestSpecBuilder == null)
            requestSpecBuilder = new RequestSpecBuilder();
        return requestSpecBuilder;
    }

    private EncoderConfig getEncoderConfigInstance() {
        if (encoderConfig == null)
            encoderConfig = new EncoderConfig();
        return encoderConfig;
    }

    public Response execute(String request, String endPoint, String baseURI) {
        Response response;
        String uri = setURI(baseURI, endPoint);
        requestSpecBuilder = getRequestBuilderInstance();
        encoderConfig = getEncoderConfigInstance();
        requestSpecBuilder.setBody(request);
        requestSpecBuilder.addHeader("merchantId", "A2GI92ROAM509H");
        requestSpecBuilder.setBaseUri(uri);
        requestSpecBuilder.setContentType(ContentType.JSON);
        RequestSpecification specification = requestSpecBuilder.addFilter(new RequestLoggingFilter()).
                addFilter(new ResponseLoggingFilter()).build();
        encoderConfig.appendDefaultContentCharsetToContentTypeIfUndefined(false);
        RestAssured.defaultParser = Parser.JSON;
        switch (methodName.toUpperCase()) {
            case "POST":
                response = given().spec(specification).relaxedHTTPSValidation().when().post();
                return response;
            case "GET":
                response = given().spec(specification).relaxedHTTPSValidation().when().get();
                return response;
            case "PUT":
                response = given().spec(specification).relaxedHTTPSValidation().when().put();
                return response;
            case "PATCH":
                response = given().spec(specification).relaxedHTTPSValidation().when().patch();
                return response;
            case "DELETE":
                response = given().spec(specification).relaxedHTTPSValidation().when().delete();
                return response;
            default:
                throw new NotFoundException("Please select proper api method");

        }

    }

    public Response execute(String request, String endPoint, String baseURI, String authTokenForHeader) {
        Response response;
        String uri = setURI(baseURI, endPoint);
        requestSpecBuilder = getRequestBuilderInstance();
        encoderConfig = getEncoderConfigInstance();
        requestSpecBuilder.setBody(request);
        requestSpecBuilder.setBaseUri(uri);
        requestSpecBuilder.setContentType(ContentType.JSON);
        RequestSpecification specification = requestSpecBuilder.addFilter(new RequestLoggingFilter()).
                addFilter(new ResponseLoggingFilter()).build();

        encoderConfig.appendDefaultContentCharsetToContentTypeIfUndefined(false);
        RestAssured.defaultParser = Parser.JSON;
        switch (methodName.toUpperCase()) {
            case "POST":
                response = given().spec(specification).auth().oauth2(authTokenForHeader).relaxedHTTPSValidation().when().post();
                return response;
            case "GET":
                response = given().spec(specification).relaxedHTTPSValidation().when().get();
                return response;
            case "PUT":
                response = given().spec(specification).relaxedHTTPSValidation().when().put();
                return response;
            case "PATCH":
                response = given().spec(specification).relaxedHTTPSValidation().when().patch();
                return response;
            case "DELETE":
                response = given().spec(specification).relaxedHTTPSValidation().when().delete();
                return response;
            default:
                throw new NotFoundException("Please select proper api method");

        }

    }

    public <T> T stringToJavaObject(String json, Class<T> className) {
        T t = null;
        try {
            t = getObjectMapper().readValue(json, className);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return t;
    }

    public <T> String javaObjectToString(T t) {
        String jsonString = null;
        try {
            jsonString = getObjectMapper().writeValueAsString(t);
            System.out.println(jsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    public void setMethod(EnumsRepo.methodName methodName) {
        this.methodName = methodName.toString();
    }

    private RestAssuredConfig getRestAssuredConfig() {
        if (restAssuredConfig == null)
            restAssuredConfig = new RestAssuredConfig();
        return restAssuredConfig;
    }

    public void setQueryParams(Map<String, Object> queryParam) {

        given().queryParams(queryParam);
    }
}

