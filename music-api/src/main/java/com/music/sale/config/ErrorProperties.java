package com.music.sale.config;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "error")
@AllArgsConstructor
public class ErrorProperties {

  private final Map<String, ErrorDetail> codes;

  public record ErrorDetail(String message, int status) {}
}
