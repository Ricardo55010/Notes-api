package IntegrationTest;


import com.example.notes_api.DTO.AccessDTO;
import com.example.notes_api.DTO.NotesDTO;
import com.example.notes_api.DTO.UserDTO;
import com.example.notes_api.NotesApiApplication;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = NotesApiApplication.class)
@AutoConfigureMockMvc
public class FailTest {
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
    public void testRateLimiter() throws Exception{
        //first 50 requests should be successful
        HttpStatusCode statusCode = null;
        for(int i = 0; i < 50; i++)
            statusCode = restTemplate.getForEntity("http://localhost:"+port+"/api/users/", String.class).getStatusCode();

        assertTrue(statusCode.is2xxSuccessful());
        System.out.println(statusCode);

        //next requests should be rate limited
        for(int i = 0; i < 100; i++)
            statusCode = restTemplate.getForEntity("http://localhost:"+port+"/api/users/", String.class).getStatusCode();

        System.out.println(statusCode);

        assertTrue(statusCode.is4xxClientError());
    }

/*    @Test
    public void testFailedCredentials() throws Exception{
        UserDTO userDTO = new UserDTO(1L, "badUser", "badpassword", "badrmail@hotmail.com", "String");
        //login
        HttpStatusCode statusCode = restTemplate.postForEntity("http://localhost:"+port+"/api/users/login?username=badUser&password=badpassword", null, AccessDTO.class).getStatusCode();
        assertTrue(statusCode.is4xxClientError());

    }
*/
}

//object entity + mockmvc good option for json
//testresttemplate good option for object