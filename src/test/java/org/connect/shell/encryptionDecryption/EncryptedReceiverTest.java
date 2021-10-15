package org.connect.shell.encryptionDecryption;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EncryptedReceiverTest
{
    @InjectMocks
    private EncryptedReceiver sut = new EncryptedReceiver();

    Map<Integer, Long> primeTable = Map.of(0, 1L, 1, 2L, 2, 3L, 3, 5L, 4, 7L, 5, 11L);

    @Test
    void initializePrimesFromFile() throws IOException
    {
        sut.initializePrimesFromFile("src/test/resources/Primes.txt");
        assertEquals(primeTable, sut.getPrimeTable());
    }
}