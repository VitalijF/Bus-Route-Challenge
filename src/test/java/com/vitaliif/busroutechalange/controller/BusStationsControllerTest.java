package com.vitaliif.busroutechalange.controller;

import com.vitaliif.busroutechalange.dto.BusRoadResponse;
import com.vitaliif.busroutechalange.dto.ErrorResponse;
import com.vitaliif.busroutechalange.exception.IllegalBusStationFileException;
import com.vitaliif.busroutechalange.service.BusRoadChecker;
import com.vitaliif.util.TestHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(BusStationsController.class)
public class BusStationsControllerTest {

    private MockMvc mockMvc;

    private BusRoadChecker busRoadChecker;

    private static final String URL = "/api/direct";

    @Before
    public void initialize() {
        busRoadChecker = Mockito.mock(BusRoadChecker.class);

        final BusStationsController controller = new BusStationsController(busRoadChecker);

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setControllerAdvice(new ResponseExceptionHandler())
                .build();
    }

    @Test
    public void testSuccessResponse() throws Exception {
        final BusRoadResponse expectedResponse = createSuccessResponse();
        Mockito.when(busRoadChecker.checkRoadExisting(Mockito.anyInt(), Mockito.anyInt())).thenReturn(expectedResponse);

        final String responseAsString = mockMvc.perform(MockMvcRequestBuilders.get(URL)
                .param("dep_sid", "1")
                .param("arr_sid", "2")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();


        final BusRoadResponse actualResponse = TestHelper.convertToObjectFromStringJson(responseAsString, BusRoadResponse.class);

        Assert.assertNotNull(actualResponse);
        Assert.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testErrorResponse() throws Exception {
        Mockito.when(busRoadChecker.checkRoadExisting(Mockito.anyInt(), Mockito.anyInt()))
                .thenThrow(new IllegalBusStationFileException("Invalid bus stations"));

        final String responseAsString = mockMvc.perform(MockMvcRequestBuilders.get(URL)
                .param("dep_sid", "1")
                .param("arr_sid", "2")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
                .andReturn().getResponse().getContentAsString();


        final ErrorResponse actualResponse = TestHelper.convertToObjectFromStringJson(responseAsString, ErrorResponse.class);
        final ErrorResponse expectedResponse = new ErrorResponse("Invalid bus stations");

        Assert.assertNotNull(actualResponse);
        Assert.assertEquals(expectedResponse, actualResponse);
    }

    private static BusRoadResponse createSuccessResponse() {
        return new BusRoadResponse(1, 2, true);
    }
}
