package jingweng.demo.springboot2.network.udpmulticast;

import jingweng.demo.springboot2.network.udpclient.UdpClient;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.nio.charset.StandardCharsets;

@Slf4j
public class UdpMulticast implements Runnable {
    private final int _port;

    private MulticastSocket ms;

    public UdpMulticast(int port) {
        this._port = port;
    }

    private void connect() throws IOException {
        ms = new MulticastSocket(_port); // 监听指定端口
    }

    public void start() throws IOException {
        connect();
        new Thread(this).start();
        log.info("Udp Multicast Connection running...");
    }


    @Override
    public void run() {
        while (true) {
            // 数据缓冲区:
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            try {
                // 收取一个UDP数据包
                ms.receive(packet);
            } catch (IOException e) {
                log.info("error:" + e.getLocalizedMessage());
            }
            // 收取到的s数据存储在buffer中，由packet.getOffset(), packet.getLength()指定起始位置和长度
            // 将其按UTF-8编码转换为String:
            String s = new String(packet.getData(), packet.getOffset(), packet.getLength(), StandardCharsets.UTF_8);
            // 通过接受通道发送数据:
            byte[] data = "ACK".getBytes(StandardCharsets.UTF_8);
            packet.setData(data);
            try {
                ms.send(packet);
            } catch (IOException e) {
                log.info("error:" + e.getLocalizedMessage());
            }

            // 通过新建udp客户端连接发送数据
            try {
                UdpClient udpClient = new UdpClient();
                udpClient.connect(packet.getAddress(),packet.getPort());
                udpClient.SendData(data);
                udpClient.Disconnect();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
