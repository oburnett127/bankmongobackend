package com.oburnett127.transactionservice.constant;

public interface ErrorMessage {

    String MSG0 = "ERRORMSG0: Oops something went wrong";
    String MSG1 = "ERRORMSG1: Amount must be specified for Transaction id {}";
    String MSG2 = "ERRORMSG2: Amount must be a positive number";
    String MSG3 = "ERRORMSG3: Check must have a signature.";
    String MSG4 = "ERRORMSG4: Signature {} cannot contain digits.";
    String MSG5 = "ERRORMSG5: Check must have a name.";
    String MSG6 = "ERRORMSG6: Name {} cannot contain digits.";
    String MSG7 = "ERRORMSG7: Amount for deposit was 0 or null";
    String MSG8 = "ERRORMSG8: Amount for deposit check was 0, negative or null";
    String MSG9 = "ERRORMSG9: Insufficient funds. Cannot withdraw the requested amount.";
}
