package com.rhb.netty.server.core.handler.udp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import lombok.SneakyThrows;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2022/6/17 15:56
 */
public class ApiDemo {

  static RandomAccessFile file ;

  static {
    try {
      file = new RandomAccessFile(new File("E:\\work\\project\\netty-rpc\\netty-server\\src\\main\\java\\com\\rhb\\netty\\server\\core\\handler/udp/1.txt"),"rw");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
   * r:以只读的模式打开，如果调用write方法将会抛出IO异常
   * rw:以读和写的模式打开
   * rws:以读和写的模式打开，要求对”文件的内容“和”元数据“的每个更新都同步到存储设备
   * rwd:以读和写的模式打开，要求对”文件的内容“的每个更新都同步到存储设备
   */
  public static void file() throws Exception {
    file.write("hello".getBytes());
    file.close();
  }

  @SneakyThrows
  public static void seek(){
    file.seek(10);
    long length = file.length();
    System.out.println("file.length:"+length);

    //文件当前偏移量
    long filePointer = file.getFilePointer();
    System.out.println("file.pointer:"+filePointer);

    for (int i=0;i<11;i++){
      System.out.println(file.read());
    }
  }

  public static void main(String[] args) throws Exception {
//    file();
    seek();
  }

}
