import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Agendamento{
    private static int proximoId=1;

    private int idAgendamento;
    private LocalDateTime dataHora;
    private Cliente cliente;
    private Veiculo veiculo;
    private Funcionario funcionario;
    private List<Servico> servicos;
    private StatusAgendamento status;

    public Agendamento(LocalDateTime dataHora, Cliente cliente, Veiculo veiculo, Funcionario funcionario){
        this.idAgendamento=proximoId++;
        this.dataHora=dataHora;
        this.cliente=cliente;
        this.veiculo=veiculo;
        this.funcionario=funcionario;
        this.servicos=new ArrayList<>();
        this.status=StatusAgendamento.AGENDADO;
    }

    public int getIdAgendamento(){
        return idAgendamento;
    }
}
