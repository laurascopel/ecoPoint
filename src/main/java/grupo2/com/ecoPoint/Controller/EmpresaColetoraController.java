package grupo2.com.ecoPoint.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import grupo2.com.ecoPoint.Model.Entity.EmpresaColetora;
import grupo2.com.ecoPoint.Service.EmpresaColetoraService;

@RestController
@RequestMapping("/empresasColetoras")
public class EmpresaColetoraController {

    private final EmpresaColetoraService empresaColetoraService;

    public EmpresaColetoraController(EmpresaColetoraService empresaColetoraService) {
        this.empresaColetoraService = empresaColetoraService;
    }

    // GET ALL
    @GetMapping
    public List<EmpresaColetora> getAllEmpresaColetora() {
        return empresaColetoraService.getAllEmpresaColetora();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public EmpresaColetora getEmpresaColetoraById(@PathVariable Long id) {
        return empresaColetoraService.getEmpresaColetoraById(id);
    }

 // CREATE
    @PostMapping
    public EmpresaColetora criarEmpresaColetora(@RequestBody EmpresaColetora empresaColetora) {
        return empresaColetoraService.salvarEmpresaColetora(empresaColetora);
    }

    // UPDATE
    @PutMapping("/{id}")
    public EmpresaColetora atualizEmpresaColetora(@PathVariable Integer id, @RequestBody EmpresaColetora empresaColetora) {
        return empresaColetoraService.atualizarEmpresaColetora(id, empresaColetora);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deletarEmpresaColetora(@PathVariable Integer id) {
        empresaColetoraService.deletarEmpresaColetora(id);
    }
}



