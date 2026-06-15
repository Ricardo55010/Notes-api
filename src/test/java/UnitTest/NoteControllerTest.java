package UnitTest;


import com.example.notes_api.Controllers.NoteController;
import com.example.notes_api.DTO.NotesDTO;
import com.example.notes_api.Models.Classification;
import com.example.notes_api.NotesApiApplication;
import com.example.notes_api.Repositories.NoteRepository;
import com.example.notes_api.Services.NoteServiceImpl;
import com.example.notes_api.filters.JWTFilter;
import com.example.notes_api.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NoteController.class)
@Import(NoteTestConfig.class)
@ContextConfiguration(classes = NotesApiApplication.class)
public class NoteControllerTest {

    @Autowired
    MockMvc mockMvc;


    @Autowired
    NoteServiceImpl noteService;
//adding mocks for controller , most of them have no methods mocked, just the bean
//there's really no need for them to be here tho, they already exist in the container
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    JWTFilter jwtFilter;

    @Autowired
    UserDetailsService userDetailsService;

    @Test
    public void testPostNote() throws Exception{
        NotesDTO note = new NotesDTO(null, "Title", "Description", null, null, Classification.PUBLIC);
        when(noteService.postNote(any(), any())).thenReturn(1L);
        mockMvc.perform(post("/api/notes/")).andExpect(status().isOk());
    }
    @Test
    public void testGetAllNotes() throws Exception{


        when(noteService.getAllNotes()).thenReturn(null);

        mockMvc.perform(get("/api/notes/"))
                .andExpect(status().isOk());
    }
    @Test
    public void testUpdateNote() throws Exception{

        NotesDTO note = new NotesDTO(null, "Title", "Description", null, null, Classification.PUBLIC);
        when(noteService.updateNote(any())).thenReturn("Updated");
        mockMvc.perform(put("/api/notes/")).andExpect(status().isOk());
    }
    @Test
    public void testDeleteNote() throws Exception{
        when(noteService.deleteNote(any())).thenReturn("Deleted");
        mockMvc.perform(delete("/api/notes/1")).andExpect(status().isOk());
    }
}


@TestConfiguration
class NoteTestConfig {
    @Bean
    NoteServiceImpl noteService(){
        return Mockito.mock(NoteServiceImpl.class);
    }

    @Bean
    JwtUtil jwtUtil(){
        return Mockito.mock(JwtUtil.class);
    }

    @Bean
    JWTFilter jwtFilter(){
        return Mockito.mock(JWTFilter.class);
    }

    @Bean
    UserDetailsService userDetailsService(){
        return Mockito.mock(UserDetailsService.class);
    }


}
