package com.vitaliif.busroutechalange.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Component
public class BusStationsControllerLoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(BusStationsControllerLoggingAspect.class);

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void restController() {
    }

    @Pointcut("execution(* *.*(..))")
    protected void allMethods() {
    }

    @Before("restController() && allMethods() && args(..,request)")
    public void logBefore(HttpServletRequest request) {
        LOGGER.info(String.format(
                "Processing request [%s]. Arguments: %s",
                request.getServletPath(),
                extractStringForLogFromRequest(request)
                )
        );
    }

    @AfterReturning(value = "restController() && allMethods()", returning = "response")
    public void logBefore(Object response) {
        LOGGER.info(String.format("Request has been processed. Response: [%s]", response)
        );
    }

    private String extractStringForLogFromRequest(HttpServletRequest request) {
        return request.getParameterMap().entrySet().stream()
                .map(entry -> " { name: [" + entry.getKey() + "], value: " + Arrays.toString(entry.getValue()) + " } ")
                .collect(Collectors.joining());
    }

}
