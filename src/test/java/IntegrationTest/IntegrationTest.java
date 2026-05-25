package IntegrationTest;


import com.example.notes_api.DTO.UserDTO;
import com.example.notes_api.NotesApiApplication;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,classes = NotesApiApplication.class)
@AutoConfigureMockMvc
public class IntegrationTest {
    @Autowired
    public  MockMvc mvc;
    public  Long userId;


    @Test
    public void test() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        UserDTO userDTO = new UserDTO(1L, "testuser", "testemail", "testpassword", "String");
        userDTO.setToken("testtoken");
        String userBody = objectMapper.writeValueAsString(userDTO);
        System.out.println(userBody);
        userId =  Long.parseLong(mvc.perform(post("/api/users/").contentType(MediaType.APPLICATION_JSON).content(userBody)).andReturn().getResponse().getContentAsString());
        System.out.println("UserId " + userId);
        String response = mvc.perform(get("/api/users/")).andReturn().getResponse().getContentAsString();
        System.out.println(response);
        mvc.perform(delete("/api/users/"+userId));
    }
}
