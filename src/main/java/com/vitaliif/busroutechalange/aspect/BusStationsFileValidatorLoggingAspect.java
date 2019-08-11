package com.vitaliif.busroutechalange.aspect;

import com.vitaliif.busroutechalange.exception.IllegalBusStationFileException;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BusStationsFileValidatorLoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileOrientedGraphReaderLoggingAspect.class);

    private final String pathToFile;

    public BusStationsFileValidatorLoggingAspect(@Value("${file.busStations.path}") String pathToFile) {

        this.pathToFile = pathToFile;
    }

    @Before("execution(* com.vitaliif.busroutechalange.service.impl.BusStationsFileValidator.isValid()))")
    public void logBeforeFileInitialization() {
        LOGGER.info(String.format(
                "Started file validation. Path to file [%s]",
                pathToFile
        ));
    }

    @After("execution(* com.vitaliif.busroutechalange.service.impl.BusStationsFileValidator.isValid()))")
    public void logAfterFileInitialization() {
        LOGGER.info(String.format(
                "File has successfully passed validation. Path [%s]",
                pathToFile
        ));
    }

    @AfterThrowing(value = "execution(* com.vitaliif.busroutechalange.service.impl.FileOrientedGraphReader.initialize()))",
            throwing = "exception")
    public void logging(IllegalBusStationFileException exception) {
        LOGGER.error(exception.getMessage(), exception);
    }
}
