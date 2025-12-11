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
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import grupo2.com.ecoPoint.Controller.EmpresaGeradoraController;
import grupo2.com.ecoPoint.Model.Entity.EmpresaColetora;
import grupo2.com.ecoPoint.Model.Entity.EmpresaGeradora;
import grupo2.com.ecoPoint.Repository.EmpresaGeradoraRepository;
import grupo2.com.ecoPoint.Service.EmpresaGeradoraService;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

@AutoConfigureMockMvc(addFilters = false)

@WebMvcTest(EmpresaGeradoraController.class)
public class EmpresaGeradoraControllerTest {
    @Autowired MockMvc mockMvc;

    @MockBean
    private EmpresaGeradoraService empresaGeradoraService;

    @MockBean
    private EmpresaGeradoraRepository empresaGeradoraRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    void deveRetornarEmpresaGeradoraPorId() throws Exception {
        EmpresaGeradora empresa = new EmpresaGeradora();
        empresa.setId(1L);
        empresa.setNome("EcoPoint");
        empresa.setEmail("ecopoint@email.com");

        when(empresaGeradoraService.getEmpresaGeradoraById(1L))
        .thenReturn(empresa);

        mockMvc.perform(get("/EmpresaGeradora/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.nome").value("EcoPoint"));

    }

    @Test
    void cadastro() throws Exception {
        String json = """
            {
            "nome": "EcoPoint",
            "cnpj": "12.345.678/0001-90",
            "endereco": "Rua das Flores, 123",
            "telefone": "(11) 98765-4321",
            "email": "ecopoint@email.com",
            "senha": "1234"
            }
            """;

        EmpresaGeradora empresa = new EmpresaGeradora();
        empresa.setId(1L);
        empresa.setNome("EcoPoint");
        empresa.setEmail("ecopoint@email.com");
        empresa.setSenha("$2a$10$hashaqui");

        when(empresaGeradoraService.salvarEmpresaGeradora(any(EmpresaGeradora.class)))
        .thenReturn(empresa);

        mockMvc.perform(post("/EmpresaGeradora/cadastrar")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1L))
        .andExpect(jsonPath("$.nome").value("EcoPoint"))
        .andExpect(jsonPath("$.email").value("ecopoint@email.com"));

    }

    @Test
    void atualizarEmpresaGeradora() throws Exception {
        String json = """
            {
            "nome": "EcoPoint Atualizada",
            "email": "ecopoint@email.com"
    }         """;

        EmpresaGeradora empresaAtualizada = new EmpresaGeradora();
        empresaAtualizada.setId(1L);
        empresaAtualizada.setNome("EcoPoint Atualizada");
        empresaAtualizada.setEmail("ecopoint@email.com");

        when(empresaGeradoraService.atualizarEmpresaGeradora(
            Mockito.eq(1L),
            Mockito.any(EmpresaGeradora.class)
        )).thenReturn(empresaAtualizada);

        mockMvc.perform(put("/EmpresaGeradora/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.nome").value("EcoPoint Atualizada"));
    }


    @Test
    void loginBemSucedidoQuandoSenhaEemailEstiveremCorretos() throws Exception {
        String jsonLogin = """
            {
                "email": "ecopoint@email.com",
                "senha": "1234"
    }         """;

        EmpresaGeradora empresa = new EmpresaGeradora();
        empresa.setId(1L);
        empresa.setNome("EcoPoint");
        empresa.setEmail("ecopoint@email.com");
        empresa.setSenha("$2a$10$hashaqui");
        
        Mockito.when(empresaGeradoraRepository.findByEmail("ecopoint@email.com"))
        .thenReturn(Optional.of(empresa));

        Mockito.when(passwordEncoder.matches("1234", empresa.getSenha()))
        .thenReturn(true);

        mockMvc.perform(post("/EmpresaGeradora/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonLogin))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.nome").value("EcoPoint"));

    }

    @Test
    void falhaNologinAoColocarSenhaIncorreta() throws Exception {
        String jsonLogin = """
            {
                "email": "ecopoint@email.com",
                "senha": "senhaErrada"
    }         """;

        EmpresaGeradora empresa = new EmpresaGeradora();
        empresa.setId(1L);
        empresa.setNome("EcoPoint");
        empresa.setEmail("ecopoint@email.com");
        empresa.setSenha("$2a$10$hashaqui");
        
        Mockito.when(empresaGeradoraRepository.findByEmail("ecopoint@email.com"))
        .thenReturn(Optional.of(empresa));

        Mockito.when(passwordEncoder.matches("senhaErrada", empresa.getSenha()))
        .thenReturn(false);

        mockMvc.perform(post("/EmpresaGeradora/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonLogin))
        .andExpect(status().isUnauthorized())
        .andExpect(content().string("Senha incorreta"));
    }

    @Test
    void falhaNoLoginAoColocarEmailIncorreto() throws Exception {
        String jsonLogin = """
            {
                "email": "naoexisto@email.com",
                "senha": "1234"
            } """;
            
            EmpresaGeradora empresa = new EmpresaGeradora();
            empresa.setId(1L);
            empresa.setNome("EcoPoint");
            empresa.setEmail("naoexisto@email.com");
            empresa.setSenha("$2a$10$hashaqui");

            Mockito.when(empresaGeradoraRepository.findByEmail("naoexisto@email.com"))
            .thenReturn(Optional.empty());

        mockMvc.perform(post("/EmpresaGeradora/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonLogin))
        .andExpect(status().isUnauthorized())
        .andExpect(content().string("Email não encontrado"));
    }

    @Test
    void deveDeletarEmpresaGeradora() throws Exception {
        Mockito.doNothing().when(empresaGeradoraService).deletarEmpresaGeradora(1L);

        mockMvc.perform(delete("/EmpresaGeradora/1"))
        .andExpect(status().isOk());
    }

}
