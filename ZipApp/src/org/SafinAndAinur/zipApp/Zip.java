package org.SafinAndAinur.zipApp;

import java.io.File;
import java.util.List;

public class Zip implements ZipInterface {

    private List<File> fileList; // файлы, кторые мы хотим сжать
    private String path; // путь
    private static final String DEFAULT_PATH = "/";
    private String zipName = "ourZip.zip";

    
    public Zip(List<File> fileList, String path) {
        this.fileList = fileList;
        this.path = path;
    }

    public Zip(List<File> list) {
        this.fileList = list;
        this.path = DEFAULT_PATH;
    }


    @Override
    public void compress() {

    }

    @Override
    public void deCompress(String pathToZip) {

    }



}
