package grupo2.com.ecoPoint;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import grupo2.com.ecoPoint.Controller.CertificadoController;
import grupo2.com.ecoPoint.Model.Entity.Certificado;
import grupo2.com.ecoPoint.Service.CertificadoService;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(CertificadoController.class)
public class CertificadoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CertificadoService certificadoService;

    @Test
    void getAllCertificados() throws Exception {
        Certificado c1 = new Certificado();
        c1.setId(1L);
        c1.setTipo("PDF");

        Certificado c2 = new Certificado();
        c2.setId(2L);
        c2.setTipo("PDF");

        when(certificadoService.getAllCertificado())
            .thenReturn(List.of(c1, c2));

        mockMvc.perform(get("/certificados"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1L))
            .andExpect(jsonPath("$[1].id").value(2L));
    }

    @Test
    void getCertificadoById() throws Exception {
        Certificado c = new Certificado();
        c.setId(1L);
        c.setTipo("PDF");

        when(certificadoService.getCertificadoById(1L))
            .thenReturn(c);

        mockMvc.perform(get("/certificados/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.tipo").value("PDF"));
    }
}
