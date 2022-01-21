package top.ydlclass.util;

import top.ydlclass.Message;
import top.ydlclass.constant.MessageType;

import java.io.*;
import java.util.Optional;

public class MsgUtils {
    public  static Optional<Message> readMessage(InputStream ips) {//读取Message

        ObjectInputStream ops = null;
        try {
            ops = new ObjectInputStream(ips);
            Message message = (Message) ops.readObject();
            return Optional.ofNullable(message);//分装成Optional外界使用的时候可以避免空指针
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
public static  void writeMessage(OutputStream ops,Message message){
    ObjectOutputStream oos= null;
    try {
        oos = new ObjectOutputStream(ops);

        oos.writeObject(message);
        oos.flush();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}
