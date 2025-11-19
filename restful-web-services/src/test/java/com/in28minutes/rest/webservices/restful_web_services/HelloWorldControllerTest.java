package com.in28minutes.rest.webservices.restful_web_services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.* ;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(HelloWorldController.class)
public class HelloWorldControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHelloWorld() throws Exception {
        // Test logic goes here
        mockMvc.perform(get("/hello-world"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World"));
    }

    @Test
    public void testHelloWorldBean() throws Exception {
        // Test logic goes here
        mockMvc.perform(get("/hello-world-bean"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello World"));
    }

    @Test
    public void testHelloWorldPathVariable() throws Exception{
        String name = "Kingsley";
        mockMvc.perform(get("/hello-world/path-variable/" + name ).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("message").value("Hello World, " + name));
    }
}
