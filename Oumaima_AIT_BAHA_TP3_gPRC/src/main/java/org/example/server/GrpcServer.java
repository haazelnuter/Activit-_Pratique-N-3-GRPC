package org.example.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.example.service.BankGrpcService;

import java.io.IOException;

public class GrpcServer {
    public static void main(String[] args) throws IOException,InterruptedException {
        Server server= ServerBuilder.forPort(1010)
                .addService(new BankGrpcService())
                .build();
        server.start();
        //
        server.awaitTermination();
    }
}
