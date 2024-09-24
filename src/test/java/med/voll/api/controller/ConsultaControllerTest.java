package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc;

    @WithMockUser
    public void test() throws Exception {
        var reponse = mvc.perform(post("/consulta")).andReturn().getResponse();
        assertThat(reponse.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}