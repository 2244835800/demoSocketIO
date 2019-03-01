package com.sockettest.demo.files;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * @Author:hepo
 * @Version:v1.0
 * @Description:
 * @Date:2019/2/20/020
 * @Time:15:51
 */
@Service("eventListenner")
public class EventListenner {
    @Resource(name = "clientCache")
    private SocketIOClientCache clientCache;

    @Resource(name = "socketIOResponse")
    private SocketIOResponse socketIOResponse;
    @Resource(name = "chatServer")
    private ChatServer chatServer;
    @OnConnect
    public void onConnect(SocketIOClient client) {
        System.out.println("建立连接");
    }

    @OnEvent("OnMSGURL")
    public void onSync(SocketIOClient client, MsgBean bean) {
        Collection<SocketIOClient> c=chatServer.getServer().getAllClients();
        for(SocketIOClient sc:c){
            System.out.println("aaaaaaaaaa"+sc.getSessionId());
        }


        System.out.printf("收到消息-from: %s to:%s\n", bean.getFrom(), bean.getTo());
        clientCache.addClient(client, bean);
        SocketIOClient ioClients = clientCache.getClient(bean.getTo());
        System.out.println("clientCache");
        if (ioClients == null) {
            System.out.println("你发送消息的用户不在线");
            return;
        }
        socketIOResponse.sendEvent(ioClients,bean);
    }

    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        System.out.println("关闭连接");
    }
}
