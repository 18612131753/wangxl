package com.ray.power.base;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * JSON转换的时候，用于日期格式的转换
 * */
public class CustomDateSerializer extends JsonSerializer<Date> {   
    @Override  
    public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {   
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");   
            String formattedDate = formatter.format(value);   
            jgen.writeString(formattedDate);   
    }   
}  