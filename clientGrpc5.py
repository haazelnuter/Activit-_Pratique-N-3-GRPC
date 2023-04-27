import grpc
import bank_pb2
import bank_pb2_grpc
import time
import random
class CurrencyStreamObserver:
    def __init__(self):
        self.responses = []

    def on_next(self, response):
        print(f"Received response: {response}")
        self.responses.append(response)

    def on_error(self, exception):
        print(f"Error: {exception}")

    def on_completed(self):
        print("Completed")

def currency_stream(stub):
    observer = CurrencyStreamObserver()
    stream = stub.fullCurrencyStream(observer)

    request = bank_pb2.ConvertCurrencyRequest(
        currencyFrom="USD",
        currencyTo="EUR",
        amount=12345667
    )
    print(f"Sending request: {request}")
    stream.onNext(request)

    for i in range(20):
        time.sleep(1)
        request = bank_pb2.ConvertCurrencyRequest(
            currencyFrom="USD",
            currencyTo="EUR",
            amount=random.randint(1, 1000)
        )
        print(f"Sending request: {request}")
        stream.onNext(request)

    stream.onCompleted()
    return observer.responses

def main():
    channel = grpc.insecure_channel('localhost:1010')
    stub = bank_pb2_grpc.BankServiceStub(channel)
    responses = currency_stream(stub)
    print(responses)

if __name__ == '__main__':
    main()