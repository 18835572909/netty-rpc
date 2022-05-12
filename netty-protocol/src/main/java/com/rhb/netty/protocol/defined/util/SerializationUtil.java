package com.rhb.netty.protocol.defined.util;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

/**
 * 序列化工具类
 *
 * @author renhuibo
 * @date 2022/5/12 17:12
 */
public class SerializationUtil{

  private static Map<Class<?>,Schema<?>> schemaCache = new ConcurrentHashMap<>();

  private static Objenesis objenesis = new ObjenesisStd();

  /**
   * 使用ProtoStuff序列化 （protostuff: 底层是用protocolbuffer,忽略protocolbuffer编码）
   *
   * @param message 对象信息
   * @param <T> 泛型
   * @return 序列化后的byte[]
   */
  @SuppressWarnings("unchecked")
  public static <T> byte[] serialize(T message){

    Class<T> aClass = (Class<T>)message.getClass();

    LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);

    try{
      return ProtostuffIOUtil.toByteArray(message,getSchema(aClass),buffer);
    }catch (Exception e){
      throw new IllegalStateException(e.getMessage(), e);
    }finally {
      buffer.clear();
    }
  }

  /**
   * 使用protostuff反序列化
   *
   * @param message 序列化的byte[]
   * @param cla 反序列化的目标class
   * @param <T> 泛型
   * @return 反序列化后的对象
   */
  public static <T> T deserialize(byte[] message,Class<T> cla){
    /**
     * T t = cla.newInstance();
     * 针对没有空参构造的对象 或 私有化构造的对象，无法使用JDK的方式的情况。 引入
     */
    try {
      T t = objenesis.newInstance(cla);
      ProtostuffIOUtil.mergeFrom(message, t, getSchema(cla));
      return t;
    }catch (Exception e){
      throw new IllegalStateException(e.getMessage(), e);
    }
  }

  /**
   * 获取T类型的schema
   *
   * @param cla class
   * @param <T> 具体
   * @return schema
   */
  @SuppressWarnings("unchecked")
  private static <T> Schema<T> getSchema(Class<T> cla){
    Schema<T> schema = (Schema<T>) schemaCache.get(cla);
    if(schema==null){
      schema = RuntimeSchema.createFrom(cla);
      schemaCache.put(cla,schema);
    }
    return schema;
  }

}
