package org.SafinAndAinur.zipApp;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class FileHelper {

    private static void checkFile(File file) {
        if (!file.exists()){
            try{
                boolean f = file.createNewFile();
                if (!f){
                    throw new FileNotFoundException("Sorry,file doesn't exist");
                }
            }catch (IOException e){
                System.err.println("File not found "+ e);
                e.printStackTrace();
            }
        }
    }

    public static String getString(File file){
        checkFile(file);
        byte [] bytes;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            try(BufferedReader reader = new BufferedReader(new FileReader(file))){
                int s;
                while ((s = reader.read()) != -1){
                    stringBuilder.append((char)s);
                }
            }
        } catch (IOException e) {
            System.err.println("File read exception " + e);
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static String bytesToString(byte [] bytes){
        if (bytes == null){
            throw new NullPointerException("bytes array mustn't be  null");
        }
        CharBuffer charBuffer = ByteBuffer.wrap(bytes).asCharBuffer();
        return charBuffer.toString();
    }


    public static byte [] stringToBytesUTFNIO(String string){
        if (string == null){
            return new byte[]{};
        }
        char [] buff = string.toCharArray();
        byte [] bytes = new byte[buff.length<<1];
        CharBuffer charBuffer = ByteBuffer.wrap(bytes).asCharBuffer();
        for (int i = 0; i <buff.length; i++) {
            charBuffer.put(buff[i]);
        }
        return bytes;
    }

    public static String binarySequence(byte [] bytes){
        if (bytes == null){
            throw new NoSuchElementException("bytes mustn't be null");
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (byte val : bytes){
            stringBuilder.append(Long.toBinaryString((val)));
        }
        return stringBuilder.toString();
    }

//    подсчитывает кол-во символов и возрашает HashMap<Символ,кол-во>

    public static HashMap<Character, Long> symbolsFrequencyMap(File file) {
        checkFile(file);
        String s = getString(file);
        HashMap<Character, Long> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1L);
            }
        }
        return map;
    }
}
