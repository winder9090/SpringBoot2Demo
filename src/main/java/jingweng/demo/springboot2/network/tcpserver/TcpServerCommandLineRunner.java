package jingweng.demo.springboot2.network.tcpserver;

import jingweng.demo.springboot2.network.tcpclient.TcpClientConnection;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

//@Component
public class TcpServerCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        try {
            TcpServerConnection tcpServer = new TcpServerConnection(13002);
            tcpServer.start();
        } catch (IOException e) {
            System.out.println("IOException： " + e.getLocalizedMessage());
        }
    }
}
