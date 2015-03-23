package org.SafinAndAinur.zipApp;

//класс для работы с консоли

import org.apache.commons.cli.Options;

public class ZipOptions {

    private  boolean isPackOpt;
    private boolean isUnpackOpt;
    private boolean isPackFilesOpt;
    private boolean isPathOpt;

    private String pathToPack;
    private String pathToUnpack;



    private Options options;

    public ZipOptions(Options options) {
        this.options = options;
    }
}
