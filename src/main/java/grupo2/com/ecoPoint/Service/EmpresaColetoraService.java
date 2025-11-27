package grupo2.com.ecoPoint.Service;

import java.util.List;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.stereotype.Service;

import grupo2.com.ecoPoint.Model.Entity.EmpresaColetora;
import grupo2.com.ecoPoint.Repository.EmpresaColetoraRepository;

@Service
public class EmpresaColetoraService {

    private final EmpresaColetoraRepository empresaColetoraRepository;

    
    public EmpresaColetoraService(EmpresaColetoraRepository empresaColetoraRepository,  List<Long> idsItens) {
        this.empresaColetoraRepository = empresaColetoraRepository;
    
    }

    public List<EmpresaColetora> buscarPorItem(Long itemId) {
        return empresaColetoraRepository.findByItensQueRecebe_Id(itemId);
    
    }

    //GET
    public List<EmpresaColetora>getAllEmpresaColetora() {
        return empresaColetoraRepository.findAll();
    }

    // GET BY ID
    public EmpresaColetora getEmpresaColetoraById(Long id) {
        return empresaColetoraRepository.findEmpresaColetoraById(id);       
    }

    // CREATE   
    public EmpresaColetora salvarEmpresaColetora(EmpresaColetora empresaColetora) {
        return empresaColetoraRepository.save(empresaColetora);
    }

    // UPDATE
    public EmpresaColetora atualizarEmpresaColetora(Long id, EmpresaColetora novaEmpresaColetora) {
        EmpresaColetora empresaColetora = empresaColetoraRepository.findEmpresaColetoraById(id);

     if (empresaColetora == null) {
            throw new RuntimeException("Não existe");
        }

        empresaColetora.setNome(novaEmpresaColetora.getNome());
        empresaColetora.setEndereco(novaEmpresaColetora.getEndereco());
        empresaColetora.setHorarioFuncionamento(novaEmpresaColetora.getHorarioFuncionamento());
        empresaColetora.setTelefone(novaEmpresaColetora.getTelefone());
        empresaColetora.setDescricao(novaEmpresaColetora.getDescricao());


        return empresaColetoraRepository.save(empresaColetora);
    };

    // DELETE
    public void deletarEmpresaColetora(Integer id) {
        empresaColetoraRepository.deleteById(id);
    }
}

    

