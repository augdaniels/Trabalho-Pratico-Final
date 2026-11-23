import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AgendamentoTest{
    private Cliente cliente;
    private Veiculo veiculo;
    private Funcionario funcionario;
    private Agendamento agendamento;

    @BeforeEach
    public void setUp(){
        cliente=new Cliente("Joao","31999999999");
        veiculo=new Veiculo("ABC1234","Gol",cliente);
        funcionario=new Funcionario("Maria","Mecanico");
        agendamento=new Agendamento(LocalDateTime.of(2026,12,1,9,0),cliente,veiculo,funcionario);
    }

    @Test
    public void deveGerarIdAutomatico(){
        Agendamento outro=new Agendamento(LocalDateTime.of(2026,12,2,10,0),cliente,veiculo,funcionario);
        assertEquals(agendamento.getIdAgendamento()+1,outro.getIdAgendamento());
    }

    @Test
    public void deveRetornarDataHora(){
        assertEquals(LocalDateTime.of(2026,12,1,9,0),agendamento.getDataHora());
    }

    @Test
    public void deveAlterarDataHora(){
        LocalDateTime nova=LocalDateTime.of(2026,12,5,14,30);
        agendamento.setDataHora(nova);
        assertEquals(nova,agendamento.getDataHora());
    }

    @Test
    public void deveRetornarCliente(){
        assertEquals(cliente,agendamento.getCliente());
    }

    @Test
    public void deveAlterarCliente(){
        Cliente nova=new Cliente("Pedro","31888888888");
        agendamento.setCliente(nova);
        assertEquals(nova,agendamento.getCliente());
    }

    @Test
    public void deveRetornarVeiculo(){
        assertEquals(veiculo,agendamento.getVeiculo());
    }

    @Test
    public void deveAlterarVeiculo(){
        Veiculo nova=new Veiculo("XYZ9876","Uno",cliente);
        agendamento.setVeiculo(nova);
        assertEquals(nova,agendamento.getVeiculo());
    }

    @Test
    public void deveRetornarFuncionario(){
        assertEquals(funcionario,agendamento.getFuncionario());
    }
}
