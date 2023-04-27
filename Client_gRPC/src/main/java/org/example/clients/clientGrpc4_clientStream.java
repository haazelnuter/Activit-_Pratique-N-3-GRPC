 package org.example.clients;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.example.stubs.Bank;
import org.example.stubs.BankServiceGrpc;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

 public class clientGrpc4_clientStream {
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
        StreamObserver<Bank.ConvertCurrencyRequest> performStream = asyncStub.performStream(new StreamObserver<Bank.ConvertCurrencyResponse>() {
            @Override
            public void onNext(Bank.ConvertCurrencyResponse convertCurrencyResponse) {
                System.out.println("++++++++++++++++++");
                System.out.println(convertCurrencyResponse);
                System.out.println("++++++++++++++++++");
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                System.out.println("FIIIIIIIIIIIIIIIIIIIIIN");
            }
        });
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            int counter=0;
            @Override
            public void run() {
                Bank.ConvertCurrencyRequest currencyRequest= Bank.ConvertCurrencyRequest.newBuilder()
                                .setAmount(Math.random()*7800)
                                        .build();
                performStream.onNext(currencyRequest);
                System.out.println("++++++++++++++++++++++counter="+counter);
                ++counter;
                if(counter==20){
                    performStream.onCompleted();
                    timer.cancel();
                }
            }
        }, 1000, 1000);
        //blocker l'application
        System.out.println("click something...");
        System.in.read();
    }
    }

