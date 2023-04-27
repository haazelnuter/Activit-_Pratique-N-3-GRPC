import grpc
import bank_pb2
import bank_pb2_grpc


def run():
    with grpc.insecure_channel('localhost:1010') as channel:
        stub = bank_pb2_grpc.BankServiceStub(channel)
        request = bank_pb2.ConvertCurrencyRequest(
            amount=6500,
            currencyFrom="DH",
            currencyTo="EUR",
        )
        response = stub.convert(request)
    print("Unary gRPC client received: " + str(response))


if __name__ == '__main__':
    run()
