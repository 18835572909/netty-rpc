namespace java com.rhb.netty.protocol.thrift

struct RequestThrift{
  1: i64 requestId;
  2: string msg;
  3: i32 type;
}

exception RequestException{
  1: required i32 code;
  2: optional string reason;
}

// thrift -gen java BaseRequestThrift.thrift

service LoginService{
  string doLogin(1:RequestThrift request) throws (1:RequestException mrge);
}
