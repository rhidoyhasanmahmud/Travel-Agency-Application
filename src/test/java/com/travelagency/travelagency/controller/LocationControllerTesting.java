package com.travelagency.travelagency.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.travelagency.model.Location;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class LocationControllerTesting extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getLocationsList() throws Exception {
        String uri = "/getAll/active";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Location[] locationlist = super.mapFromJson(content, Location[].class);
        assertTrue(locationlist.length > 0);
    }

    @Test
    public void createLocation() throws Exception {
        String uri = "/location";
        Location location = new Location();
        location.setId(1L);
        location.setName("Dhaka");
        String inputJson = super.mapToJson(location);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int response = mvcResult.getResponse().getStatus();
        assertEquals(201, response);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Location is created successfully");
    }

    @Test
    public void updateLocation() throws Exception {
        String uri = "/location";
        Location location = new Location();
        location.setName("Dhaka 2");
        String inputJson = super.mapToJson(location);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Location is updated successfully");
    }

    @Test
    public void getLocationById() throws Exception {
        String uri = "/location/get/2";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Location is deleted successsfully");
    }
}
