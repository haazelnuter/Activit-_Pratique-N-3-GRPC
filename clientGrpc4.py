import grpc
import bank_pb2
import bank_pb2_grpc

def perform_client_streaming(stub):
    requests = [
        bank_pb2.ConvertCurrencyRequest(currencyFrom="USD", currencyTo="EUR", amount=10690),
        bank_pb2.ConvertCurrencyRequest(currencyFrom="EUR", currencyTo="JPY", amount=2090),
        bank_pb2.ConvertCurrencyRequest(currencyFrom="JPY", currencyTo="GBP", amount=3890),
    ]
    response = stub.performStream(iter(requests))
    print(f"Result: {response.result:.2f}")

def main():
    channel = grpc.insecure_channel('localhost:1010')
    stub = bank_pb2_grpc.BankServiceStub(channel)
    perform_client_streaming(stub)

if __name__ == '__main__':
    main()