package UnitTest;


import com.example.notes_api.Controllers.UserController;
import com.example.notes_api.DTO.NotesDTO;
import com.example.notes_api.DTO.UserDTO;
import com.example.notes_api.NotesApiApplication;
import com.example.notes_api.Services.NoteServiceImpl;
import com.example.notes_api.Services.UserService;
import com.example.notes_api.Services.UserServiceImpl;
import com.example.notes_api.filters.JWTFilter;
import com.example.notes_api.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
@ContextConfiguration(classes = NotesApiApplication.class)
@Import(UserTestConfig.class)
public class UserControllerTest {
    @Autowired
    UserService userService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testPostUser() throws Exception{
        UserDTO userDTO = new UserDTO(null, "Name", "Email", "Password", null);
        when(userService.postUser(any())).thenReturn(1L);
        mockMvc.perform(post("/api/users/")).andExpect(status().isOk());
    }
    @Test
    public void testGetAllUsers() throws Exception{
        when(userService.getAllUsers(any())).thenReturn(null);
        mockMvc.perform(get("/api/users/"))
                .andExpect(status().isOk());
    }
    @Test
    public void testUpdateUser() throws Exception{
        UserDTO userDTO = new UserDTO(null, "Name", "Email", "Password", null);
        when(userService.updateUser(any())).thenReturn("Updated");
        mockMvc.perform(put("/api/users/")).andExpect(status().isOk());
    }
    @Test
    public void testDeleteUser() throws Exception{
        when(userService.deleteUser(any())).thenReturn("Deleted");
        mockMvc.perform(delete("/api/users/1")).andExpect(status().isOk());
    }
}


@TestConfiguration
class UserTestConfig {
    @Bean
    UserService userService(){
        return Mockito.mock(UserServiceImpl.class);
    }

    //adding just mock without a specific definition for its method just to test web layer
    @Bean
    JwtUtil jwtUtil(){
        return Mockito.mock(JwtUtil.class);
    }

    @Bean
    JWTFilter jwtFilter(){
        return Mockito.mock(JWTFilter.class);
    }

    @Bean
    AuthenticationManager authenticationManager(){
        return Mockito.mock(AuthenticationManager.class);
    }
}
