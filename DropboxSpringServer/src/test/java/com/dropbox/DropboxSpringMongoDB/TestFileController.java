package com.dropbox.DropboxSpringMongoDB;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dropbox.DropboxSpringMongoDB.controller.FilesController;
import com.dropbox.DropboxSpringMongoDB.controller.UserController;
import com.dropbox.DropboxSpringMongoDB.document.Files;
import com.dropbox.DropboxSpringMongoDB.document.User;
import com.dropbox.DropboxSpringMongoDB.service.FilesService;
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

public class TestFileController extends DropboxSpringMongoDbApplicationTests{
    @Mock
    private FilesService filesService;

    @InjectMocks
    private FilesController filesController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc = MockMvcBuilders.standaloneSetup(filesController).build();
    }

    @Test
    public void testCreateDirectory() throws Exception{

        String payload = "{user_uuid : 1234 , dir_name: manali }";
//        String body = (new ObjectMapper()).valueToTree(signup).toString();

        String msg = "success";
        Mockito.when(filesService.createDirectory("1234", "manali")).thenReturn(msg);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/files/createDir")
                .accept(MediaType.APPLICATION_JSON_VALUE).content(payload)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(200, response.getStatus());
    }

    @Test
    public void testGetFiles() throws Exception{
        List<Files> files = new ArrayList<>();
        files.add(new Files());
        files.add(new Files());

        String user_uuid = "1234";
        Mockito.when(filesService.getFiles("1234")).thenReturn(files);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/files/getFiles/{user_uuid} ",1234)
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
        Mockito.when(filesService.deleteFileOrDirectory("1234")).thenReturn(msg);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/files/deleteFileAndDir")
                .accept(MediaType.APPLICATION_JSON_VALUE).content(payload)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(200, response.getStatus());
    }
}
