package IntegrationTest;


import com.example.notes_api.DTO.AccessDTO;
import com.example.notes_api.DTO.NotesDTO;
import com.example.notes_api.DTO.UserDTO;
import com.example.notes_api.Models.Classification;
import com.example.notes_api.NotesApiApplication;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.logging.Logger;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = NotesApiApplication.class)
@AutoConfigureMockMvc
public class IntegrationTest {
    @Autowired
    public  MockMvc mvc;
    @LocalServerPort
    int port; //random port
    static TestRestTemplate restTemplate;

    @BeforeAll
    public static void setUp() {
        restTemplate = new TestRestTemplate();
    }

    @Test
    public void testEverything() throws Exception{
        UserDTO userDTO = new UserDTO(1L, "testuser", "password", "email@hotmail.com", "String");
        //post user
        Long userId = Long.parseLong(restTemplate.postForEntity("http://localhost:"+port+"/api/users/", userDTO, String.class).getBody());
        System.out.println("UserId " + userId);
        //get all users
        String response = restTemplate.getForEntity("http://localhost:"+port+"/api/users/", String.class).getBody();
        System.out.println(response);
        //login
        AccessDTO accessDTO = restTemplate.postForEntity("http://localhost:"+port+"/api/users/login?username=testuser&password=password", null, AccessDTO.class).getBody();
        System.out.println(accessDTO.getJwt());
        //create note
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessDTO.getJwt());
        NotesDTO notesDTO = new NotesDTO(1L, "Title", "Description", null, null, Classification.PUBLIC);
        restTemplate.exchange("http://localhost:"+port+"/api/notes/"+userId, HttpMethod.POST, new HttpEntity<>(notesDTO, headers), String.class).getBody();
        //delete user
        restTemplate.delete("http://localhost:"+port+"/api/users/"+userId);
    }

}

//object entity + mockmvc good option for json
//testresttemplate good option for object