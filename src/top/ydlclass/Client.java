package top.ydlclass;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import top.ydlclass.constant.MessageType;
import top.ydlclass.constant.ServerConstant;
import top.ydlclass.util.MsgUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Optional;
import java.util.Scanner;

/*功能 给服务器发消息，给好友发消息，群发消息

*/
@SuppressWarnings("all")
public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket=new Socket();
        socket.connect(new InetSocketAddress(8888));//连接
        System.out.println("客户端已连上服务器");
        //发消息
        OutputStream ops=socket.getOutputStream();
        InputStream ips=socket.getInputStream();
String name=null;
        while(true){

            while (name==null){
                System.out.println("请您登录你的账号");

               name= login(ops, ips);
            }
            Scanner scanner=new Scanner(System.in);
                printOder();
                String msgType=scanner.next();
                switch (Integer.parseInt(msgType)){
                    case MessageType.TO_SERVER:
sendToServer(ops,ips,name);
                        break;
                    case MessageType.TO_FRIEND:
sendToFriend(ops,name);
                        break;
                    case MessageType.TO_ALL:

                        break;
                    case MessageType.FROM_FRIEND:
                        fromFriend(ips);
                        break;
                    default:
                        break;
                }
            }
        }



    private static void fromFriend(InputStream inputStream) {
while (true){
    Scanner scanner=new Scanner(System.in);
    Message message=MsgUtils.readMessage(inputStream);
    System.out.println("用户ID:"+message.getUserName()+":"+message.getContent());
    System.out.println("是否继续接收？退出请输入NO");
    String loop =scanner.next();
    if("NO".equals(loop)){
        break;
    }
}
    }

    private static void sendToFriend(OutputStream outputStream ,String name) {
        Scanner scanner=new Scanner(System.in);

        Message message=new Message(MessageType.TO_FRIEND);
        message.setUserName(name);
        System.out.println("请输入发送消息的名字");
String friendName=scanner.next();
message.setFriendUserName(friendName);
        System.out.println("请输入发送内容,按Enter发送");

        String content=scanner.next();
        message.setContent(content);
        MsgUtils.writeMessage(outputStream,message);

    }

    private static void sendToServer(OutputStream ops,InputStream ips,String name){
        Scanner scanner=new Scanner(System.in);
        System.out.print(name+":");
        String msgCont=scanner.next();
        MsgUtils.writeMessage(ops,new Message(MessageType.TO_SERVER,msgCont));
        Message message=MsgUtils.readMessage(ips);
        System.out.println(message.getContent());

    }

    private static String login(OutputStream ops, InputStream ips) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入用户名:");
       String name=scanner.next();
        System.out.println("(默认密码为123) 请输入密码:");
        String pwd=scanner.next();
        MsgUtils.writeMessage(ops,new Message(MessageType.LOGIN,name,pwd));
        Message message=MsgUtils.readMessage(ips);
        if("登录成功".equals(message.getContent())){
            System.out.println(message.getContent());
                return name;}
        else{
            return null;}
    }
    private static void printOder(){
        System.out.println("请选择功能: 1.向服务器发消息;2.私聊;3.群发;6.接收消息");
    }

}
