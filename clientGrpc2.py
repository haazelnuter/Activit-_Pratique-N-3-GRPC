import grpc
import bank_pb2
import bank_pb2_grpc
import threading

response_received_event = threading.Event()


def run():
    with grpc.insecure_channel('localhost:1010') as channel:
        stub = bank_pb2_grpc.BankServiceStub(channel)
        request = bank_pb2.ConvertCurrencyRequest(
            amount=6900,
            currencyFrom="DH",
            currencyTo="EUR"
        )
        response_future = stub.convert.future(request)
        response_future.add_done_callback(on_convert_currency_complete)

        # Use the global response_received_event to block the main thread
        response_received_event.wait()


def on_convert_currency_complete(response_future):
    try:
        response = response_future.result()
        print("Async gRPC client received: " + str(response))
    except Exception as e:
        print("Error while calling convert_currency: " + str(e))

    # Set the global response_received_event to unblock the main thread
    response_received_event.set()


if __name__ == '__main__':
    run()
