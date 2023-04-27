package org.example.clients;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.example.stubs.Bank;
import org.example.stubs.BankServiceGrpc;

import java.io.IOException;

public class clientGrpc3_serverStream {
    public static void main(String[] args) throws IOException {
        ManagedChannel managedChannel= ManagedChannelBuilder.forAddress("localhost",1010)
                .usePlaintext()
                .build();
        //blokingstub== mode Asynchrone
        BankServiceGrpc.BankServiceStub asyncStub=BankServiceGrpc.newStub(managedChannel);
        Bank.ConvertCurrencyRequest request=Bank.ConvertCurrencyRequest.newBuilder()
                .setAmount(6500).
                setCurrencyFrom("DH")
                .setCurrencyTo("EUR")
                .build();
       asyncStub.getCurrencyStream(request, new StreamObserver<Bank.ConvertCurrencyResponse>() {
           @Override
           public void onNext(Bank.ConvertCurrencyResponse convertCurrencyResponse) {
               System.out.println("***************************");
               System.out.println(convertCurrencyResponse);
               System.out.println("***************************");
           }

           @Override
           public void onError(Throwable throwable) {
               System.out.println(throwable.getMessage());

           }

           @Override
           public void onCompleted() {
               System.out.println("FIIIIIIIIIN...");

           }
       });
        //blocker l'application
        System.out.println("click something...");
        System.in.read();
    }
    }

