package top.ydlclass;

import com.sun.xml.internal.ws.developer.Serialization;

import java.io.Serializable;

public class Message implements Serializable {
    private Integer type;//消息类型
    private String content;//消息的内容
private  String userName;
private String passWord;
private  String friendUserName;
    public Message() {
    }

    public Message(Integer type, String content) {
        this.type = type;
        this.content = content;
    }

    public Message(Integer type, String userName, String passWord) {
        this.type = type;
        this.userName = userName;
        this.passWord = passWord;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getFriendUserName() {
        return friendUserName;
    }

    public void setFriendUserName(String friendUserName) {
        this.friendUserName = friendUserName;
    }

    @Override
    public String toString() {
        return "Message{" +
                "type=" + type +
                ", content='" + content + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", friendUserName='" + friendUserName + '\'' +
                '}';
    }
}
