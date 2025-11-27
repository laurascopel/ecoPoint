package grupo2.com.ecoPoint.Service;

import java.util.List;
import org.springframework.stereotype.Service;

import grupo2.com.ecoPoint.Model.Entity.EmpresaColetora;
import grupo2.com.ecoPoint.Repository.EmpresaColetoraRepository;

@Service
public class EmpresaColetoraService {

    private final EmpresaColetoraRepository empresaColetoraRepository;

    
    public EmpresaColetoraService(EmpresaColetoraRepository empresaColetoraRepository,  Long id) {
        this.empresaColetoraRepository = empresaColetoraRepository;
    
    }

    public List<EmpresaColetora> getAllEmpresaColetora() {
        return empresaColetoraRepository.findAll();
    }

    public EmpresaColetora getEmpresaColetoraById(Long id) {
        return empresaColetoraRepository.findEmpresaColetoraById(id);       
    }

    public EmpresaColetora salvarEmpresaColetora(EmpresaColetora empresaColetora) {
        return empresaColetoraRepository.save(empresaColetora);
    }

    public EmpresaColetora atualizarEmpresaColetora(Long id, EmpresaColetora empresaColetoraAtualizada) {
        EmpresaColetora empresaColetora = empresaColetoraRepository.findEmpresaColetoraById(id);

        if (empresaColetora == null) {
            throw new RuntimeException("Não existe");
        }

        empresaColetora.setNome(empresaColetoraAtualizada.getNome());
        empresaColetora.setEndereco(empresaColetoraAtualizada.getEndereco());
        empresaColetora.setCnpj(empresaColetoraAtualizada.getCnpj());
        empresaColetora.setHorarioFuncionamento(empresaColetoraAtualizada.getHorarioFuncionamento());
        empresaColetora.setData(empresaColetoraAtualizada.getData());
        empresaColetora.setTelefone(empresaColetoraAtualizada.getTelefone());
        empresaColetora.setDescricao(empresaColetoraAtualizada.getDescricao());

        return empresaColetoraRepository.save(empresaColetora);

    };

    public void deletarEmpresaColetora(Long id) {
        empresaColetoraRepository.deleteById(id);
    }
}

    

