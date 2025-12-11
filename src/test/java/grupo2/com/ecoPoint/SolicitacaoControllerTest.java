package grupo2.com.ecoPoint;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import grupo2.com.ecoPoint.Controller.SolicitacaoController;
import grupo2.com.ecoPoint.Model.Entity.Solicitacao;
import grupo2.com.ecoPoint.Service.SolicitacaoService;

@WebMvcTest(SolicitacaoController.class)
@AutoConfigureMockMvc(addFilters = false) // Desabilita Spring Security nos testes

public class SolicitacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SolicitacaoService solicitacaoService;

    @Test
    void deveCriarUmaSolicitacao() throws Exception {

        String json = """
            {
                "geradoraId": 1,
                "coletoraId": 2,
                "dataAgendada": "2025-02-10",
                "itensIds": [5, 6]
            }
            """;

        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setId(1L);

        when(solicitacaoService.criarSolicitacao(
                eq(1L),
                eq(2L),
                eq(LocalDate.parse("2025-02-10")),
                eq(List.of(5L, 6L))
        )).thenReturn(solicitacao);

        mockMvc.perform(post("/solicitacao")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1L));
    }


    @Test
    void deveAceitarSolicitacao() throws Exception {
        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setId(1L);

        when(solicitacaoService.aceitarSolicitacao(1L)).thenReturn(solicitacao);

        mockMvc.perform(put("/solicitacao/1/aceitar"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1L));
    }


    @Test
    void deveRecusarSolicitacao() throws Exception {
        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setId(1L);

        when(solicitacaoService.recusarSolicitacao(1L)).thenReturn(solicitacao);

        mockMvc.perform(put("/solicitacao/1/recusar"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1L));
    }


    @Test
    void deveColetarSolicitacao() throws Exception {
        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setId(1L);

        when(solicitacaoService.coletarSolicitacao(1L)).thenReturn(solicitacao);

        mockMvc.perform(put("/solicitacao/1/coletar"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void deveEnviarCertificado() throws Exception {
        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setId(1L);

        when(solicitacaoService.adicionarCertificado(
            eq(1L),
            any(byte[].class),
            eq("PDF")
        )).thenReturn(solicitacao);

        MockMultipartFile arquivo = new MockMultipartFile(
            "arquivo",
            "certificado.pdf",
            MediaType.APPLICATION_PDF_VALUE,
            "Conteúdo do certificado".getBytes()
        );

        mockMvc.perform(multipart("/solicitacao/1/certificado")
                .file(arquivo)
                .param("tipo", "PDF"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1L));
    }

}
    

