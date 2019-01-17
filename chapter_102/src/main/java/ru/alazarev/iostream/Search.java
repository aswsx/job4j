package ru.alazarev.iostream;

import java.io.File;
import java.util.*;

public class Search {
    Queue<File> queue = new LinkedList<>();

    private void upDate(File file) {
        if (file.isDirectory()) {
            for (File current : file.listFiles()) {
                upDate(current);
            }
        } else if (file.isFile()) {
            queue.add(file);
        }
    }

    List<File> files(String parent, List<String> exts) {
        List<File> resultFiles = new LinkedList<>();
        for (File currentFile : Arrays.asList(new File(parent).listFiles())) {
            upDate(currentFile);
            while (!queue.isEmpty()) {
                File actualFile = queue.poll();
                for (String reg : exts) {
                    if (actualFile.getName().toLowerCase().matches(".*" + reg)) {
                        resultFiles.add(actualFile);
                        break;
                    }
                }
            }
        }
        return resultFiles;
    }
}
