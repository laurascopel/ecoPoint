package grupo2.com.ecoPoint.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import grupo2.com.ecoPoint.Model.Entity.EmpresaColetora;
import grupo2.com.ecoPoint.Model.Entity.ItemResiduo;
import grupo2.com.ecoPoint.Repository.EmpresaColetoraRepository;

@Service
public class EmpresaColetoraService {

@Autowired /* Serve para injeção de dependência, private EmpresaColetoraRepository repository = new */
private EmpresaColetoraRepository repository; 

@Autowired
private PasswordEncoder passwordEncoder; /* Do metodo criptografar */

    public EmpresaColetora login(String email, String senhaDigitada) {
    EmpresaColetora empresa = repository.findByEmail(email) /* procurar a empresa coletora pelo email*/
            .orElseThrow(() -> new RuntimeException("Email não encontrado!")); /* abre uma exceção se nao encontrar um email*/

    boolean senhaCorreta = passwordEncoder.matches(senhaDigitada, empresa.getSenha()); /* vai ver se a senha é igual a senha q ta no banco*/

    if (!senhaCorreta) {
        throw new RuntimeException("Senha incorreta!"); /* Se não for a senha vai passar a mensagem*/
    }

    return empresa; /* login ok*/
    }

    public EmpresaColetora salvarEmpresaColetora(EmpresaColetora empresa) {
    String senhaCriptografada = passwordEncoder.encode(empresa.getSenha());  /* Criptografa antes de salvar*/
    empresa.setSenha(senhaCriptografada);
        return repository.save(empresa); /*substitui a senha original por criptografada e salva TODA EMPRESA no banco*/
    }

    public List<EmpresaColetora> getAllEmpresaColetora() {
        return repository.findAll();
    }

    public EmpresaColetora getEmpresaColetoraById(Long id) {
        return repository.findEmpresaColetoraById(id);       
    }

    public EmpresaColetora atualizarEmpresaColetora(Long id, EmpresaColetora empresaColetoraAtualizada) {
        EmpresaColetora empresaColetora = repository.findEmpresaColetoraById(id);

        empresaColetora.setNome(empresaColetoraAtualizada.getNome());
        empresaColetora.setEndereco(empresaColetoraAtualizada.getEndereco());
        empresaColetora.setCnpj(empresaColetoraAtualizada.getCnpj());
        empresaColetora.setHorarioFuncionamento(empresaColetoraAtualizada.getHorarioFuncionamento());
        empresaColetora.setData(empresaColetoraAtualizada.getData());
        empresaColetora.setTelefone(empresaColetoraAtualizada.getTelefone());
        empresaColetora.setDescricao(empresaColetoraAtualizada.getDescricao());

        return repository.save(empresaColetora);

    };

    public void deletarEmpresaColetora(Long id) {
        repository.deleteById(id);
    }

    public List<ItemResiduo> getItensByEmpresaColetoraId(Long id) {
        EmpresaColetora empresaColetora = repository.findEmpresaColetoraById(id);
        return empresaColetora.getItensQueRecebe();
    }
    
    public void atualizarItensColetora(Long empresaColetoraId, List<ItemResiduo> itemResiduo) {
        EmpresaColetora empresaColetora = repository.findEmpresaColetoraById(empresaColetoraId);
        
        empresaColetora.setItensQueRecebe(itemResiduo);
        
        repository.save(empresaColetora);
}
}

    

