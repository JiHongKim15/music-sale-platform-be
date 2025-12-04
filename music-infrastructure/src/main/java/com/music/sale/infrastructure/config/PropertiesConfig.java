package com.music.sale.infrastructure.config;

import com.music.sale.infrastructure.security.jwt.JwtProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({JwtProperties.class})
public class PropertiesConfig {}
