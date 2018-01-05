package com.ray.base.base;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * jackson 日期时间。 格式： yyyy-MM-dd HH:mm:ss
 * @author lixy
 *
 */
public class CustomDateTimeSerializer extends JsonSerializer<Date> {

    public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {   
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
            String formattedDate = formatter.format(value); 
            jgen.writeString(formattedDate);   
    } 

}
