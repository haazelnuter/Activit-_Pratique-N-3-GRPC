package org.example.clients;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.example.stubs.Bank;
import org.example.stubs.BankServiceGrpc;

import java.util.UUID;

public class clientGrpc1 {
    public static void main(String[] args) {
        ManagedChannel managedChannel= ManagedChannelBuilder.forAddress("localhost",1010)
                .usePlaintext()
                .build();
        //blokingstub== mode synchrone
        BankServiceGrpc.BankServiceBlockingStub BlockingStub=BankServiceGrpc.newBlockingStub(managedChannel);
        Bank.ConvertCurrencyRequest request=Bank.ConvertCurrencyRequest.newBuilder()
                .setAmount(6500).
                setCurrencyFrom("DH")
                .setCurrencyTo("EUR")
                .build();
        Bank.ConvertCurrencyResponse convertResponse = BlockingStub.convert(request);
        System.out.println(convertResponse.toString());

    }
    }

