//package com.example.abtasdan.listviewtutorial.requests.requests;
//
//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.JavaType;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.lang.reflect.Type;
//
//import retrofit.converter.ConversionException;
//import retrofit.converter.Converter;
//import retrofit.mime.TypedByteArray;
//import retrofit.mime.TypedInput;
//import retrofit.mime.TypedOutput;
//
///**
// * Created by umutkina on 04/08/14.
// */
//public class JacksonConverter implements Converter {
//    private static final String MIME_TYPE = "application/json; charset=UTF-8";
//
//    private final ObjectMapper objectMapper;
//
//    public JacksonConverter() {
//
//
//        this(new ObjectMapper());
//    }
//
//    public JacksonConverter(ObjectMapper objectMapper) {
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        this.objectMapper = objectMapper;
//    }
//
//    @Override
//    public Object fromBody(TypedInput body, Type type) throws ConversionException {
//
////
////        BufferedReader reader = null;
////        try {
////            reader = new BufferedReader(new InputStreamReader(body.in()));
////            StringBuilder out = new StringBuilder();
////            String newLine = System.getProperty("line.separator");
////            String line;
////            while ((line = reader.readLine()) != null) {
////                out.append(line);
////                out.append(newLine);
////            }
////            System.out.println("myresponse : "+out);
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//
//        // Prints the correct String representation of body.
//
//        try {
//            JavaType javaType = objectMapper.getTypeFactory().constructType(type);
//
//
//            return objectMapper.readValue(body.in(), javaType);
//
//        } catch (JsonParseException e) {
//            throw new ConversionException(e);
//        } catch (JsonMappingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    @Override
//    public TypedOutput toBody(Object object) {
//        try {
//            String json = objectMapper.writeValueAsString(object);
//            return new TypedByteArray(MIME_TYPE, json.getBytes("UTF-8"));
//        } catch (JsonProcessingException e) {
//            throw new AssertionError(e);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}