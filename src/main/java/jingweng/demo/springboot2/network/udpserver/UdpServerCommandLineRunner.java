package jingweng.demo.springboot2.network.udpserver;

import jingweng.demo.springboot2.network.tcpclient.TcpClientConnection;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

//@Component
public class UdpServerCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        try {
            UdpServerConnection udpServer = new UdpServerConnection(13002);
            udpServer.start();

        } catch (IOException e) {
            System.out.println("IOException： " + e.getLocalizedMessage());
        }
    }
}
