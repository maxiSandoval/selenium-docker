package com.maaxii.util;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Asimple utility to read file.
 * First we check the classpath, if it's found, it is used.
 * If not, then we check the filesystem
 * 
 */
public class ResourcesLoader {

    private static final Logger log = LoggerFactory.getLogger(ResourcesLoader.class);

    public static InputStream getResources(String path) throws Exception{
        log.info("Reading resource from location: {}", path);
        InputStream stream = ResourcesLoader.class.getClassLoader().getResourceAsStream(path);
        if(Objects.nonNull(stream)){
            return stream;
        }
        return Files.newInputStream(Path.of(path));
    }

}
