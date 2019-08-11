package com.vitaliif.busroutechalange.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Aspect
@Component
public class FileOrientedGraphReaderLoggingAspect
{
    private static final Logger LOGGER = LoggerFactory.getLogger(FileOrientedGraphReaderLoggingAspect.class);

    private final String pathToFile;

    public FileOrientedGraphReaderLoggingAspect(@Value("${file.busStations.path}") String pathToFile) {

        this.pathToFile = pathToFile;
    }

    @Before("execution(* com.vitaliif.busroutechalange.service.impl.FileOrientedGraphReader.initialize()))")
    public void logBeforeFileInitialization() {
        LOGGER.info(String.format(
                "Start extracting oriented graph from file. Path [%s]",
                pathToFile
        ));
    }

    @After("execution(* com.vitaliif.busroutechalange.service.impl.FileOrientedGraphReader.initialize()))")
    public void logAfterFileInitialization() {
        LOGGER.info(String.format(
                "File initialization has been successfully completed and mapped to oriented graph. Path [%s]",
                pathToFile
        ));
    }

    @AfterThrowing(value = "execution(* com.vitaliif.busroutechalange.service.impl.FileOrientedGraphReader.initialize()))",
            throwing = "exception")
    public void logging(IOException exception) {
        LOGGER.error(String.format(
                "Exception occurs while file reading. Path [%s]",
                pathToFile
        ), exception);
    }
}