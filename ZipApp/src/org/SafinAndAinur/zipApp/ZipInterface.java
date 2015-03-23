package org.SafinAndAinur.zipApp;

public interface ZipInterface {

    //архивирует
    public void compress();

    //разархивирует, pathToZip - путь до zip архива, который нужно разархивировать
    public void deCompress(String pathToZip);


}
