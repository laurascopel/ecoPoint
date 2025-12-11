package grupo2.com.ecoPoint;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import grupo2.com.ecoPoint.Model.Entity.EmpresaGeradora;
import grupo2.com.ecoPoint.Repository.EmpresaGeradoraRepository;
import grupo2.com.ecoPoint.Service.EmpresaGeradoraService;

class EmpresaGeradoraServiceTest {

    @Mock
    private EmpresaGeradoraRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private EmpresaGeradoraService service;

    private EmpresaGeradora empresa;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        empresa = new EmpresaGeradora();
        empresa.setId(1L);
        empresa.setEmail("teste@email.com");
        empresa.setSenha("senhaCriptografada");
        empresa.setNome("Empresa X");
        empresa.setCnpj("123");
        empresa.setEndereco("Rua A");
        empresa.setTelefone("9999-0000");
    }



    @Test
    void deveLogarComSucesso() {
        when(repository.findByEmail("teste@email.com"))
                .thenReturn(Optional.of(empresa));

        when(passwordEncoder.matches("123", "senhaCriptografada"))
                .thenReturn(true);

        EmpresaGeradora resultado = service.login("teste@email.com", "123");

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
    }

    @Test
    void deveFalharQuandoEmailIncorreto() {
        when(repository.findByEmail("naoexiste@email.com"))
                .thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            service.login("naoexiste@email.com", "123");
        });

        assertEquals("Email não encontrado!", ex.getMessage());
    }

    @Test
    void deveFalharQuandoSenhaIncorreta() {
        when(repository.findByEmail("teste@email.com"))
                .thenReturn(Optional.of(empresa));

        when(passwordEncoder.matches("senhaErrada", "senhaCriptografada"))
                .thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            service.login("teste@email.com", "senhaErrada");
        });

        assertEquals("Senha incorreta!", ex.getMessage());
    }


    @Test
    void deveSalvarEmpresaComSenhaCriptografada() {
        EmpresaGeradora nova = new EmpresaGeradora();
        nova.setSenha("123");

        when(passwordEncoder.encode("123"))
                .thenReturn("senhaCripto");

        when(repository.save(nova))
                .thenReturn(nova);

        EmpresaGeradora resultado = service.salvarEmpresaGeradora(nova);

        assertEquals("senhaCripto", nova.getSenha());
        verify(repository).save(nova);
    }



    @Test
    void deveRetornarListaDeEmpresas() {
        when(repository.findAll()).thenReturn(List.of(empresa));

        List<EmpresaGeradora> lista = service.getAllEmpresaGeradora();

        assertEquals(1, lista.size());
        assertEquals("Empresa X", lista.get(0).getNome());
    }



    @Test
    void deveRetornarEmpresaPorId() {
        when(repository.findEmpresaGeradoraById(1L))
                .thenReturn(empresa);

        EmpresaGeradora resultado = service.getEmpresaGeradoraById(1L);

        assertEquals("Empresa X", resultado.getNome());
    }



    @Test
    void deveAtualizarEmpresaGeradora() {
        EmpresaGeradora nova = new EmpresaGeradora();
        nova.setNome("Novo Nome");
        nova.setCnpj("456");
        nova.setEndereco("Rua Nova");
        nova.setTelefone("8888-1111");

        when(repository.findEmpresaGeradoraById(1L))
                .thenReturn(empresa);

        when(repository.save(any(EmpresaGeradora.class)))
                .thenReturn(empresa);

        EmpresaGeradora atualizada = service.atualizarEmpresaGeradora(1L, nova);

        assertEquals("Novo Nome", atualizada.getNome());
        assertEquals("456", atualizada.getCnpj());
        assertEquals("Rua Nova", atualizada.getEndereco());
        assertEquals("8888-1111", atualizada.getTelefone());
    }



    @Test
    void deveDeletarEmpresa() {
        doNothing().when(repository).deleteById(1L);

        service.deletarEmpresaGeradora(1L);

        verify(repository, times(1)).deleteById(1L);
    }
}
