package com.jtbank.backend.utility;

public class GenerateAccountNumber {
    private GenerateAccountNumber() {}

    public static long generateNumber() {
        var randomNumber = (long) (Math.random() * 100_00_00_000);
        var result = 100_00_00_000l + randomNumber;
        return result;
    }
}
