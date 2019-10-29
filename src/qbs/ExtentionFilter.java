package qbs;

import java.io.File;
import java.io.FilenameFilter;

//class used to filter files with specific extention
public class ExtentionFilter implements FilenameFilter {

    public String ext;

    public ExtentionFilter(String ext) {
        this.ext = ext;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(ext);
    }
}