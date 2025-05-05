package com.eagle.eir.edr.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class InfoMapperUdpServerConfig {

    @Value("${info-mapper.enable}")
    private Boolean infoMapperIsEnabled;

}
