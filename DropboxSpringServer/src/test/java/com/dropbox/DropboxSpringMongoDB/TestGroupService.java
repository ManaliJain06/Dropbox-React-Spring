package com.dropbox.DropboxSpringMongoDB;
import static org.junit.Assert.assertEquals;

import com.dropbox.DropboxSpringMongoDB.document.*;
import com.dropbox.DropboxSpringMongoDB.repository.GroupsRepository;
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

public class TestGroupService extends DropboxSpringMongoDbApplicationTests {
    @Mock
    private GroupsRepository groupsRepository;

    @InjectMocks
    private GroupsService groupsService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc = MockMvcBuilders.standaloneSetup(groupsService).build();
    }

    @Test
    public void testCreateGroup() throws Exception{

        List<filesArray> filesArrayList = new ArrayList<>();
        List<MembersArray> membersArrayList = new ArrayList<>();

        MembersArray member = new MembersArray();
        member.setMember_name("manali");
        member.setMember_uuid("1234");

        membersArrayList.add(member);

        Groups group = new Groups();
        group.setGroup_uuid(UUID.randomUUID());
        group.setGroup_name("testing");
        group.setCreator_uuid("1234");
        group.setCreator_name("manali");
        group.setMembersArray(membersArrayList);
        group.setFilesArray(filesArrayList);

        Groups g = null;
        Mockito.when(groupsRepository.save(group)).thenReturn(g);

        String msg = groupsService.createGroup("1234", "manali", "testing");

        assertEquals("fail", msg);
    }

}
