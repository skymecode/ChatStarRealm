# ChatStarRealm
这是一个基于Java的简单聊天室（博客：http://skymee.top/）

由于本人还未接触JDBC所以暂时只能使用HashMap的存储方式进行存储，这只是最初的模型版本，没有实现存储功能
即不能检验登录，也不能判断账号是否已经存在，所以现在是伪登录.
具体实现：
登录实现就是客户端向服务端发送消息，服务端进行验证消息然后反馈登录是否成功的消息给客户端，然后客户端才能进行下一步功能
![787 ` GH){7MWV59O{HHAU2](https://user-images.githubusercontent.com/92422899/150634484-71362aa8-c2df-4891-a42a-135195629d50.png)
![BP_G_VS04JN%07Q2D9YJ{GB](https://user-images.githubusercontent.com/92422899/150634493-f7aa9083-2d1e-4afc-9ed4-4f6229a764fb.png)
