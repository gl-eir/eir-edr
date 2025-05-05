package com.eagle.eir.edr.server;

import com.eagle.eir.edr.config.UdpServerConfig;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class UDPServer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UdpServerConfig udpServerConfig;

    private DatagramSocket datagramSocket;

    @Autowired
    @Qualifier("udpServerQueue")
    private ConcurrentLinkedQueue<String> udpServerQueue;

    @PostConstruct
    public void init() throws SocketException {
        this.datagramSocket = new DatagramSocket(udpServerConfig.getServerPort());
        logger.info("UDP server port:{} is reserved and will be listening on same", udpServerConfig.getServerPort());
    }

    public void startServer() {
        byte[] receive = new byte[2000];
        DatagramPacket DpReceive = new DatagramPacket(receive, receive.length);
        logger.info("UDP server listening on port:{} ", udpServerConfig.getServerPort());
        while (true) {
            try {
                datagramSocket.receive(DpReceive);
                String udpString = new String(receive, 0, DpReceive.getLength());
                logger.info("Edr UDP Received:{} Queue:{}", udpString, udpServerQueue.size());
                udpServerQueue.add(udpString);
            } catch (IOException e) {
                logger.error("Error in UDP Receiver Error:{}", e.getMessage(), e);
            }
        }
    }

}