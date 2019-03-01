package com.sockettest.demo.files;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import com.sockettest.demo.files.EventListenner;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author:hepo
 * @Version:v1.0
 * @Description:
 * @Date:2019/2/20/020
 * @Time:15:53
 */
//继承InitializingBean，使Spring加载完配置文件，自动运行如下方法
@Service("chatServer")
public class ChatServer implements InitializingBean {
    @Resource
    private EventListenner eventListenner;
    private  SocketIOServer server;
    public void afterPropertiesSet() throws Exception {
        Configuration config = new Configuration();
        config.setPort(9098);

        SocketConfig socketConfig = new SocketConfig();
        socketConfig.setReuseAddress(true);
        socketConfig.setTcpNoDelay(true);
        socketConfig.setSoLinger(0);
        config.setSocketConfig(socketConfig);
        config.setHostname("localhost");

        server = new SocketIOServer(config);
        server.addListeners(eventListenner);
        server.start();
        System.out.println("启动正常");
    }

    public SocketIOServer getServer() {
        return server;
    }

    public void setServer(SocketIOServer server) {
        this.server = server;
    }
}
