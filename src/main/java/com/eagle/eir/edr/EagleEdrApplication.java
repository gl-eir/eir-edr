package com.eagle.eir.edr;

import com.eagle.eir.edr.server.UDPServer;
import com.eagle.eir.edr.service.UdpRequestHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class EagleEdrApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(EagleEdrApplication.class, args);
        new Thread(() -> context.getBean(UdpRequestHandler.class).startConsumer()).start();
        new Thread(() -> context.getBean(UDPServer.class).startServer()).start();


    }

}
