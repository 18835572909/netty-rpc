# netty-rpc
手写netty的rpc通讯

## 手写Http代理类
使用JDK-Proxy完成动态代理，实现伪rpc通讯（完成）

## 添加rpc支持
1. protocol buffer 3 使用样例

2. thrift协议使用样例(已完)

3. Netty之自定义协议通讯(通讯和心跳)
    1. 客户端断线重连(已完)
    2. 服务端定时清理(已完)
    3. 心跳机制(已完)

4. Netty处理拆包、粘包(已完)
   1. Netty通讯协议之自定义分割符-粘包问题
   2. Netty通讯协议之自定义长度-粘包问题
   
5. Nettt之序列化处理(已完)
   1. JDK
   2. JBoss Marshalling
   3. Protocol Buffer

7. Netty之网络通讯
   1. HttpServer搭建(已完)
   2. TCP通讯
   3. UDP通讯
   4. Netty之WebSocket通讯

## Netty之ByteBuf-API样例
1. Unpooled
2. ByteBufUtil ... 
