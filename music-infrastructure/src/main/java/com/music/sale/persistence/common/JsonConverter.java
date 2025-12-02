// Copyright (C) 2024 Your Name or Company
package com.music.sale.persistence.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.HashMap;
import java.util.Map;

@Converter
public class JsonConverter implements AttributeConverter<Map<String, Object>, String> {
  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public String convertToDatabaseColumn(Map<String, Object> attribute) {
    try {
      return attribute != null ? objectMapper.writeValueAsString(attribute) : null;
    } catch (Exception e) {
      System.out.println("Error serializing to JSON: " + e.getMessage());
      return null;
    }
  }

  @Override
  public Map<String, Object> convertToEntityAttribute(String dbData) {
    try {
      if (dbData == null || dbData.isBlank()) {
        return new HashMap<>();
      }

      return objectMapper.readValue(dbData, new TypeReference<HashMap<String, Object>>() {});
    } catch (Exception e) {
      System.out.println("Error deserializing JSON: " + e.getMessage() + ", data: " + dbData);
      return new HashMap<>();
    }
  }
}
