package jingweng.demo.springboot2.network.tcpserver;

import lombok.extern.slf4j.Slf4j;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

@Slf4j
public class TcpServerThread implements Runnable{
    Socket sock;

    public TcpServerThread(Socket sock) {
        this.sock = sock;
    }

    @Override
    public void run() {
        // 3、输入流
        try (DataInputStream is = new DataInputStream(this.sock.getInputStream())) {
            // 3、输出流
            try (OutputStream os = this.sock.getOutputStream()) {
                handle(is, os);
            }
        } catch (Exception e) {
            try {
                // 5、资源关闭
                this.sock.close();
            } catch (IOException ioe) {
                log.error(e.getLocalizedMessage());
            } catch (Exception ex) {
                log.error(ex.getLocalizedMessage());
            }
            System.out.println("client disconnected.");
        }
    }

    private void handle(InputStream is, OutputStream os) throws IOException {
        int len;

        // 4、数据接收和响应
        int available = 0;
        while (available == 0) {
            available = is.available();
        }
        byte[] buf = new byte[available];
        while ((len = is.read(buf)) != -1) {
            // 接收数据
            byte[] data = new byte[len];

            // 响应数据
            System.arraycopy(buf, 0, data, 0, len);
            System.out.println("Received: " + buf.toString());
            os.write(buf);

        }
    }
}
