package org.example;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import org.example.jeu.JeuGrpc;
import org.example.jeu.JeuOuterClass;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;


public class JeuClient {
    private static final Logger logger = Logger.getLogger(JeuClient.class.getName());

    private final ManagedChannel channel;
    private final JeuGrpc.JeuBlockingStub blockingStub;

    public JeuClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        blockingStub = JeuGrpc.newBlockingStub(channel);
    }

    public void play(String playerName) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(playerName + ", devinez un nombre entre 1 et 1000 : ");
            int guess = scanner.nextInt();

            JeuOuterClass.GuessRequest request = JeuOuterClass.GuessRequest.newBuilder()
                    .setPlayerName(playerName)
                    .setGuess(guess)
                    .build();

            JeuOuterClass.Response response;
            try {
                response = blockingStub.guess(request);
                System.out.println(response.getMessage());
                if (response.getMessage().startsWith("BRAVO")) {
                    break;
                }
            } catch (StatusRuntimeException e) {
                logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
                break;
            }
        }
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws InterruptedException {
        JeuClient client = new JeuClient("localhost", 2000);
        try {
            client.play("Joueur 1");
        } finally {
            client.shutdown();
        }
    }
}