package com.eagle.eir.edr.dto;

import com.eagle.eir.edr.constants.DateTimeFormats;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.net.InetAddress;
import java.time.temporal.ChronoUnit;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class IdentityCheckDto {

    private String timeStamp;
    private String imsi;
    private String imei;
    private String msisdn;
    private String status;
    private String result;
    private String value;

    // EDR Parameters
    private String tac;
    private String deviceType;
    private String appliedListName;
    private String reasonCode;
    private String protocol;
    private String timeTaken;
    private String hostname;
    private String server;
    private String originHost;
    private String sessionId;

    public void initiateValue(String str) {
        String data[] = str.split(",");

        this.timeStamp = data[0];
        this.imei = data[1];
        this.imsi = data[2];
        this.msisdn = data[3];
        this.tac = data[4];
        this.deviceType = data[5];
        this.status = data[6];
        this.appliedListName = data[7];
        this.reasonCode = data[8];
        this.protocol = data[9];
        this.timeTaken = data[10];
        this.hostname = data[11];
        this.server = data[12];
        this.sessionId = data[13];
        this.originHost = data[14];
    }

    public String toEdr() {
        StringBuilder sb = new StringBuilder();
        sb.append(timeStamp).append(",").append(this.getImei()).append(",").append(this.getImsi()).append(",").append(this.getMsisdn())
                .append(",").append(this.getTac()).append(",").append(this.getDeviceType()).append(",").append(this.getStatus())
                .append(",").append(this.getAppliedListName()).append(",").append(this.getReasonCode()).append(",").append(this.getProtocol())
                .append(",").append(timeTaken).append(",").append(this.getHostname()).append(",").append(this.getServer())
                .append(",").append(this.getSessionId()).append(",").append(this.getOriginHost());
        return sb.toString();
    }
}
