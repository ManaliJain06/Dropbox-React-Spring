package com.dropbox.DropboxSpringMongoDB;


import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dropbox.DropboxSpringMongoDB.controller.UserController;
import com.dropbox.DropboxSpringMongoDB.document.User;
import com.dropbox.DropboxSpringMongoDB.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

public class TestUserController extends DropboxSpringMongoDbApplicationTests {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController usercontroller;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc = MockMvcBuilders.standaloneSetup(usercontroller).build();
    }

    @Test
    public void testLogin() throws Exception {
        User user = new User();
        user.setEmail("z@gmail.com");
        user.setPassword("Manali");

        String body = (new ObjectMapper()).valueToTree(user).toString();

        User loggedInuser = new User();
        loggedInuser.setPassword("abcd");
        Mockito.when(userService.checkUser(user.getEmail())).thenReturn(loggedInuser);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/user/login")
                .accept(MediaType.APPLICATION_JSON_VALUE).content(body)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(401, response.getStatus());
    }

    @Test
    public void testAlreadyExistingUser() throws Exception {
        User signup = new User();
        signup.setEmail("z@gmail.com");
        signup.setPassword("Manali");
        signup.setUser_uuid(UUID.randomUUID());
        signup.setFirstName("manali");
        signup.setLastName("jain");

        String body = (new ObjectMapper()).valueToTree(signup).toString();

        User existingUser = new User();
        existingUser.setPassword("Manali");
        Mockito.when(userService.checkUser(signup.getEmail())).thenReturn(existingUser);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/user/signup")
                .accept(MediaType.APPLICATION_JSON_VALUE).content(body)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(401, response.getStatus());
    }

    @Test
    public void testAddNewUser() throws Exception {
        User signup = new User();
        signup.setEmail("z@gmail.com");
        signup.setPassword("Manali");
        signup.setUser_uuid(UUID.randomUUID());
        signup.setFirstName("manali");
        signup.setLastName("jain");
        signup.setInterest(null);
        signup.setOverview(null);

        String body = (new ObjectMapper()).valueToTree(signup).toString();

        User existingUser = null;
        Mockito.when(userService.checkUser(signup.getEmail())).thenReturn(existingUser);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/user/signup")
                .accept(MediaType.APPLICATION_JSON_VALUE).content(body)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(202, response.getStatus());
    }
}
