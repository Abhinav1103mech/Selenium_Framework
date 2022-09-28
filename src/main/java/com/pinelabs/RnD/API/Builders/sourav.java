//package api.jsonProcessor;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.core.JsonGenerationException;
//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import io.restassured.response.Response;
//
//import java.io.IOException;
//
//public class JacksonJsonImpl implements Json {
//
//    private static final ThreadLocal<ObjectMapper> tlObjectMapper = new ThreadLocal<ObjectMapper>();
//    private static volatile JacksonJsonImpl _instance;
//
//    public JacksonJsonImpl() {
//
//    }
//
//    public static JacksonJsonImpl getInstance() {
//        if (_instance == null) {
//            synchronized (JacksonJsonImpl.class) {
//                if (_instance == null) {
//                    _instance = new JacksonJsonImpl();
//                }
//            }
//        }
//        return _instance;
//    }
//
//    public static ObjectMapper getObjectMapper() {
//        ObjectMapper objectMapper = tlObjectMapper.get();
//        if (objectMapper == null) {
//            objectMapper = new ObjectMapper();
//
//            objectMapper.setVisibilityChecker(
//                    objectMapper.getVisibilityChecker().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
//            objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
//            objectMapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
//            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
//            tlObjectMapper.set(objectMapper);
//        }
//        return objectMapper;
//    }
//
//    public static ObjectMapper getObjectMapperNullFields() {
//        ObjectMapper objectMapper = tlObjectMapper.get();
//        if (objectMapper == null) {
//            objectMapper = new ObjectMapper();
//
//            objectMapper.setVisibilityChecker(
//                    objectMapper.getVisibilityChecker().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
//            objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
//            objectMapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
//            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
//            tlObjectMapper.set(objectMapper);
//        }
//        return objectMapper;
//    }
//
//    public <T> String toJSon(T t) throws IOException {
//        try {
//            String json = getObjectMapper().writeValueAsString(t);
//            return json;
//        } catch (JsonGenerationException jge) {
//            throw jge;
//        } catch (JsonMappingException jme) {
//            throw jme;
//        } catch (IOException ioe) {
//            throw ioe;
//        }
//    }
//
//    public <T> T fromJson(String json, Class<T> clazz) throws IOException {
//
//        try {
//            T t = getObjectMapper().readValue(json, clazz);
//            return t;
//        } catch (JsonParseException jpe) {
//            throw jpe;
//        } catch (JsonMappingException jme) {
//            throw jme;
//        } catch (IOException ioe) {
//            throw ioe;
//        }
//    }
//
//    public <T> T responsefromJson(Response response, Class<T> clazz) throws IOException {
//        T t = null;
//        if (response.getStatusCode() != 400) {
//            t = fromJson(response.asString(), clazz);
//        }
//        return t;
//    }
//
//    public <T> T responsefromJsonfor400(Response response, Class<T> clazz) throws IOException {
//        T t = null;
//        t = fromJson(response.asString(), clazz);
//        return t;
//    }
//
//    public <T> T fromJson(String json, TypeReference typeRef) throws IOException {
//
//        try {
//            T t = getObjectMapper().readValue(json, typeRef);
//            return t;
//        } catch (JsonParseException jpe) {
//            throw jpe;
//        } catch (JsonMappingException jme) {
//            throw jme;
//        } catch (IOException ioe) {
//            throw ioe;
//        }
//    }
//}