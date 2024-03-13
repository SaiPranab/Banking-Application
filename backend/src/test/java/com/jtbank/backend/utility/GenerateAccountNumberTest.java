package com.jtbank.backend.utility;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenerateAccountNumberTest {

    @Test
    void generateNumber() {
        System.out.println(GenerateAccountNumber.generateNumber());
        var result = GenerateAccountNumber.generateNumber();
        Assertions.assertTrue(result > 1_00_00_00_000);
    }
}