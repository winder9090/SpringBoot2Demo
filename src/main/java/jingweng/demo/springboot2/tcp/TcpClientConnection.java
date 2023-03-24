package jingweng.demo.springboot2.tcp;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.tcp
 * @className: TcpClientConnection
 * @author:
 * @description: TcpClient连接启动类
 * @date: 2023/3/24 17:39
 * @version: 1.0
 */
@Slf4j
public class TcpClientConnection implements Runnable{
    private final String _Address;
    private final int _port;

    private Socket _socket;

    private ExecutorService threadPool = new ThreadPoolExecutor(2,5,
            1L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(3),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());

    public TcpClientConnection(String Address,int port) {
        this._Address = Address;
        this._port = port;
    }

    /**
     * 建立Scoket Tcp连接
     * @throws IOException
     */
    private void connect() throws IOException {
        _socket = new Socket(_Address, _port);
    }

    public void start() throws IOException {
        threadPool.execute(this);
//        new Thread(this).start();
        log.info("Tcp Client Connection running...");
    }

    @Override
    public void run() {
        while (true) {
            try {
                connect();
            } catch (IOException e) {
                log.error(e.getLocalizedMessage());
            }
            // socket 连接上才继续
            if(this._socket != null && !this._socket.isClosed()){
                // socket输入流
                try (InputStream input = this._socket.getInputStream()) {
                    // socket输出流
                    try (OutputStream output = this._socket.getOutputStream()) {
                        handle(input, output);
                    }
                } catch (IOException e) {
                    log.error(e.getLocalizedMessage());
                }
                finally {
                    try {
                        // socket连接打开，需要先关闭
                        if(this._socket != null){
                            this._socket.close();
                        }
                    } catch (IOException e) {
                        log.error(e.getLocalizedMessage());
                    }
                    // 延时3秒重连
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("disconnected.");
                }
            }
        }
    }

    private void handle(InputStream is, OutputStream os) throws IOException {
        byte[] buf = new byte[1024];
        int len = -1;
        while ((len = is.read(buf)) != -1) {
            byte[] data = new byte[len];
            System.arraycopy(buf, 0, data, 0, len);
            System.out.println("Received: " + buf.toString());
            os.write(buf);
        }
    }
}