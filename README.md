# bankmongo
Who knows why I named it bankmongo since id doesn't use Mongo?
-----------------------------------------------------------------
Instructions for Postman:
-----------------------------------------------
account-service: http://localhost:9002/account/

http://localhost:9002/account/list - GET - Lists all accounts


http://localhost:9002/account/create - POST - create a new account
{
    "fullName":"Cat Reynolds",
    "balance":12345
}

http://localhost:9002/account/get - GET - get account info for a given account id
{
    "id":3
}

http://localhost:9002/account/withdraw - POST - withdraw given amount from given account
{
    "id":1,
    "amount":300
}

http://localhost:9002/account/deposit - POST - deposit given amount into given account
{
    "id":1,
    "amount":400
}

http://localhost:9002/account/depositcheck - POST - deposit check with given amount into given account - try without a fullname or signature
{
    "id":1,
    "fullName":"cat reynolds",
    "signature":"cat reynolds",
    "amount":250
}

http://localhost:9002/account/transfer - POST - Perform transfer from one account to another
{
    "idSender":1,
    "idReceiver":2,
    "amount":4
}
-------------------------------------------------------
transaction-service: http://localhost:9003/transaction/

http://localhost:9003/transaction/list - GET - List all transactions in database

http://localhost:9003/transaction/create - POST - create a new transaction
transType: (0: withdraw, 1: deposit, 2: deposit check, 3: transfer). Put account number in sender and receiver fields.
{
    "account":1,
    "date":"2022-04-20",
    "description":"transfer from acct 1 to acct 2",
    "transType":3,
    "amount":1,
    "sender":1,
    "receiver":2
}

http://localhost:9003/transaction/gettransbyaccount - GET - Get all transactions for a given account ID.
{
    "id":1
}

http://localhost:9003/transaction/gettran - GET - Get transaction info for given transaction ID.
{
    "id":2
}

