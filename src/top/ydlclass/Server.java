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

    @SuppressWarnings("all")
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket=new ServerSocket();
        serverSocket.bind(new InetSocketAddress(8888));
        System.out.println("服务端已经在端口：8888 上启动");
        while(true){
        Socket socket=serverSocket.accept();
        new ServerThread(socket).start();
        }

}


}
