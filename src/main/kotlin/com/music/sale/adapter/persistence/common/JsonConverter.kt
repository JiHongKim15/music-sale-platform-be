// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.persistence.common

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class JsonConverter : AttributeConverter<Map<String, Any>, String> {
    private val objectMapper = ObjectMapper()

    override fun convertToDatabaseColumn(attribute: Map<String, Any>?): String? {
        return try {
            attribute?.let { objectMapper.writeValueAsString(it) }
        } catch (e: Exception) {
            println("Error serializing to JSON: ${e.message}")
            null
        }
    }

    override fun convertToEntityAttribute(dbData: String?): Map<String, Any>? {
        return try {
            if (dbData.isNullOrBlank()) {
                return emptyMap()
            }
            
            objectMapper.readValue(dbData, object : TypeReference<HashMap<String, Any>>() {})
        } catch (e: Exception) {
            println("Error deserializing JSON: ${e.message}, data: $dbData")
            // JSON 파싱 실패 시 빈 맵 반환
            emptyMap()
        }
    }
}
