package com.maaxii.util;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    private static final Logger log = LoggerFactory.getLogger(ResourcesLoader.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T getTestData(String path, Class<T> type) {
        try (InputStream stream = ResourcesLoader.getResources(path)) {
            return mapper.readValue(stream, type);
        } catch (Exception e) {
            log.error("Unable to read test data {}", path, e);
        }
        return null;
    }
}
