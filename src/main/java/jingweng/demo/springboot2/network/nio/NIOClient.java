package jingweng.demo.springboot2.network.nio;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

//@Component
@Slf4j
public class NIOClient {

    @Value("${nioClient.host}")
    private InetAddress addr;

    @Value("${nioClient.port}")
    private int port;

    @PostConstruct
    public void start() throws IOException, InterruptedException {
        InetSocketAddress socketAddress = new InetSocketAddress(this.addr, this.port);
        new Thread(new NioClientThread(socketAddress)).start();
    }
}

@Slf4j
class NioClientThread extends Thread {
    InetSocketAddress socketAddress;

    private static int BUFF_SIZE = 1024;

    public NioClientThread(InetSocketAddress socketAddress) {
        this.socketAddress = socketAddress;
    }

    @SneakyThrows
    @Override
    public void run() {
        boolean isOK = false;
        SocketChannel socketChannel = null;

        while (!isOK){
            try{
                socketChannel = SocketChannel.open(socketAddress);
                isOK = true;
            }
            catch (IOException e){
                isOK = false;
            }
        }

        log.info("连接 NIOServer 服务，端口：10002...");

        ArrayList<String> companyDetails = new ArrayList<>();

        // 创建消息列表
        companyDetails.add("腾讯");
        companyDetails.add("阿里巴巴");
        companyDetails.add("京东");
        companyDetails.add("百度");
        companyDetails.add("google");

        for (String companyName : companyDetails) {
            socketChannel.write(ByteBuffer.wrap(companyName.getBytes()));
            log.info("发送: " + companyName);

            ByteBuffer buffer = ByteBuffer.allocate(BUFF_SIZE);
            buffer.clear();
            socketChannel.read(buffer);
            String result = new String(buffer.array()).trim();
            log.info("收到NIOServer回复的消息：" + result);

            // 等待2秒钟再发送下一条消息
            Thread.sleep(2000);
        }

        socketChannel.close();
    }
}
