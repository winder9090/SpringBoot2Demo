package jingweng.demo.springboot2.network.tcpclient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.tcp
 * @className: TcpClientCommandLineRunner
 * @author:
 * @description: TcpClient启动类
 * @date: 2023/3/24 17:30
 * @version: 1.0
 */
@Component
public class TcpClientCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
		try {
			TcpClientConnection tcpClient = new TcpClientConnection("10.10.10.82",13002);
			tcpClient.start();

		} catch (IOException e) {
			System.out.println("IOException： " + e.getLocalizedMessage());
		}
    }
}
