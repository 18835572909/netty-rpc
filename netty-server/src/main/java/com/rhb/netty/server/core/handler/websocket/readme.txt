
1. WebSocket原理
答： 本质是对http请求进行的升级握手（Upgrade handshake）

2. Http升级WebSocket后Handler执行器链的改变

connect before：
HttpServerCodec(HttpRequestDecoder\HttpRequestEncoder)、ChunkedWriteHandler、HttpObjectAggregator、HttpRequestHandler、WebSocketServerProtocolHandler、TextWebSocketFrameHandler

connect after:
WebSocketFrameDecoder、WebSocketFrameEncoder、WebSocketServerProtocolHandler、TextWebSocketFrameHandler

3. 文件传输
  a. 文件写入的handler一定是要的ChunkedWriteHandler
  b. http协议使用zero-copy的 DefaultFileRegion
  c. ssl支持的不支持zero-copy，使用ChunkedNioFile

4. 理解chanel.retain()方法\FullHttpRequest.replace()方法