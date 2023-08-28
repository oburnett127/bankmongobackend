package com.oburnett127.transactionservice.constant;

public interface DebugMessage {

    String MSG1 = "DEBUGMSG1: Deposit successful for Transaction id {}";
    String MSG2 = "DEBUGMSG2: Check deposit successful for Transaction id {}";
    String MSG3 = "DEBUGMSG3: deposit {} amount into Transaction {}, balance is now at {}";
    String MSG4 = "DEBUGMSG4: deposit {} balance is now at {} Name: {}, Signature{} at {}";
    String MSG5 = "DEBUGMSG5: created new Transaction for {} with id {}";
    String MSG6 = "DEBUGMSG6: withdraw amount {} from Transaction {}, balance is now at {}";
    String MSG7 = "DEBUGMSG7: Transfer successful. Sending Transaction id {}, receiving Transaction id {}";
}