package jingweng.demo.springboot2.websocket;

import jingweng.demo.springboot2.config.WebSocketConfig;
import jingweng.demo.springboot2.entity.Department;
import jingweng.demo.springboot2.service.DepartmentService;
import jingweng.demo.springboot2.service.impl.DepartmentServiceImpl;
import jingweng.demo.springboot2.utils.SpringContextUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.websocket
 * @className: WebSocketServer
 * @author:
 * @description: TODO
 * @date: 2023/3/14 15:17
 * @version: 1.0
 */
@Component
@Service
@ServerEndpoint(value = "/api/websocket/{sid}",configurator= WebSocketConfig.class)
public class WebSocketServer {
    private static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);
    private static int onlineCount = 0;
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet();
    private Session session;
    private String sid = "";

    public WebSocketServer() {
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        // 获取websocket会话的用户信息绑定的http会话的用户信息
        Object obj = session.getUserProperties().get("user");

        // 测试注入类,执行函数
        DepartmentService s = SpringContextUtils.getBean(DepartmentService.class);
        List<Department> d = s.getDepartments();

        this.session = session;
        webSocketSet.add(this);
        this.sid = sid;
        addOnlineCount();

        try {
            this.sendMessage("conn_success");
            log.info("有新窗口开始监听:" + sid + ",当前在线人数为:" + getOnlineCount());
        } catch (IOException var4) {
            log.error("websocket IO Exception");
        }

    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        subOnlineCount();
        log.info("释放的sid为：" + this.sid);
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自窗口" + this.sid + "的信息:" + message);
        Iterator var3 = webSocketSet.iterator();

        while(var3.hasNext()) {
            WebSocketServer item = (WebSocketServer)var3.next();

            try {
                item.sendMessage(message);
            } catch (IOException var6) {
                var6.printStackTrace();
            }
        }

    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public static void sendInfo(String message, @PathParam("sid") String sid) throws IOException {
        log.info("推送消息到窗口" + sid + "，推送内容:" + message);
        Iterator var2 = webSocketSet.iterator();

        while(var2.hasNext()) {
            WebSocketServer item = (WebSocketServer)var2.next();

            try {
                if (sid != null && item.sid.equals(sid)) {
                    item.sendMessage(message);
                }
            } catch (IOException var5) {
            }
        }

    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        ++onlineCount;
    }

    public static synchronized void subOnlineCount() {
        --onlineCount;
    }

    public static CopyOnWriteArraySet<WebSocketServer> getWebSocketSet() {
        return webSocketSet;
    }
}