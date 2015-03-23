package org.SafinAndAinur.zipApp;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;


public class BitInputStream {

    private InputStream inputStream;

    private int nextBits; // если конец файла -1

    private int numBitsRemaining; // от 0 до 7

    private boolean isEOF; //конец файла

    public BitInputStream(InputStream inputStream) {
        if (inputStream == null){
            throw new NullPointerException("Input stream is null");
        }
        this.inputStream = inputStream;
        this.numBitsRemaining = 0;
        this.isEOF = false;
    }

    public int read() throws IOException{
        if (isEOF){
            return -1;
        }
        if (numBitsRemaining == 0){
            nextBits = inputStream.read();
            if(nextBits == -1){
                this.isEOF = true;
                return -1;
            }
            this.numBitsRemaining = 8;
        }
        numBitsRemaining--;
        return (nextBits >>> numBitsRemaining)&1;
    }

    public int readNoEOF() throws IOException{
        int res = read();
        if (res != -1){
            return res;
        }else {
            throw new EOFException("Достигнут конец файла");
        }

    }

    public void close() throws IOException{
        inputStream.close();
    }
}
