package org.connect.shell.encryptionDecryption;

import org.connect.shell.encryptionDecryption.encapsulators.PrivateKeyEncapsulator;
import org.connect.shell.encryptionDecryption.encapsulators.PublicKeyEncapsulator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class EncryptedReceiver
{
    private transient long primeP;
    private transient long primeQ;
    private long publicSharedN;
    private long publicSharedE;

    private transient final Map<Integer, Long> primeTable = new HashMap<>();

    private transient  PublicKeyEncapsulator publicKeyEncapsulator;
    private transient PrivateKeyEncapsulator privateKeyEncapsulator;


    public void randomizeForKeyGeneration() {
        primeP = randomlyExtractElementFromIntKeyDictionary(primeTable);
        primeQ = randomlyExtractElementFromIntKeyDictionary(primeTable);
        publicSharedE = randomlyExtractElementFromIntKeyDictionary(primeTable);

        publicSharedN = primeP * primeQ;

        publicKeyEncapsulator = new PublicKeyEncapsulator(publicSharedN, publicSharedE);
        privateKeyEncapsulator = new PrivateKeyEncapsulator(publicSharedE, primeP, primeQ);
    }

    private <T> T randomlyExtractElementFromIntKeyDictionary(final Map<Integer, T> dictionary)
    {
        System.out.println(dictionary);
        Random random = new Random();
        final int index = random.nextInt(0, primeTable.size());
        final T prime = dictionary.get(index);
        primeTable.remove(index);
        return prime;
    }

    public PublicKeyEncapsulator getPublicKey() {
        return publicKeyEncapsulator;
    }

    public PrivateKeyEncapsulator getPrivateKey() {
        return privateKeyEncapsulator;
    }

    public void initializePrimesFromFile(String filePath) throws IOException {
        final List<String> lines = Files.lines(Paths.get(filePath)).toList();
        int i = 0;
        for(final String s: lines)
            primeTable.put(i++, Long.parseLong(s));

        System.out.println(primeTable);
    }

    public Map<Integer, Long> getPrimeTable()
    {
        return Map.copyOf(primeTable);
    }
}
