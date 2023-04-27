package org.example.jeu;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: jeu.proto")
public final class JeuGrpc {

  private JeuGrpc() {}

  public static final String SERVICE_NAME = "jeu.Jeu";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<JeuOuterClass.GuessRequest,
          JeuOuterClass.Response> getGuessMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Guess",
      requestType = JeuOuterClass.GuessRequest.class,
      responseType = JeuOuterClass.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<JeuOuterClass.GuessRequest,
          JeuOuterClass.Response> getGuessMethod() {
    io.grpc.MethodDescriptor<JeuOuterClass.GuessRequest, JeuOuterClass.Response> getGuessMethod;
    if ((getGuessMethod = JeuGrpc.getGuessMethod) == null) {
      synchronized (JeuGrpc.class) {
        if ((getGuessMethod = JeuGrpc.getGuessMethod) == null) {
          JeuGrpc.getGuessMethod = getGuessMethod = 
              io.grpc.MethodDescriptor.<JeuOuterClass.GuessRequest, JeuOuterClass.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "jeu.Jeu", "Guess"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  JeuOuterClass.GuessRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  JeuOuterClass.Response.getDefaultInstance()))
                  .setSchemaDescriptor(new JeuMethodDescriptorSupplier("Guess"))
                  .build();
          }
        }
     }
     return getGuessMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static JeuStub newStub(io.grpc.Channel channel) {
    return new JeuStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static JeuBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new JeuBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static JeuFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new JeuFutureStub(channel);
  }

  /**
   */
  public static abstract class JeuImplBase implements io.grpc.BindableService {

    /**
     */
    public void guess(JeuOuterClass.GuessRequest request,
                      io.grpc.stub.StreamObserver<JeuOuterClass.Response> responseObserver) {
      asyncUnimplementedUnaryCall(getGuessMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGuessMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                      JeuOuterClass.GuessRequest,
                      JeuOuterClass.Response>(
                  this, METHODID_GUESS)))
          .build();
    }
  }

  /**
   */
  public static final class JeuStub extends io.grpc.stub.AbstractStub<JeuStub> {
    private JeuStub(io.grpc.Channel channel) {
      super(channel);
    }

    private JeuStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected JeuStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new JeuStub(channel, callOptions);
    }

    /**
     */
    public void guess(JeuOuterClass.GuessRequest request,
                      io.grpc.stub.StreamObserver<JeuOuterClass.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGuessMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class JeuBlockingStub extends io.grpc.stub.AbstractStub<JeuBlockingStub> {
    private JeuBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private JeuBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected JeuBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new JeuBlockingStub(channel, callOptions);
    }

    /**
     */
    public JeuOuterClass.Response guess(JeuOuterClass.GuessRequest request) {
      return blockingUnaryCall(
          getChannel(), getGuessMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class JeuFutureStub extends io.grpc.stub.AbstractStub<JeuFutureStub> {
    private JeuFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private JeuFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected JeuFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new JeuFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<JeuOuterClass.Response> guess(
        JeuOuterClass.GuessRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGuessMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GUESS = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final JeuImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(JeuImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GUESS:
          serviceImpl.guess((JeuOuterClass.GuessRequest) request,
              (io.grpc.stub.StreamObserver<JeuOuterClass.Response>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class JeuBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    JeuBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return JeuOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Jeu");
    }
  }

  private static final class JeuFileDescriptorSupplier
      extends JeuBaseDescriptorSupplier {
    JeuFileDescriptorSupplier() {}
  }

  private static final class JeuMethodDescriptorSupplier
      extends JeuBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    JeuMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (JeuGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new JeuFileDescriptorSupplier())
              .addMethod(getGuessMethod())
              .build();
        }
      }
    }
    return result;
  }
}
