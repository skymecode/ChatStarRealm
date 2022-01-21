package top.ydlclass;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import top.ydlclass.constant.MessageType;
import top.ydlclass.util.MsgUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Optional;

/*功能 给服务器发消息，给好友发消息，群发消息

*/
@SuppressWarnings("all")
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket=new Socket();
        socket.connect(new InetSocketAddress(8888));//连接
        System.out.println("客户端已连接");
        //发消息
        OutputStream ops=socket.getOutputStream();
        InputStream ips=socket.getInputStream();
        MsgUtils.writeMessage(ops,new Message(MessageType.LOGIN,"skyme","1232"));
        Optional<Message> message=MsgUtils.readMessage(ips);
        System.out.println(message);
        socket.close();
        ips.close();
ops.close();
    }

}
