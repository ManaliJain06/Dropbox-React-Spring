package com.dropbox.DropboxSpringMongoDB;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dropbox.DropboxSpringMongoDB.controller.FilesController;
import com.dropbox.DropboxSpringMongoDB.controller.GroupsController;
import com.dropbox.DropboxSpringMongoDB.controller.UserController;
import com.dropbox.DropboxSpringMongoDB.document.Files;
import com.dropbox.DropboxSpringMongoDB.document.Groups;
import com.dropbox.DropboxSpringMongoDB.document.User;
import com.dropbox.DropboxSpringMongoDB.service.FilesService;
import com.dropbox.DropboxSpringMongoDB.service.GroupsService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
public class TestGroupController extends DropboxSpringMongoDbApplicationTests{
    @Mock
    private GroupsService groupsService;

    @InjectMocks
    private GroupsController groupsController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc = MockMvcBuilders.standaloneSetup(groupsController).build();
    }

    @Test
    public void testCreateGroup() throws Exception{

        String payload = "{user_uuid : 1234 , user_name: manali, groupName: testing123 }";

        String msg = "success";
        Mockito.when(groupsService.createGroup("1234", "manali", "testing123")).thenReturn(msg);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/groups/createGroup")
                .accept(MediaType.APPLICATION_JSON_VALUE).content(payload)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(200, response.getStatus());
    }
    @Test
    public void testGetGroups() throws Exception{
        List<Groups> groups = new ArrayList<>();
        groups.add(new Groups());
        groups.add(new Groups());

        String user_uuid = "1234";
        Mockito.when(groupsService.getGroups("1234")).thenReturn(groups);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/groups/getGroups/{user_uuid} ",1234)
                .accept(MediaType.APPLICATION_JSON_VALUE).content(user_uuid)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(200, response.getStatus());
    }

    @Test
    public void testDeleteFileOrDir() throws Exception{

        String payload = "{_id : 1234}";

        String msg = "success";
        Mockito.when(groupsService.deleteGroup("1234")).thenReturn(msg);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/groups/deleteGroup")
                .accept(MediaType.APPLICATION_JSON_VALUE).content(payload)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(200, response.getStatus());
    }
}
