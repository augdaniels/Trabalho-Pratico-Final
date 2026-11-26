import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServicoTest {
    @BeforeEach
    void limparServicos() {
        for (int id = 101; id <= 110; id++) {
            Servico.excluir(id);
        }
    }

    @Test
    void deveRetornarIdCorreto() {
        Servico servico = new Servico(1, "Lavagem simples", 50.00);

        assertEquals(1, servico.getId());
    }

    @Test
    void deveRetornarIdZero() {
        Servico servico = new Servico(0, "Lavagem simples", 50.00);

        assertEquals(0, servico.getId());
    }
    @Test
    void deveAlterarId() {
        Servico servico = new Servico(1, "Lavagem simples", 50.00);

        servico.setId(2);

        assertEquals(2, servico.getId());
    }
    @Test
    void deveRetornarNomeCorreto() {
        Servico servico = new Servico(1, "Lavagem simples", 50.00);

        assertEquals("Lavagem simples", servico.getNome());
    }
    @Test
    void deveAlterarNome() {
        Servico servico = new Servico(1, "Lavagem simples", 50.00);
        servico.setNome("Lavagem completa");

        assertEquals("Lavagem completa", servico.getNome());
    }
    @Test
    void deveRetornarValorCorreto() {
        Servico servico = new Servico(1, "Lavagem simples", 50.00);

        assertEquals(50.00, servico.getValor(), 0.001);
    }
    @Test
    void deveAlterarValor() {
        Servico servico = new Servico(1, "Lavagem simples", 50.00);
        servico.setValor(80.00);

        assertEquals(80.00, servico.getValor(), 0.001);
    }
    @Test
    void deveRetornarIdMaior() {
        Servico servico = new Servico(100, "Lavagem simples", 50.00);

        assertEquals(100, servico.getId());
    }
    @Test
    void deveAlterarIdNovamente() {
        Servico servico = new Servico(1, "Lavagem simples", 50.00);
        servico.setId(100);
        assertEquals(100, servico.getId());
    }

    @Test
    void deveRetornarOutroNome() {
        Servico servico = new Servico(2, "Polimento", 100.00);
        assertEquals("Polimento", servico.getNome());
    }

    @Test
    void deveRetornarNomeComposto() {
        Servico servico = new Servico(3, "Lavagem completa", 80.00);
        assertEquals("Lavagem completa", servico.getNome());
    }

    @Test
    void deveAlterarNomeParaPolimento() {
        Servico servico = new Servico(1, "Lavagem simples", 50.00);
        servico.setNome("Polimento");
        assertEquals("Polimento", servico.getNome());
    }

    @Test
    void deveAlterarNomeParaHigienizacao() {
        Servico servico = new Servico(1, "Lavagem simples", 50.00);
        servico.setNome("Higienizacao");
        assertEquals("Higienizacao", servico.getNome());
    }

    @Test
    void deveRetornarValorZero() {
        Servico servico = new Servico(1, "Lavagem simples", 0.00);
        assertEquals(0.00, servico.getValor(), 0.001);
    }

    @Test
    void deveRetornarValorDecimal() {
        Servico servico = new Servico(1, "Lavagem simples", 59.90);
        assertEquals(59.90, servico.getValor(), 0.001);
    }

    @Test
    void deveAlterarValorParaZero() {
        Servico servico = new Servico(1, "Lavagem simples", 50.00);
        servico.setValor(0.00);
        assertEquals(0.00, servico.getValor(), 0.001);
    }

    @Test
    void deveAlterarValorDecimal() {
        Servico servico = new Servico(1, "Lavagem simples", 50.00);
        servico.setValor(79.90);
        assertEquals(79.90, servico.getValor(), 0.001);
    }
    @Test
    void deveAlterarIdParaZero() {
        Servico servico = new Servico(1, "Lavagem simples", 50.00);
        servico.setId(0);

        assertEquals(0, servico.getId());
    }
    @Test
    void deveCadastrarServico() {
        Servico servico = new Servico(101, "Lavagem simples", 50.00);

        Servico.cadastrar(servico);

        assertEquals(servico, Servico.consultar(101));
    }

    @Test
    void deveConsultarServico() {
        Servico servico = new Servico(102, "Polimento", 100.00);

        Servico.cadastrar(servico);

        assertEquals(servico, Servico.consultar(102));
    }

    @Test
    void deveAtualizarServico() {
        Servico servico = new Servico(103, "Lavagem simples", 50.00);

        Servico.cadastrar(servico);
        Servico.atualizar(103, "Lavagem completa", 80.00);

        assertEquals("Lavagem completa", Servico.consultar(103).getNome());
        assertEquals(80.00, Servico.consultar(103).getValor(), 0.001);
    }

    @Test
    void deveExcluirServico() {
        Servico servico = new Servico(104, "Polimento", 100.00);

        Servico.cadastrar(servico);
        Servico.excluir(104);

        assertEquals(null, Servico.consultar(104));
    }
    @Test
    void deveCadastrarOutroServico() {
        Servico servico = new Servico(105, "Higienizacao", 150.00);
        Servico.cadastrar(servico);

        assertEquals(servico, Servico.consultar(105));
    }

    @Test
    void deveCadastrarServicoComValorZero() {
        Servico servico = new Servico(106, "Lavagem", 0.00);
        Servico.cadastrar(servico);

        assertEquals(servico, Servico.consultar(106));
    }

    @Test
    void deveConsultarServicoInexistente() {
        assertEquals(null, Servico.consultar(999));
    }

    @Test
    void deveConsultarValorDoServico() {
        Servico.cadastrar(new Servico(107, "Polimento", 90.00));

        assertEquals(90.00, Servico.consultar(107).getValor(), 0.001);
    }

    @Test
    void deveAtualizarValorParaZero() {
        Servico.cadastrar(new Servico(108, "Lavagem", 50.00));
        Servico.atualizar(108, "Lavagem", 0.00);

        assertEquals(0.00, Servico.consultar(108).getValor(), 0.001);
    }

    @Test
    void deveTentarAtualizarServicoInexistente() {
        Servico.atualizar(998, "Lavagem", 50.00);

        assertEquals(null, Servico.consultar(998));
    }

    @Test
    void deveExcluirServicoSemAfetarOutro() {
        Servico.cadastrar(new Servico(109, "Lavagem", 50.00));
        Servico.cadastrar(new Servico(110, "Polimento", 100.00));

        Servico.excluir(109);

        assertEquals(null, Servico.consultar(109));
        assertEquals("Polimento", Servico.consultar(110).getNome());
    }

    @Test
    void deveTentarExcluirServicoInexistente() {
        Servico.excluir(997);

        assertEquals(null, Servico.consultar(997));
    }
}