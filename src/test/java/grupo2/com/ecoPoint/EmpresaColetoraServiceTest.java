package grupo2.com.ecoPoint;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import grupo2.com.ecoPoint.Model.Entity.EmpresaColetora;
import grupo2.com.ecoPoint.Model.Entity.ItemResiduo;
import grupo2.com.ecoPoint.Repository.EmpresaColetoraRepository;
import grupo2.com.ecoPoint.Service.EmpresaColetoraService;

public class EmpresaColetoraServiceTest {

    @Mock
    private EmpresaColetoraRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private EmpresaColetoraService service;

    private EmpresaColetora empresa;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        empresa = new EmpresaColetora();
        empresa.setId(1L);
        empresa.setEmail("teste@email.com");
        empresa.setSenha("senhaCriptografada");
    }


    @Test
    void testMetodoDeLoginComSucesso() {
        when(repository.findByEmail("teste@email.com"))
                .thenReturn(Optional.of(empresa));

        when(passwordEncoder.matches("123", "senhaCriptografada"))
                .thenReturn(true);

        EmpresaColetora resultado = service.login("teste@email.com", "123");

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
    }


    @Test
    void testMetodoDeLoginComEmailNaoEncontrado() {
        when(repository.findByEmail("naoexiste@email.com"))
                .thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            service.login("naoexiste@email.com", "123");
        });

        assertEquals("Email não encontrado!", ex.getMessage());
    }


    @Test
    void tesMetodoDetLoginComSenhaIncorreta() {
        when(repository.findByEmail("teste@email.com"))
                .thenReturn(Optional.of(empresa));

        when(passwordEncoder.matches("errada", "senhaCriptografada"))
                .thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            service.login("teste@email.com", "errada");
        });

        assertEquals("Senha incorreta!", ex.getMessage());
    }

    // ---------------------------------------------------------
    // SALVAR EMPRESA (CRIPTOGRAFIA)
    // ---------------------------------------------------------
    @Test
    void testSalvarEmpresaColetora() {
        EmpresaColetora nova = new EmpresaColetora();
        nova.setSenha("senha");

        when(passwordEncoder.encode("senha")).thenReturn("senhaCripto");
        when(repository.save(nova)).thenReturn(nova);

        EmpresaColetora resultado = service.salvarEmpresaColetora(nova);

        assertEquals("senhaCripto", resultado.getSenha());
    }


    @Test
    void testMetodoGetAllEmpresaColetoraDeveRetornarTodasAsEmpresas() {
        List<EmpresaColetora> lista = Arrays.asList(empresa);
        when(repository.findAll()).thenReturn(lista);

        List<EmpresaColetora> resultado = service.getAllEmpresaColetora();

        assertEquals(1, resultado.size());
    }


    @Test
    void testMetodoGetEmpresaColetoraByIdDeveRetornarEmpresaPorId() {
        when(repository.findEmpresaColetoraById(1L)).thenReturn(empresa);

        EmpresaColetora resultado = service.getEmpresaColetoraById(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
    }


    @Test
    void testMetodoAtualizarEmpresaColetora() {
        EmpresaColetora atualizada = new EmpresaColetora();
        atualizada.setNome("Nova Empresa");

        when(repository.findEmpresaColetoraById(1L)).thenReturn(empresa);
        when(repository.save(empresa)).thenReturn(empresa);

        EmpresaColetora resultado = service.atualizarEmpresaColetora(1L, atualizada);

        assertEquals("Nova Empresa", resultado.getNome());
    }

    // ---------------------------------------------------------
    // LISTAR ITENS DE RESÍDUO
    // ---------------------------------------------------------
    @Test
    void testMetodoGetItensByEmpresaColetoraIdDeveRetornarTodosOsItensDaEmpresaColetora() {
        empresa.setItensQueRecebe(Arrays.asList(new ItemResiduo(), new ItemResiduo()));

        when(repository.findEmpresaColetoraById(1L)).thenReturn(empresa);

        List<ItemResiduo> resultado = service.getItensByEmpresaColetoraId(1L);

        assertEquals(2, resultado.size());
    }


    @Test
    void testMetodoAtualizarItensColetoraDeveAtualizarOsItens() {
        List<ItemResiduo> novosItens = Arrays.asList(new ItemResiduo());

        when(repository.findEmpresaColetoraById(1L)).thenReturn(empresa);
        when(repository.save(empresa)).thenReturn(empresa);

        service.atualizarItensColetora(1L, novosItens);

        assertEquals(1, empresa.getItensQueRecebe().size());
    }
}

