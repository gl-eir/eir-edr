package com.eagle.eir.edr.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class UdpServerConfig {

    @Value("${eagle.edr.serverPort}")
    private Integer serverPort;
    

}
