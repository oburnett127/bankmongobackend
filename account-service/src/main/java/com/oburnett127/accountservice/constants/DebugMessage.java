package com.oburnett127.accountservice.constants;

public interface DebugMessage {

    String MSG1 = "DEBUGMSG1: Deposit successful for account id {}";
    String MSG2 = "DEBUGMSG2: Check deposit successful for account id {}";
    String MSG3 = "DEBUGMSG3: deposit {} amount into account {}, balance is now at {}";
    String MSG4 = "DEBUGMSG4: deposit {} balance is now at {} Name: {}, Signature{} at {}";
    String MSG5 = "DEBUGMSG5: created new account for {} with id {}";
    String MSG6 = "DEBUGMSG6: withdraw amount {} from account {}, balance is now at {}";
    String MSG7 = "DEBUGMSG7: Transfer successful. Sending account id {}, receiving account id {}";
}