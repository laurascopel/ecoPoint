package grupo2.com.ecoPoint;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.nio.charset.StandardCharsets;

import org.mockito.Mockito;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import grupo2.com.ecoPoint.Controller.ItemResiduoController;
import grupo2.com.ecoPoint.Model.Entity.ItemResiduo;
import grupo2.com.ecoPoint.Service.ItemResiduoService;

@WebMvcTest(ItemResiduoController.class)
@AutoConfigureMockMvc(addFilters = false)
class ItemResiduoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemResiduoService itemResiduoService;

  
    @Test
    void getItemResiduoById() throws Exception {
        ItemResiduo item = new ItemResiduo();
        item.setId(1L);
        item.setNome("Plástico");

        when(itemResiduoService.getItemResiduoById(1L)).thenReturn(item);

        mockMvc.perform(get("/itemresiduo/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Plástico"));
    }

  
    @Test
    void atualizarItemResiduo() throws Exception {
        String json = """
            {
                "nome": "Vidro"
            }
        """;

        ItemResiduo atualizado = new ItemResiduo();
        atualizado.setId(1L);
        atualizado.setNome("Vidro");

        when(itemResiduoService.atualizarItemResiduo(
                Mockito.eq(1L), Mockito.any(ItemResiduo.class)
        )).thenReturn(atualizado);

        mockMvc.perform(put("/itemresiduo/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.getBytes(StandardCharsets.UTF_8)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Vidro"));
    }

 
    @Test
    void deletarItemResiduo() throws Exception {
        doNothing().when(itemResiduoService).deletarItemResiduo(1L);

        mockMvc.perform(delete("/itemresiduo/1"))
                .andExpect(status().isOk());
    }
}
