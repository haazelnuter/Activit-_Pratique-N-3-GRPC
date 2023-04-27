import grpc
import bank_pb2
import bank_pb2_grpc


def run():
    with grpc.insecure_channel('localhost:1010') as channel:
        stub = bank_pb2_grpc.BankServiceStub(channel)
        request = bank_pb2.ConvertCurrencyRequest(
            amount=6890000,
            currencyFrom="DH",
            currencyTo="EUR"
        )
        response_iterator = stub.getCurrencyStream(request)

        for response in response_iterator:
            print("***************************")
            print(response)
            print("***************************")


if __name__ == '__main__':
    run()
