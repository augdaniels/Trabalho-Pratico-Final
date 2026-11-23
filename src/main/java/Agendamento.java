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

    public LocalDateTime getDataHora(){
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora){
        this.dataHora=dataHora;
    }

    public Cliente getCliente(){
        return cliente;
    }

    public void setCliente(Cliente cliente){
        this.cliente=cliente;
    }

    public Veiculo getVeiculo(){
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo){
        this.veiculo=veiculo;
    }

    public Funcionario getFuncionario(){
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario){
        this.funcionario=funcionario;
    }

    public StatusAgendamento getStatus(){
        return status;
    }

    public List<Servico> getServicos(){
        return servicos;
    }
}
