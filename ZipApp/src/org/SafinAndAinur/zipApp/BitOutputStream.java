package org.SafinAndAinur.zipApp;

import java.io.IOException;
import java.io.OutputStream;

public class BitOutputStream {

    private OutputStream outputStream;

    private int currentByte;

    private int numOfBitsInCurrentByte;

    public BitOutputStream(OutputStream outputStream) {
        if (outputStream == null){
            throw new NullPointerException("Output stream is null");
        }
        this.outputStream = outputStream;
        currentByte = 0;
        numOfBitsInCurrentByte = 0;
    }

    // Записывает биты в выходной поток
    public void write(int bit) throws IOException {
        if (!(bit == 0 || bit == 1))
            throw new IllegalArgumentException("бит должен быть 0 или 1");
        currentByte = currentByte << 1 | bit;
        numOfBitsInCurrentByte++;
        if (numOfBitsInCurrentByte == 8) {
            outputStream.write(currentByte);
            numOfBitsInCurrentByte = 0;
        }
    }

    public void close() throws IOException{
        while (numOfBitsInCurrentByte != 0){
            write(0); // дописываем 0 , если остались для дополнения до байта
        }
        outputStream.close();
    }
}
