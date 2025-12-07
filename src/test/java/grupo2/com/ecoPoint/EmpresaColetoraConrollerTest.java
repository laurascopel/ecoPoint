package grupo2.com.ecoPoint;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import grupo2.com.ecoPoint.Controller.EmpresaColetoraController;
import grupo2.com.ecoPoint.Model.Entity.EmpresaColetora;
import grupo2.com.ecoPoint.Repository.EmpresaColetoraRepository;
import grupo2.com.ecoPoint.Service.EmpresaColetoraService;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

@AutoConfigureMockMvc(addFilters = false)

@WebMvcTest(EmpresaColetoraController.class)

public class EmpresaColetoraConrollerTest {
    @Autowired MockMvc mockMvc;

    @MockBean
    private EmpresaColetoraService empresaColetoraService;

    @MockBean
    private EmpresaColetoraRepository empresaColetoraRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;


    @Test
    void retornarId() throws Exception {
        EmpresaColetora empresa = new EmpresaColetora();
        empresa.setId(1L);
        empresa.setNome("EcoPoint");
        empresa.setEmail("ecopoint@email.com");

        when(empresaColetoraService.getEmpresaColetoraById(1L))
        .thenReturn(empresa);

        mockMvc.perform(get("/empresasColetoras/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.nome").value("EcoPoint"));
        
    }

    @Test
    void cadastro() throws Exception {
        String json = """
            {
            "nome": "EcoPoint",
            "email": "ecopoint@email.com",
            "senha": "1234"
            }
            """;


        EmpresaColetora empresa = new EmpresaColetora();
        empresa.setId(1L);
        empresa.setNome("EcoPoint");
        empresa.setEmail("ecopoint@email.com");
        empresa.setSenha("$2a$10$hashaqui");

        when(empresaColetoraService.salvarEmpresaColetora(any(EmpresaColetora.class)))
        .thenReturn(empresa);

        mockMvc.perform(post("/empresasColetoras/cadastrar")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))

        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.nome").value("EcoPoint"))
        .andExpect(jsonPath("$.email").value("ecopoint@email.com"));
            
    }

    @Test
    void atualizarEmpresaColetora() throws Exception {
        String json = """
            {
            "nome": "EcoPoint Atualizada",
            "email": "ecopoint@email.com"
    }         """;

        EmpresaColetora empresaAtualizada = new EmpresaColetora();
        empresaAtualizada.setId(1L);
        empresaAtualizada.setNome("EcoPoint Atualizada");
        empresaAtualizada.setEmail("ecopoint@email.com");

        when(empresaColetoraService.atualizarEmpresaColetora(
            Mockito.eq(1L),
            Mockito.any(EmpresaColetora.class)
        )).thenReturn(empresaAtualizada);

        mockMvc.perform(put("/empresasColetoras/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.nome").value("EcoPoint Atualizada"));
    }

    @Test
    void loginBemSucedido() throws Exception {
        String jsonLogin = """
            {
                "email": "ecopoint@email.com",
                "senha": "1234"
            }
            """;    

        EmpresaColetora empresa = new EmpresaColetora();
        empresa.setId(1L);
        empresa.setNome("EcoPoint");
        empresa.setEmail("ecopoint@email.com");
        empresa.setSenha("$2a$10$hashaqui");
        
  
        Mockito.when(empresaColetoraRepository.findByEmail("ecopoint@email.com"))
        .thenReturn(Optional.of(empresa));

        Mockito.when(passwordEncoder.matches("1234", empresa.getSenha()))
        .thenReturn(true);

        mockMvc.perform(post("/empresasColetoras/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonLogin))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.nome").value("EcoPoint"));
    }

    @Test
    void loginFalhaSenhaIncorreta() throws Exception {
        String jsonLogin = """
            {
                "email": "ecopoint@email.com",
                "senha": "senhaErrada"
    }
            """;    

        EmpresaColetora empresa = new EmpresaColetora();
        empresa.setId(1L);
        empresa.setNome("EcoPoint");
        empresa.setEmail("ecopoint@email.com");
        empresa.setSenha("$2a$10$hashaqui");

        Mockito.when(empresaColetoraRepository.findByEmail("ecopoint@email.com"))
        .thenReturn(Optional.of(empresa));

        Mockito.when(passwordEncoder.matches("senhaErrada", empresa.getSenha()))
        .thenReturn(false);

        mockMvc.perform(post("/empresasColetoras/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonLogin))
        .andExpect(status().isUnauthorized())
        .andExpect(content().string("Senha incorreta"));    

    }

    @Test
    void loginFalhaEmailIncorreto() throws Exception {
        String jsonLogin = """
            {
                "email": "naoexisto@gmail.com",
                "senha": "1234"
            } """;

        EmpresaColetora empresa = new EmpresaColetora();
        empresa.setId(1L);
        empresa.setNome("EcoPoint");
        empresa.setEmail("naoexisto@email.com");
        empresa.setSenha("$2a$10$hashaqui");

        Mockito.when(empresaColetoraRepository.findByEmail("naoexisto@email.com"))
        .thenReturn(Optional.empty());  


        mockMvc.perform(post("/empresasColetoras/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonLogin))
        .andExpect(status().isUnauthorized())
        .andExpect(content().string("Email não encontrado"));

    }

    @Test
    void deletarEmpresaColetora() throws Exception {
        Mockito.doNothing().when(empresaColetoraService).deletarEmpresaColetora(1L);

        mockMvc.perform(delete("/empresasColetoras/1"))
        .andExpect(status().isOk());
    }
                    
        
    
}
