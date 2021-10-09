package org.connect.shell;

import org.connect.shell.encryptionDecryption.EncryptedReceiver;
import org.connect.shell.encryptionDecryption.encapsulators.PrivateKeyEncapsulator;
import org.connect.shell.encryptionDecryption.encapsulators.PublicKeyEncapsulator;

import java.io.IOException;

public class Driver
{
    public static void main(String[] args) throws IOException
    {
        EncryptedReceiver encryptedReceiver = new EncryptedReceiver();
        encryptedReceiver.initializePrimesFromFile("src/main/resources/Primes.txt");
        encryptedReceiver.randomizeForKeyGeneration();
        PublicKeyEncapsulator publicKeyEncapsulator = encryptedReceiver.getPublicKey();
        long modNumber = publicKeyEncapsulator.getModNumber();
        long poweredPrime = publicKeyEncapsulator.getPoweredPrime();
        PrivateKeyEncapsulator privateKeyEncapsulator = encryptedReceiver.getPrivateKey();

        long messageKeyChar = publicKeyEncapsulator.encryptMessage('H');
        long messageKeyLong = publicKeyEncapsulator.encryptMessage(72);
        long messageFromChar = privateKeyEncapsulator.decryptMessage(messageKeyChar);
        long messageFromLong = privateKeyEncapsulator.decryptMessage(messageKeyLong);

        assert messageFromLong == 72;
        assert messageFromChar == 72;
    }
}
