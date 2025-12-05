package grupo2.com.ecoPoint.Controller;
import java.util.List;
import java.util.Map;
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
import grupo2.com.ecoPoint.Model.Entity.EmpresaGeradora;
import grupo2.com.ecoPoint.Repository.EmpresaGeradoraRepository;
import grupo2.com.ecoPoint.Service.EmpresaGeradoraService;

@RestController
@RequestMapping("/EmpresaGeradora")
public class EmpresaGeradoraController {

    @Autowired
    private EmpresaGeradoraRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String senha = body.get("senha");

        EmpresaGeradora empresa = repository.findByEmail(email).orElse(null);

        if (empresa == null) {
            return ResponseEntity.status(401).body("Email não encontrado");
        }

        boolean senhaCorreta = passwordEncoder.matches(senha, empresa.getSenha());

        if (!senhaCorreta) {
            return ResponseEntity.status(401).body("Senha incorreta");
        }

        return ResponseEntity.ok(empresa);
    }

 @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody EmpresaGeradora empresaGeradora) {
    EmpresaGeradora salva = empresaGeradoraService.salvarEmpresaGeradora(empresaGeradora);
    return ResponseEntity.ok(salva);
}

    private final EmpresaGeradoraService empresaGeradoraService;

    public EmpresaGeradoraController(EmpresaGeradoraService empresaGeradoraService) {
        this.empresaGeradoraService = empresaGeradoraService;
    }

    // GET ALL
    @GetMapping
    public List<EmpresaGeradora> getAllEmpresaGeradora() {
        return empresaGeradoraService.getAllEmpresaGeradora();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public EmpresaGeradora getEmpresaGeradoraById(@PathVariable Long id) {
        return empresaGeradoraService.getEmpresaGeradoraById(id);
    }

 // CREATE
    @PostMapping
    public EmpresaGeradora criarEmpresaGeradora(@RequestBody EmpresaGeradora empresaGeradora) {
        return empresaGeradoraService.salvarEmpresaGeradora(empresaGeradora);
    }

    // UPDATE
    @PutMapping("/{id}")
    public EmpresaGeradora atualizEmpresaGeradora(@PathVariable Long id, @RequestBody EmpresaGeradora empresaGeradora) {
        return empresaGeradoraService.atualizarEmpresaGeradora(id, empresaGeradora);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deletarEmpresaGeradora(@PathVariable Long id) {
        empresaGeradoraService.deletarEmpresaGeradora(id);
    }
}