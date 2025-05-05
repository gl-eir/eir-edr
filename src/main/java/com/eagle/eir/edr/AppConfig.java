package com.eagle.eir.edr;

import com.eagle.eir.edr.dto.IdentityCheckDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Configuration
@Data
public class AppConfig {

    @Bean(name = "udpServerQueue")
    public ConcurrentLinkedQueue<String> udpServerQueue() {
        return new ConcurrentLinkedQueue<>();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
