package org.connect.shell.encryptionDecryption.encapsulators;

public class PublicKeyEncapsulator {
    private long publicSharedN;
    private long publicSharedE;

    public PublicKeyEncapsulator(long publicSharedN, long publicSharedE) {
        this.publicSharedN = publicSharedN;
        this.publicSharedE = publicSharedE;
    }

    public long getModNumber() {
        return publicSharedN;
    }

    public long getPoweredPrime() {
        return publicSharedE;
    }

    public long encryptMessage(char h) {
        return encryptMessage((int) h);
    }

    public long encryptMessage(int h) {
        return Double.valueOf(Math.pow(Double.valueOf(h), Double.valueOf(getPoweredPrime()))).longValue()%getModNumber();
    }
}
