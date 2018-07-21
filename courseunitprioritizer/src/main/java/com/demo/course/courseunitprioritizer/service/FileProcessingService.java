package com.demo.course.courseunitprioritizer.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Function;
import java.util.stream.Stream;

public class FileProcessingService<R> {

    private Path filePath;

    public FileProcessingService(Path path) {
        this.filePath = path;
    }

    public R processFile(Function<Stream<String>, R> fileProcessFunction) throws FileProcessingServiceException {

        try (Stream<String> stream = Files.lines(filePath)) {
            return fileProcessFunction.apply(stream);

        } catch (IOException e) {
            throw new FileProcessingServiceException("Error in handling file processor: " + fileProcessFunction, e);
        }
    }

}
