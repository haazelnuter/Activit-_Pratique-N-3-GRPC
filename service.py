import grpc
import bank_pb2
import bank_pb2_grpc

def run():
    # Créer un canal gRPC
    channel = grpc.insecure_channel('localhost:50051')
    # Créer un stub pour appeler les méthodes du service
    stub = currency_converter_pb2_grpc.BankStub(channel)

    # Appeler la méthode convert() en utilisant le stub
    request = currency_converter_pb2.ConvertCurrencyRequest(currency_from='USD', currency_to='EUR', amount=100)
    response = stub.convert(request)
    print(response)

    # Appeler la méthode getCurrencyStream() en utilisant le stub
    request = currency_converter_pb2.ConvertCurrencyRequest(currency_from='USD', currency_to='EUR', amount=100)
    responses = stub.getCurrencyStream(request)
    for response in responses:
        print(response)

    # Appeler la méthode performStream() en utilisant le stub
    request_iterator = iter([
        currency_converter_pb2.ConvertCurrencyRequest(amount=10),
        currency_converter_pb2.ConvertCurrencyRequest(amount=20),
        currency_converter_pb2.ConvertCurrencyRequest(amount=30),
    ])
    response = stub.performStream(request_iterator)
    print(response)

    # Appeler la méthode fullCurrencyStream() en utilisant le stub
    request_iterator = iter([
        currency_converter_pb2.ConvertCurrencyRequest(amount=10),
        currency_converter_pb2.ConvertCurrencyRequest(amount=20),
        currency_converter_pb2.ConvertCurrencyRequest(amount=30),
    ])
    responses = stub.fullCurrencyStream(request_iterator)
    for response in responses:
        print(response)

if __name__ == '__main__':
    run()