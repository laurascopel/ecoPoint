package grupo2.com.ecoPoint.Service;


import java.util.List;

import org.springframework.stereotype.Service;

import grupo2.com.ecoPoint.Model.Entity.EmpresaGeradora;
import grupo2.com.ecoPoint.Repository.EmpresaGeradoraRepository;

@Service
public class EmpresaGeradoraService {

    private final EmpresaGeradoraRepository empresaGeradoraRepository;


    public EmpresaGeradoraService(EmpresaGeradoraRepository empresaGeradoraRepository) {
        this.empresaGeradoraRepository = empresaGeradoraRepository;
    }

    //GET
    public List<EmpresaGeradora>getAllEmpresaGeradora() {
        return empresaGeradoraRepository.findAll();
    }

    // GET BY ID
    public EmpresaGeradora getEmpresaGeradoraById(Integer id) {
        return empresaGeradoraRepository.findEmpresaGeradoraById(id);       
    }

    // CREATE   
    public EmpresaGeradora salvarEmpresaGeradora(EmpresaGeradora empresaGeradora) {
        return empresaGeradoraRepository.save(empresaGeradora);
    }

    // UPDATE
    public EmpresaGeradora atualizarEmpresaGeradora(Integer id, EmpresaGeradora novaEmpresaGeradora) {
        EmpresaGeradora empresaGeradora = empresaGeradoraRepository.findEmpresaGeradoraById(id);

     if (empresaGeradora == null) {
            throw new RuntimeException("Não existe");
        }

        empresaGeradora.setNome(novaEmpresaGeradora.getNome());
        empresaGeradora.setEndereco(novaEmpresaGeradora.getEndereco());
        empresaGeradora.setTelefone(novaEmpresaGeradora.getTelefone());
        empresaGeradora.setCnpj(novaEmpresaGeradora.getCnpj());

        return empresaGeradoraRepository.save(empresaGeradora);
    };

    // DELETE
    public void deletarEmpresaGeradora(Integer id) {
        empresaGeradoraRepository.deleteById(id);
    }
}

    

