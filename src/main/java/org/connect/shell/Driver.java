package org.connect.shell;

import org.connect.shell.encryptionDecryption.EncryptedReceiver;
import org.connect.shell.encryptionDecryption.encapsulators.PrivateKeyEncapsulator;
import org.connect.shell.encryptionDecryption.encapsulators.PublicKeyEncapsulator;

public class Driver
{
    public static void main(String[] args)
    {
        EncryptedReceiver encryptedReceiver = new EncryptedReceiver();
        encryptedReceiver.randomizeForKeyGeneration();
        PublicKeyEncapsulator publicKeyEncapsulator = encryptedReceiver.getPublicKey();
        long modNumber = publicKeyEncapsulator.getModNumber();
        long poweredPrime = publicKeyEncapsulator.getPoweredPrime();
        PrivateKeyEncapsulator privateKeyEncapsulator = encryptedReceiver.getPrivateKey();

        long messageKeyChar = publicKeyEncapsulator.encryptMessage('H');
        long messageKeyLong = publicKeyEncapsulator.encryptMessage(72);
        char messageFromChar = privateKeyEncapsulator.decryptMessage(messageKeyChar);
        char messageFromLong = privateKeyEncapsulator.decryptMessage(messageKeyLong);

        assert messageFromLong == 72;
        assert messageFromChar == 72;
    }
}
