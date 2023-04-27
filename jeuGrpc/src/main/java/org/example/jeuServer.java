package org.example;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import org.example.jeu.JeuGrpc;
import org.example.jeu.JeuOuterClass;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

public class jeuServer {
    private static final Logger logger = Logger.getLogger(jeuServer.class.getName());

    private final int secretNumber = ThreadLocalRandom.current().nextInt(1, 1000);
    private final Server server;

    public jeuServer(int port) {
        server = ServerBuilder.forPort(port)
                .addService(new JeuImpl())
                .build();
    }

    public void start() throws IOException {
        server.start();
        logger.info("Server started, listening on " + server.getPort());
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.err.println("Shutting down gRPC server since JVM is shutting down");
                jeuServer.this.stop();
                System.err.println("Server shut down");
            }
        });
    }

    public void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    private class JeuImpl extends JeuGrpc.JeuImplBase {
        private boolean gameover = false;
        private String winner = "";

        public void guess(JeuOuterClass.GuessRequest request, StreamObserver<JeuOuterClass.Response> responseObserver) {
            if (gameover) {
                responseObserver.onNext(JeuOuterClass.Response.newBuilder().setMessage("Le jeu est terminé. Le gagnant est " + winner).build());
                responseObserver.onCompleted();
                return;
            }

            int guess = request.getGuess();
            if (guess < secretNumber) {
                responseObserver.onNext(JeuOuterClass.Response.newBuilder().setMessage("Votre nombre est plus petit.").build());
            } else if (guess > secretNumber) {
                responseObserver.onNext(JeuOuterClass.Response.newBuilder().setMessage("Votre nombre est plus grand.").build());
            } else {
                gameover = true;
                winner = request.getPlayerName();
                responseObserver.onNext(JeuOuterClass.Response.newBuilder().setMessage("BRAVO " + winner + " vous avez gagné !").build());
            }
            responseObserver.onCompleted();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        jeuServer server = new jeuServer(2000);
        server.start();
        server.server.awaitTermination();
    }
}
