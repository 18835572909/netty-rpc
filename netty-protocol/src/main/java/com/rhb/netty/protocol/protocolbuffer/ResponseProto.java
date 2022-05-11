// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: BaseResponseProtocol.proto

package com.rhb.netty.protocol.protocolbuffer;

public final class ResponseProto {
  private ResponseProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface ResponseProtocolOrBuilder extends
      // @@protoc_insertion_point(interface_extends:ResponseProtocol)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>int64 responseId = 1;</code>
     * @return The responseId.
     */
    long getResponseId();

    /**
     * <code>string msg = 2;</code>
     * @return The msg.
     */
    java.lang.String getMsg();
    /**
     * <code>string msg = 2;</code>
     * @return The bytes for msg.
     */
    com.google.protobuf.ByteString
        getMsgBytes();

    /**
     * <code>int32 type = 3;</code>
     * @return The type.
     */
    int getType();
  }
  /**
   * Protobuf type {@code ResponseProtocol}
   */
  public static final class ResponseProtocol extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:ResponseProtocol)
      ResponseProtocolOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use ResponseProtocol.newBuilder() to construct.
    private ResponseProtocol(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private ResponseProtocol() {
      msg_ = "";
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new ResponseProtocol();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private ResponseProtocol(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 8: {

              responseId_ = input.readInt64();
              break;
            }
            case 18: {
              java.lang.String s = input.readStringRequireUtf8();

              msg_ = s;
              break;
            }
            case 24: {

              type_ = input.readInt32();
              break;
            }
            default: {
              if (!parseUnknownField(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.rhb.netty.protocol.protocolbuffer.ResponseProto.internal_static_ResponseProtocol_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.rhb.netty.protocol.protocolbuffer.ResponseProto.internal_static_ResponseProtocol_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.rhb.netty.protocol.protocolbuffer.ResponseProto.ResponseProtocol.class, com.rhb.netty.protocol.protocolbuffer.ResponseProto.ResponseProtocol.Builder.class);
    }

    public static final int RESPONSEID_FIELD_NUMBER = 1;
    private long responseId_;
    /**
     * <code>int64 responseId = 1;</code>
     * @return The responseId.
     */
    @java.lang.Override
    public long getResponseId() {
      return responseId_;
    }

    public static final int MSG_FIELD_NUMBER = 2;
    private volatile java.lang.Object msg_;
    /**
     * <code>string msg = 2;</code>
     * @return The msg.
     */
    @java.lang.Override
    public java.lang.String getMsg() {
      java.lang.Object ref = msg_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        msg_ = s;
        return s;
      }
    }
    /**
     * <code>string msg = 2;</code>
     * @return The bytes for msg.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getMsgBytes() {
      java.lang.Object ref = msg_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        msg_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int TYPE_FIELD_NUMBER = 3;
    private int type_;
    /**
     * <code>int32 type = 3;</code>
     * @return The type.
     */
    @java.lang.Override
    public int getType() {
      return type_;
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (responseId_ != 0L) {
        output.writeInt64(1, responseId_);
      }
      if (!getMsgBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, msg_);
      }
      if (type_ != 0) {
        output.writeInt32(3, type_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (responseId_ != 0L) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(1, responseId_);
      }
      if (!getMsgBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, msg_);
      }
      if (type_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, type_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.rhb.netty.protocol.protocolbuffer.ResponseProto.ResponseProtocol)) {
        return super.equals(obj);
      }
      com.rhb.netty.protocol.protocolbuffer.ResponseProto.ResponseProtocol other = (com.rhb.netty.protocol.protocolbuffer.ResponseProto.ResponseProtocol) obj;

      if (getResponseId()
          != other.getResponseId()) return false;
      if (!getMsg()
          .equals(other.getMsg())) return false;
      if (getType()
          != other.getType()) return false;
      if (!unknownFields.equals(other.unknownFields)) return false;
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + RESPONSEID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getResponseId());
      hash = (37 * hash) + MSG_FIELD_NUMBER;
      hash = (53 * hash) + getMsg().hashCode();
      hash = (37 * hash) + TYPE_FIELD_NUMBER;
      hash = (53 * hash) + getType();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.rhb.netty.protocol.protocolbuffer.ResponseProto.ResponseProtocol parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.rhb.netty.protocol.protocolbuffer.ResponseProto.ResponseProtocol parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.rhb.netty.protocol.protocolbuffer.ResponseProto.ResponseProtocol parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.rhb.netty.protocol.protocolbuffer.ResponseProto.ResponseProtocol parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.rhb.netty.protocol.protocolbuffer.ResponseProto.ResponseProtocol parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.rhb.netty.protocol.protocolbuffer.ResponseProto.ResponseProtocol parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.rhb.netty.protocol.protocolbuffer.ResponseProto.ResponseProtocol parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.rhb.netty.protocol.protocolbuffer.ResponseProto.ResponseProtocol parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.rhb.netty.protocol.protocolbuffer.ResponseProto.ResponseProtocol parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.rhb.netty.protocol.protocolbuffer.ResponseProto.ResponseProtocol parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.rhb.netty.protocol.protocolbuffer.ResponseProto.ResponseProtocol parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.rhb.netty.protocol.protocolbuffer.ResponseProto.ResponseProtocol parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.rhb.netty.protocol.protocolbuffer.ResponseProto.ResponseProtocol prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code ResponseProtocol}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:ResponseProtocol)
        com.rhb.netty.protocol.protocolbuffer.ResponseProto.ResponseProtocolOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.rhb.netty.protocol.protocolbuffer.ResponseProto.internal_static_ResponseProtocol_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.rhb.netty.protocol.protocolbuffer.ResponseProto.internal_static_ResponseProtocol_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.rhb.netty.protocol.protocolbuffer.ResponseProto.ResponseProtocol.class, com.rhb.netty.protocol.protocolbuffer.ResponseProto.ResponseProtocol.Builder.class);
      }

      // Construct using com.rhb.netty.protocol.protocolbuffer.ResponseProto.ResponseProtocol.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        responseId_ = 0L;

        msg_ = "";

        type_ = 0;

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.rhb.netty.protocol.protocolbuffer.ResponseProto.internal_static_ResponseProtocol_descriptor;
      }

      @java.lang.Override
      public com.rhb.netty.protocol.protocolbuffer.ResponseProto.ResponseProtocol getDefaultInstanceForType() {
        return com.rhb.netty.protocol.protocolbuffer.ResponseProto.ResponseProtocol.getDefaultInstance();
      }

      @java.lang.Override
      public com.rhb.netty.protocol.protocolbuffer.ResponseProto.ResponseProtocol build() {
        com.rhb.netty.protocol.protocolbuffer.ResponseProto.ResponseProtocol result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.rhb.netty.protocol.protocolbuffer.ResponseProto.ResponseProtocol buildPartial() {
        com.rhb.netty.protocol.protocolbuffer.ResponseProto.ResponseProtocol result = new com.rhb.netty.protocol.protocolbuffer.ResponseProto.ResponseProtocol(this);
        result.responseId_ = responseId_;
        result.msg_ = msg_;
        result.type_ = type_;
        onBuilt();
        return result;
      }

      @java.lang.Override
      public Builder clone() {
        return super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.rhb.netty.protocol.protocolbuffer.ResponseProto.ResponseProtocol) {
          return mergeFrom((com.rhb.netty.protocol.protocolbuffer.ResponseProto.ResponseProtocol)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.rhb.netty.protocol.protocolbuffer.ResponseProto.ResponseProtocol other) {
        if (other == com.rhb.netty.protocol.protocolbuffer.ResponseProto.ResponseProtocol.getDefaultInstance()) return this;
        if (other.getResponseId() != 0L) {
          setResponseId(other.getResponseId());
        }
        if (!other.getMsg().isEmpty()) {
          msg_ = other.msg_;
          onChanged();
        }
        if (other.getType() != 0) {
          setType(other.getType());
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.rhb.netty.protocol.protocolbuffer.ResponseProto.ResponseProtocol parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.rhb.netty.protocol.protocolbuffer.ResponseProto.ResponseProtocol) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private long responseId_ ;
      /**
       * <code>int64 responseId = 1;</code>
       * @return The responseId.
       */
      @java.lang.Override
      public long getResponseId() {
        return responseId_;
      }
      /**
       * <code>int64 responseId = 1;</code>
       * @param value The responseId to set.
       * @return This builder for chaining.
       */
      public Builder setResponseId(long value) {
        
        responseId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int64 responseId = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearResponseId() {
        
        responseId_ = 0L;
        onChanged();
        return this;
      }

      private java.lang.Object msg_ = "";
      /**
       * <code>string msg = 2;</code>
       * @return The msg.
       */
      @Override
      public java.lang.String getMsg() {
        java.lang.Object ref = msg_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          msg_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string msg = 2;</code>
       * @return The bytes for msg.
       */
      @Override
      public com.google.protobuf.ByteString
          getMsgBytes() {
        java.lang.Object ref = msg_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          msg_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string msg = 2;</code>
       * @param value The msg to set.
       * @return This builder for chaining.
       */
      public Builder setMsg(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        msg_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string msg = 2;</code>
       * @return This builder for chaining.
       */
      public Builder clearMsg() {
        
        msg_ = getDefaultInstance().getMsg();
        onChanged();
        return this;
      }
      /**
       * <code>string msg = 2;</code>
       * @param value The bytes for msg to set.
       * @return This builder for chaining.
       */
      public Builder setMsgBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        msg_ = value;
        onChanged();
        return this;
      }

      private int type_ ;
      /**
       * <code>int32 type = 3;</code>
       * @return The type.
       */
      @java.lang.Override
      public int getType() {
        return type_;
      }
      /**
       * <code>int32 type = 3;</code>
       * @param value The type to set.
       * @return This builder for chaining.
       */
      public Builder setType(int value) {
        
        type_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 type = 3;</code>
       * @return This builder for chaining.
       */
      public Builder clearType() {
        
        type_ = 0;
        onChanged();
        return this;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:ResponseProtocol)
    }

    // @@protoc_insertion_point(class_scope:ResponseProtocol)
    private static final com.rhb.netty.protocol.protocolbuffer.ResponseProto.ResponseProtocol DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.rhb.netty.protocol.protocolbuffer.ResponseProto.ResponseProtocol();
    }

    public static com.rhb.netty.protocol.protocolbuffer.ResponseProto.ResponseProtocol getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ResponseProtocol>
        PARSER = new com.google.protobuf.AbstractParser<ResponseProtocol>() {
      @java.lang.Override
      public ResponseProtocol parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new ResponseProtocol(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<ResponseProtocol> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ResponseProtocol> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.rhb.netty.protocol.protocolbuffer.ResponseProto.ResponseProtocol getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ResponseProtocol_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ResponseProtocol_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\032BaseResponseProtocol.proto\"A\n\020Response" +
      "Protocol\022\022\n\nresponseId\030\001 \001(\003\022\013\n\003msg\030\002 \001(" +
      "\t\022\014\n\004type\030\003 \001(\005B6\n%com.rhb.netty.protoco" +
      "l.protocolbufferB\rResponseProtob\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_ResponseProtocol_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_ResponseProtocol_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ResponseProtocol_descriptor,
        new java.lang.String[] { "ResponseId", "Msg", "Type", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
