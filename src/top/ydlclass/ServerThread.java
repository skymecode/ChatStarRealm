package top.ydlclass;

import top.ydlclass.constant.MessageType;
import top.ydlclass.constant.ServerConstant;
import top.ydlclass.util.MsgUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Optional;

public class ServerThread extends Thread{
    private Socket socket;

    public ServerThread() {
    }

    public ServerThread(Socket socket){
    this.socket=socket;
}
    @Override
    public void run() {
        while(true){
try {


    InputStream inputStream = socket.getInputStream();
    OutputStream outputStream = socket.getOutputStream();
            Message message = MsgUtils.readMessage(inputStream);
            if(message!=null){
                switch (message.getType()){
                    case MessageType.LOGIN:
                        login(socket, outputStream, message);
                        break;
                    case MessageType.TO_SERVER:
                        toClient(outputStream);
                        break;
                    case MessageType.TO_FRIEND:
                        toFriend(message);
                        break;
                    case MessageType.TO_ALL:
                    default:
                        break;
                }       }  } catch (IOException e) {
            e.printStackTrace();
        }



                }


        }


    private void toFriend(Message message) throws IOException {
        Socket socket=ServerConstant.USER_MAP.get(message.getFriendUserName());
            MsgUtils.writeMessage(socket.getOutputStream(),message);
    }



    private static void toClient(OutputStream outputStream) {
        MsgUtils.writeMessage(outputStream,new Message(MessageType.FROM_SERVER,"ok"));
    }

    private static void login(Socket socket, OutputStream outputStream, Message message) {
        if (message.getUserName() == null || !message.getPassWord().equals("123")) {
            System.out.println("系统发现用户登录失败");
            MsgUtils.writeMessage(outputStream, new Message(MessageType.FROM_SERVER, "登录失败"));

        } else {
            System.out.println(message.getUserName() + "在地址:"+socket.getInetAddress()+"端口:"+socket.getPort()+"上连接到服务器");
            ServerConstant.USER_MAP.put(message.getUserName(), socket);
            MsgUtils.writeMessage(outputStream, new Message(MessageType.FROM_SERVER, "登录成功"));
        }
    }

}
