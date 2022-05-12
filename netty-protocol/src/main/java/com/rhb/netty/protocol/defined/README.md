**自定义RPC通讯协议**

1. 协议包格式
    ```
    data_length::int + commond::byte + data::byte[] 
    ```
2. 序列化方式
   ```
   google对protocol buffer包装后提供的工具类ProtoSuffIOUtil
   ``` 