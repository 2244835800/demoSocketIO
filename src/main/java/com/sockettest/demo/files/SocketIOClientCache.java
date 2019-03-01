package com.sockettest.demo.files;

import com.corundumstudio.socketio.SocketIOClient;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author:hepo
 * @Version:v1.0
 * @Description:
 * @Date:2019/2/20/020
 * @Time:15:53
 */
@Service("clientCache")
public class SocketIOClientCache {
    //String：EventType类型
    private Map<String,SocketIOClient> clients=new ConcurrentHashMap<String,SocketIOClient>();

    //用户发送消息添加
    public void addClient(SocketIOClient client,MsgBean msgBean){
        clients.put(msgBean.getFrom(),client);
    }

    //用户退出时移除
    public void remove(MsgBean msgBean) {
        clients.remove(msgBean.getFrom());
    }

    //获取所有
    public  SocketIOClient getClient(String to) {
        return clients.get(to);
    }
}