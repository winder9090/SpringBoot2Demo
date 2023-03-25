package jingweng.demo.springboot2.network.tcpserver;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Tcp服务端
 */
@Slf4j
public class TcpServerConnection implements Runnable{
    private final int port;

    ServerSocket _socket;
    Socket sock;

    public TcpServerConnection(int port) {
        this.port = port;
    }

    private void connect() throws IOException {
        // 1、创建Socket服务端
        this._socket = new ServerSocket(port);
    }

    public void start() throws IOException {
        this.connect();
        new Thread(this).start();
    }
    @Override
    public void run() {
        while (true) {
            try {
                // 2、方法阻塞等待，直到有客户端连接
                this.sock = this._socket.accept();
            } catch (IOException e) {
                log.error(e.getLocalizedMessage());
            }
            System.out.println("connected from " + this.sock.getRemoteSocketAddress());
            TcpServerThread tcpServerHandler = new TcpServerThread(this.sock);
            new Thread(tcpServerHandler).start();
        }
    }
}
