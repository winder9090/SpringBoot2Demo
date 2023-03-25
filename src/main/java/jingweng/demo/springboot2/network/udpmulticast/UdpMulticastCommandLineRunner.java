package jingweng.demo.springboot2.network.udpmulticast;

import jingweng.demo.springboot2.network.udpserver.UdpServerConnection;
import org.springframework.boot.CommandLineRunner;

import java.io.IOException;

public class UdpMulticastCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        try {
            UdpMulticast udpMulticast = new UdpMulticast(13002);
            udpMulticast.start();

        } catch (IOException e) {
            System.out.println("IOException： " + e.getLocalizedMessage());
        }
    }
}
