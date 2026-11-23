import java.util.ArrayList;
import java.util.List;

public class AgendamentoRepository{
    private List<Agendamento> agendamentos=new ArrayList<>();

    public void salvar(Agendamento agendamento){
        agendamentos.add(agendamento);
    }

    public List<Agendamento> listarTodos(){
        return agendamentos;
    }

    public Agendamento buscarPorId(int id){
        for(Agendamento a : agendamentos){
            if(a.getIdAgendamento()==id){
                return a;
            }
        }
        return null;
    }
}
