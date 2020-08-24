package com.github.javafaker.service.files;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class EnFile {
    private final String file;
    private final String path;

    private EnFile(String file, String path) {
        this.file = file;
        this.path = path;
    }
    public String getFile() {
        return file;
    }

    public String getPath() {
        return path;
    }

    private static List<EnFile> files;

    static {
        try {
            files = initFiles();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static List<EnFile> getFiles() {
        return new ArrayList<EnFile>(files);
    }

    public static List<EnFile> initFiles() throws FileNotFoundException {
        List<EnFile> files = new ArrayList<EnFile>();

        File pwd = null;

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        pwd = new File(loader.getResource("en").getPath());

        for (File file : pwd.listFiles()) {
            if (file.isFile() && file.toString().endsWith(".yml")) {
                Yaml yaml = new Yaml();

                final Map<Map, Map<Map, Map>> valuesMap = yaml.loadAs(new FileInputStream(file), Map.class);
                Set<String> paths = findLeafPaths(valuesMap.get("en").get("faker"), "");

                for(String path : paths) {
                    files.add(new EnFile(file.getName(), path));
                }
            }
        }

        return files;
    }

    private static Set<String> findLeafPaths(Map<String, Object> map, String path) {

        Set<String> paths = new HashSet<String>();

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() instanceof Map) {
                Set<String> subPaths = findLeafPaths((Map) entry.getValue(), path + "." + entry.getKey());
                paths.addAll(subPaths);
            } else {
                String leafPath = path.length() > 0 ? path.substring(1) : path;

                int lastSep = leafPath.lastIndexOf('.');
                if (lastSep > -1)
                    leafPath = leafPath.substring(0, lastSep);

                paths.add(leafPath);
            }
        }

        return paths;
    }
}
