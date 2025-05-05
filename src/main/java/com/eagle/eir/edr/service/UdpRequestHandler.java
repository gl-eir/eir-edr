package com.eagle.eir.edr.service;

import com.eagle.eir.edr.config.InfoMapperUdpServerConfig;
import com.eagle.eir.edr.config.UdpServerConfig;
import com.eagle.eir.edr.constants.MsisdnFilledStatus;
import com.eagle.eir.edr.dto.IdentityCheckDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class UdpRequestHandler {

    private final Logger edrLogger = LoggerFactory.getLogger("edrFileLogger");

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private UdpServerConfig udpServerConfig;

    private ConcurrentLinkedQueue<String> udpServerQueue;

    private ObjectMapper objectMapper;

    private final String seprator = ",";

    private final int msisdnPosition = 3;

    InfoMapperUdpServerConfig infoMapperUdpServerConfig;

    @Autowired
    IHlrDumpService hlrDumpService;

    @Autowired
    public UdpRequestHandler(UdpServerConfig udpServerConfig, InfoMapperUdpServerConfig infoMapperUdpServerConfig, @Qualifier("udpServerQueue") ConcurrentLinkedQueue<String> udpServerQueue, ObjectMapper objectMapper) {
        this.udpServerConfig = udpServerConfig;
        this.udpServerQueue = udpServerQueue;
        this.objectMapper = objectMapper;
        this.infoMapperUdpServerConfig = infoMapperUdpServerConfig;
    }

    public void startConsumer() {
        logger.info("Consumer started on Queue");
        while (Boolean.TRUE) {
            try {
                String EDR = udpServerQueue.poll();
                if (EDR == null) {
                    continue;
                }
                if (infoMapperUdpServerConfig.getInfoMapperIsEnabled()) {
                    IdentityCheckDto identityCheckDto = new IdentityCheckDto();
                    identityCheckDto.initiateValue(EDR);
                    if (StringUtils.hasText(identityCheckDto.getMsisdn())) {
                        edrLogger.info(EDR);
                    } else {
                        String msisdn = hlrDumpService.get(identityCheckDto.getImsi());
                        if (msisdn == null) {
                            identityCheckDto.setMsisdn("");
                            identityCheckDto.setAppliedListName(MsisdnFilledStatus.NOT_FOUND_IN_HLR.getCode() + "");
                        } else {
                            identityCheckDto.setMsisdn(msisdn);
                            identityCheckDto.setAppliedListName(MsisdnFilledStatus.FILLED_FROM_HLR.getCode() + "");
                        }
                        edrLogger.info(identityCheckDto.toEdr());
                    }
                } else {
                    edrLogger.info(EDR);
                }
            } catch (Exception e) {
                logger.error("Exception ", e);
            }
        }
    }
}
