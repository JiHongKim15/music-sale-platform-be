package com.music.sale.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "error")
public class ErrorProperties {

    private Map<String, ErrorDetail> codes;

    public Map<String, ErrorDetail> getCodes() {
        return codes;
    }

    public void setCodes(Map<String, ErrorDetail> codes) {
        this.codes = codes;
    }

    public static class ErrorDetail {
        private String message;
        private int status;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
