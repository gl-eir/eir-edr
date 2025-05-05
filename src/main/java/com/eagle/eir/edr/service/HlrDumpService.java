package com.eagle.eir.edr.service;

import com.eagle.eir.edr.config.InfoMapperUdpServerConfig;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class HlrDumpService implements IHlrDumpService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Map<String, String> hlrDumpCache = new ConcurrentHashMap<>();

    @Autowired
    InfoMapperUdpServerConfig infoMapperUdpServerConfig;

    @PostConstruct
    public void myInit() {
        if (infoMapperUdpServerConfig.getInfoMapperIsEnabled())
            loadHlrDump();
    }


    public int loadHlrDump() {
//        try {
//            String query = "select imsi , msisdn from " + infoMapperUdpServerConfig.getTableName();
//            Statement statement = jdbcTemplate.getDataSource().getConnection().createStatement();
//            ResultSet rs = statement.executeQuery(query);
//            while (rs.next()) {
//                String imsi = rs.getString("imsi");
//                String msisdn = rs.getString("msisdn");
//                hlrDumpCache.put(imsi, msisdn);
//            }
//            rs.close();
//            statement.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        logger.info("HlrDumpCache data load count : {}", hlrDumpCache.size());
        return hlrDumpCache.size();
    }

    @Override
    public String get(String imsi) {
        String msisdn = hlrDumpCache.get(imsi);
        logger.info("Search: Found in HLR Dump List : msisdn:{} imsi:{}", msisdn, imsi);
        return msisdn;

    }
}