package com.rhb.netty.protocol.proxy;

import com.rhb.netty.exception.NeException;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/5/12 15:47
 */
public class HttpClient {

  public static Response post(OkHttpClient okHttpClient,String url,String body)
      throws IOException, NeException {
    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),body);

    Request request = new Request.Builder()
        .url(url)
        .post(requestBody)
        .build();

    Call call = okHttpClient.newCall(request);
    Response response = call.execute();

    if(!response.isSuccessful()){
      throw new NeException(response.message());
    }

    return response;
  }

}
