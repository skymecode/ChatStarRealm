package top.ydlclass;

import com.sun.javafx.collections.MappingChange;
import top.ydlclass.constant.MessageType;
import top.ydlclass.util.MsgUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Optional;

public class Server {
    public final static HashMap<String,Socket> USER_MAP=new HashMap<>();
    @SuppressWarnings("all")
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket=new ServerSocket();
        serverSocket.bind(new InetSocketAddress(8888));
        System.out.println("服务器已经启动");
        while(true){
        Socket socket=serverSocket.accept();//等待连接
        InputStream inputStream=socket.getInputStream();
            OutputStream outputStream=socket.getOutputStream();
        Optional<Message> message=MsgUtils.readMessage(inputStream);
        if(!message.isPresent()||message.get().getUserName()==null||!message.get().getPassWord().equals("123")){
            System.out.println("系统发现用户登录失败");
            MsgUtils.writeMessage(outputStream,new Message(MessageType.FROM_SERVER,"登录失败"));
            continue;
        }
        else{
            System.out.println(message.get().getUserName()+"登录成功");
            USER_MAP.put(message.get().getUserName(),socket);
            MsgUtils.writeMessage(outputStream,new Message(MessageType.FROM_SERVER,"登录成功"));
        }

    }
}}
