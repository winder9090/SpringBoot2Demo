package jingweng.demo.springboot2.network.udpclient;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

@Slf4j
public class UdpClient {
    DatagramSocket ds;
    public UdpClient() {
    }

    public void connect(InetAddress Address, int port) throws IOException {
        ds = new DatagramSocket();
        ds.setSoTimeout(1000);
        ds.connect(Address, port);
    }

    public void Disconnect(){
        if(ds != null){
            ds.disconnect();
        }
    }
    public void SendData(byte[] data) {
        DatagramPacket packet = new DatagramPacket(data, data.length);
        packet.setData(data);
        try {
            ds.send(packet);
        } catch (IOException e) {
            log.info("error:" + e.getLocalizedMessage());
        }

    }
}