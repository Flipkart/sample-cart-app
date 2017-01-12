package com.ekart.springbootjetty.sample.service.util;

import com.ekart.springbootjetty.sample.service.exceptions.AttributeConversionException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.Map;

/**
 * Created by sonikumari.b on 11/11/16.
 */
public class AttributeMapConvertor implements AttributeConverter<Map<String, String>, String> {

   private static final ObjectMapper MAPPER = new ObjectMapper();

   @Override
   public String convertToDatabaseColumn(Map<String, String> attribute) {

      try {
         if (attribute == null) {
            return null;
         }
         return MAPPER.writeValueAsString(attribute);
      } catch (JsonProcessingException e) {
         throw new AttributeConversionException("JsonProcessingException", e);
      }
   }

   @Override
   public Map<String, String> convertToEntityAttribute(String dbData) {
      try {
         if (dbData == null) {
            return null;
         }
         Map<String, String> attributeHash = MAPPER.readValue(dbData, Map.class);
         return attributeHash;
      } catch (IOException e) {
         throw new AttributeConversionException("IOException", e);
      }
   }
}
