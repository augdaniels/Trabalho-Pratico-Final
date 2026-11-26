import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AgendamentoRepositoryTest{
    private AgendamentoRepository repository;
    private Agendamento agendamento;

    @BeforeEach
    public void setUp(){
        repository=new AgendamentoRepository();
        Cliente cliente=new Cliente("Joao","31999999999");
        Veiculo veiculo=new Veiculo("ABC1234","Gol",cliente);
        Funcionario funcionario=new Funcionario("Maria","Mecanico");
        agendamento=new Agendamento(LocalDateTime.of(2026,12,1,9,0),cliente,veiculo,funcionario);
    }

    @Test
    public void deveSalvarEListarTodos(){
        repository.salvar(agendamento);
        assertEquals(1,repository.listarTodos().size());
    }

    @Test
    public void deveBuscarPorId(){
        repository.salvar(agendamento);
        Agendamento achado=repository.buscarPorId(agendamento.getIdAgendamento());
        assertEquals(agendamento,achado);
    }

    @Test
    public void deveRetornarNullQuandoNaoAcharId(){
        assertNull(repository.buscarPorId(9999));
    }

    @Test
    public void deveRemover(){
        repository.salvar(agendamento);
        repository.remover(agendamento.getIdAgendamento());
        assertTrue(repository.listarTodos().isEmpty());
    }
}
