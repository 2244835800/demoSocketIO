package com.sockettest.demo.files;

import com.corundumstudio.socketio.SocketIOClient;
import org.springframework.stereotype.Service;

/**
 * @Author:hepo
 * @Version:v1.0
 * @Description:
 * @Date:2019/2/20/020
 * @Time:15:53
 */
@Service("socketIOResponse")
public class SocketIOResponse {
    public void sendEvent(SocketIOClient client, MsgBean bean) {
        System.out.println("推送消息");
        client.sendEvent("OnMSG", bean);
    }
}