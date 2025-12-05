package grupo2.com.ecoPoint.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import grupo2.com.ecoPoint.Model.Entity.EmpresaGeradora;
import grupo2.com.ecoPoint.Repository.EmpresaGeradoraRepository;

@Service
public class EmpresaGeradoraService {

    private final EmpresaGeradoraRepository empresaGeradoraRepository;

    @Autowired
    private EmpresaGeradoraRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public EmpresaGeradora login(String email, String senhaDigitada) {
    EmpresaGeradora empresa = repository.findByEmail(email) /* procurar a empresa geradora pelo email*/
    .orElseThrow(() -> new RuntimeException("Email não encontrado!")); /* abre uma exceção se nao encontrar um email*/

    boolean senhaCorreta = passwordEncoder.matches(senhaDigitada, empresa.getSenha()); /* vai ver se a senha é igual a senha q ta no banco*/

    if (!senhaCorreta) {
        throw new RuntimeException("Senha incorreta!"); /* Se não for a senha vai passar a mensagem*/
    }

    return empresa; /* login ok*/
    }

    public EmpresaGeradora salvarEmpresaGeradora(EmpresaGeradora empresa) {
    String senhaCriptografada = passwordEncoder.encode(empresa.getSenha());  /* Criptografa antes de salvar*/
    empresa.setSenha(senhaCriptografada);
        return repository.save(empresa); /*substitui a senha original por criptografada e salva TODA EMPRESA no banco*/
    }

    
    public EmpresaGeradoraService(EmpresaGeradoraRepository empresaGeradoraRepository) {
        this.empresaGeradoraRepository = empresaGeradoraRepository;
    
    }

    public List<EmpresaGeradora> getAllEmpresaGeradora() {
        return empresaGeradoraRepository.findAll();
    }

    public EmpresaGeradora getEmpresaGeradoraById(Long id) {
        return empresaGeradoraRepository.findEmpresaGeradoraById(id);       
    }

    public EmpresaGeradora atualizarEmpresaGeradora(Long id, EmpresaGeradora empresaGeradoraAtualizada) {
        EmpresaGeradora empresaGeradora = empresaGeradoraRepository.findEmpresaGeradoraById(id);

        empresaGeradora.setNome(empresaGeradoraAtualizada.getNome());
        empresaGeradora.setCnpj(empresaGeradoraAtualizada.getCnpj());
        empresaGeradora.setEndereco(empresaGeradoraAtualizada.getEndereco());
        empresaGeradora.setTelefone(empresaGeradoraAtualizada.getTelefone());

        return empresaGeradoraRepository.save(empresaGeradora);
        
    };

    public void deletarEmpresaGeradora(Long id) {
        empresaGeradoraRepository.deleteById(id);
    }
}
    