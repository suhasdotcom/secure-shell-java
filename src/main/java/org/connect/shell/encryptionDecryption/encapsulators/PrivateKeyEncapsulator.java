package org.connect.shell.encryptionDecryption.encapsulators;

import java.math.BigInteger;

public class PrivateKeyEncapsulator {
    private transient long primeD;
    private long publicSharedN;

    public PrivateKeyEncapsulator(final long publicSharedE, final long primeP, final long primeQ) {
        initializePrimeD(publicSharedE, primeP, primeQ);
        initializePublicSharedN(primeP, primeQ);
    }

    private void initializePublicSharedN(long primeP, long primeQ) {
        publicSharedN = primeP*primeQ;
    }

    /**
     * original formula: (E*D)(mod (P-1) * (Q-1)) = 1
     * manipulated:
     * @param publicSharedE
     * @param primeP
     * @param primeQ
     */
    private void initializePrimeD(long publicSharedE, long primeP, long primeQ) {
        final BigInteger p = BigInteger.valueOf(primeP);
        final BigInteger q = BigInteger.valueOf(primeQ);
        final BigInteger e = BigInteger.valueOf(publicSharedE);

        final BigInteger p_1 = p.subtract(BigInteger.valueOf(1L));
        final BigInteger q_1 = q.subtract(BigInteger.valueOf(1L));

        final BigInteger d = e.modInverse(p_1.multiply(q_1));
        primeD = d.longValue();
    }

    public long decryptMessage(long messageKeyChar) {
        return Double.valueOf(Math.pow(Double.valueOf(messageKeyChar), Double.valueOf(primeD))).longValue()%publicSharedN;
    }
}
