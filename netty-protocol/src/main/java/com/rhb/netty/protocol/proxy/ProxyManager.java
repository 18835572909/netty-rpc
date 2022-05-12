package com.rhb.netty.protocol.proxy;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.rhb.netty.exception.NeException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import okhttp3.OkHttpClient;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/5/12 14:46
 */
public class ProxyManager<T> {

  private Class<T> cla;

  private String url;

  private OkHttpClient okHttpClient;

  public ProxyManager(Class<T> cla,String url,OkHttpClient okHttpClient){
    this.cla = cla;
    this.url = url;
    this.okHttpClient = okHttpClient;
  }

  @SuppressWarnings("unchecked")
  public T newInstance(){
    return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),cla.getInterfaces(),new HttpInvocationHandler());
  }

  private class HttpInvocationHandler implements InvocationHandler{

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

      String remoteUri = url + "/"+method.getName();

      /**
       * 针对Post+单个参数 (hutool-json)
       */
      if(args.length == 1){
        String body = JSONUtil.toJsonStr(args[0]);
        return HttpClient.post(okHttpClient,remoteUri,body);
      }

      /**
       * 针对Post+单个参数（Java reflect）
       */
      if(args.length==1){
        Class<?> parameterType = method.getParameterTypes()[0];
        Field[] fields = parameterType.getFields();

        JSONObject requestBody = new JSONObject();
        for (Field field : fields){
          field.setAccessible(true);
          String fieldName = field.getName();
          Object fieldValue = field.get(args[0]);
          requestBody.putOnce(fieldName,fieldValue);
        }

        return HttpClient.post(okHttpClient,remoteUri,requestBody.toString());
      }

      throw new NeException("暂不支持");
    }

  }

}
