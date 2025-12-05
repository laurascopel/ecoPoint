package grupo2.com.ecoPoint.Controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import grupo2.com.ecoPoint.Model.Entity.EmpresaColetora;
import grupo2.com.ecoPoint.Repository.EmpresaColetoraRepository;
import grupo2.com.ecoPoint.Service.EmpresaColetoraService;
import java.util.Map;

@RestController
@RequestMapping("/empresasColetoras")
public class EmpresaColetoraController {

    private final EmpresaColetoraService empresaColetoraService;
    
    @Autowired
    private EmpresaColetoraRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder; /* Baean no ecoaplication*/

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {

        String email = body.get("email");
        String senha = body.get("senha");

        EmpresaColetora empresa = repository.findByEmail(email).orElse(null); /* Buscar empresa pelo email*/

        if (empresa == null) {
            return ResponseEntity.status(401).body("Email não encontrado");
        }

        boolean senhaCorreta = passwordEncoder.matches(senha, empresa.getSenha()); /* verifica a senha com BCrypt*/

        if (!senhaCorreta) {
            return ResponseEntity.status(401).body("Senha incorreta");
        }
        return ResponseEntity.ok(empresa); /* Login correto */ /* Metodo do JPA */
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody EmpresaColetora empresaColetora) {
    empresaColetora.setSenha(passwordEncoder.encode(empresaColetora.getSenha()));
    EmpresaColetora salva = empresaColetoraService.salvarEmpresaColetora(empresaColetora);
    return ResponseEntity.ok(salva);
}

    public EmpresaColetoraController(EmpresaColetoraService empresaColetoraService) {
        this.empresaColetoraService = empresaColetoraService;
    }


    @GetMapping
    public List<EmpresaColetora> getAllEmpresaColetora() {
        return empresaColetoraService.getAllEmpresaColetora();
    }


    @GetMapping("/{id}")
    public EmpresaColetora getEmpresaColetoraById(@PathVariable Long id) {
        return empresaColetoraService.getEmpresaColetoraById(id);
    }


    @PostMapping
    public EmpresaColetora criarEmpresaColetora(@RequestBody EmpresaColetora empresaColetora) {
        return empresaColetoraService.salvarEmpresaColetora(empresaColetora);
    }

 
    @PutMapping("/{id}")
    public EmpresaColetora atualizEmpresaColetora(@PathVariable Long id, @RequestBody EmpresaColetora empresaColetora) {
        return empresaColetoraService.atualizarEmpresaColetora(id, empresaColetora);
    }


    @DeleteMapping("/{id}")
    public void deletarEmpresaColetora(@PathVariable Long id) {
        empresaColetoraService.deletarEmpresaColetora(id);
    }



}



