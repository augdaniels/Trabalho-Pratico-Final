import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ServicoTest {

    // Compara BigDecimal pelo valor, ignorando a escala (50 e 50.00 são iguais)
    private static void assertValor(String esperado, BigDecimal real) {
        assertEquals(0, new BigDecimal(esperado).compareTo(real));
    }

    // ---------- id automático ----------

    @Test
    void deveGerarIdsSequenciais() {
        Servico primeiro = new Servico("Lavagem simples", new BigDecimal("50.00"));
        Servico segundo = new Servico("Polimento", new BigDecimal("100.00"));

        assertEquals(primeiro.getId() + 1, segundo.getId());
    }

    // ---------- nome ----------

    @Test
    void deveRetornarNomeCorreto() {
        Servico servico = new Servico("Lavagem simples", new BigDecimal("50.00"));

        assertEquals("Lavagem simples", servico.getNome());
    }

    @Test
    void deveAlterarNome() {
        Servico servico = new Servico("Lavagem simples", new BigDecimal("50.00"));
        servico.setNome("Lavagem completa");

        assertEquals("Lavagem completa", servico.getNome());
    }

    @Test
    void deveRetornarOutroNome() {
        Servico servico = new Servico("Polimento", new BigDecimal("100.00"));

        assertEquals("Polimento", servico.getNome());
    }

    @Test
    void deveRetornarNomeComposto() {
        Servico servico = new Servico("Lavagem completa", new BigDecimal("80.00"));

        assertEquals("Lavagem completa", servico.getNome());
    }

    @Test
    void deveAlterarNomeParaPolimento() {
        Servico servico = new Servico("Lavagem simples", new BigDecimal("50.00"));
        servico.setNome("Polimento");

        assertEquals("Polimento", servico.getNome());
    }

    @Test
    void deveAlterarNomeParaHigienizacao() {
        Servico servico = new Servico("Lavagem simples", new BigDecimal("50.00"));
        servico.setNome("Higienizacao");

        assertEquals("Higienizacao", servico.getNome());
    }

    // ---------- valor ----------

    @Test
    void deveRetornarValorCorreto() {
        Servico servico = new Servico("Lavagem simples", new BigDecimal("50.00"));

        assertValor("50.00", servico.getValor());
    }

    @Test
    void deveAlterarValor() {
        Servico servico = new Servico("Lavagem simples", new BigDecimal("50.00"));
        servico.setValor(new BigDecimal("80.00"));

        assertValor("80.00", servico.getValor());
    }

    @Test
    void deveRetornarValorZero() {
        Servico servico = new Servico("Lavagem simples", BigDecimal.ZERO);

        assertValor("0.00", servico.getValor());
    }

    @Test
    void deveRetornarValorDecimal() {
        Servico servico = new Servico("Lavagem simples", new BigDecimal("59.90"));

        assertValor("59.90", servico.getValor());
    }

    @Test
    void deveAlterarValorParaZero() {
        Servico servico = new Servico("Lavagem simples", new BigDecimal("50.00"));
        servico.setValor(BigDecimal.ZERO);

        assertValor("0.00", servico.getValor());
    }

    @Test
    void deveAlterarValorDecimal() {
        Servico servico = new Servico("Lavagem simples", new BigDecimal("50.00"));
        servico.setValor(new BigDecimal("79.90"));

        assertValor("79.90", servico.getValor());
    }

    // ---------- cadastrar, consultar, atualizar e excluir ----------
    // Como o id é automático, cada teste usa o id do próprio objeto criado.

    @Test
    void deveCadastrarServico() {
        Servico servico = new Servico("Lavagem simples", new BigDecimal("50.00"));

        Servico.cadastrar(servico);

        assertEquals(servico, Servico.consultar(servico.getId()));
    }

    @Test
    void deveConsultarServico() {
        Servico servico = new Servico("Polimento", new BigDecimal("100.00"));

        Servico.cadastrar(servico);

        assertEquals(servico, Servico.consultar(servico.getId()));
    }

    @Test
    void deveCadastrarOutroServico() {
        Servico servico = new Servico("Higienizacao", new BigDecimal("150.00"));

        Servico.cadastrar(servico);

        assertEquals(servico, Servico.consultar(servico.getId()));
    }

    @Test
    void deveCadastrarServicoComValorZero() {
        Servico servico = new Servico("Lavagem", BigDecimal.ZERO);

        Servico.cadastrar(servico);

        assertEquals(servico, Servico.consultar(servico.getId()));
    }

    @Test
    void deveConsultarServicoInexistente() {
        // o id gerado nunca é negativo, então -1 nunca existe
        assertNull(Servico.consultar(-1));
    }

    @Test
    void deveConsultarValorDoServico() {
        Servico servico = new Servico("Polimento", new BigDecimal("90.00"));
        Servico.cadastrar(servico);

        assertValor("90.00", Servico.consultar(servico.getId()).getValor());
    }

    @Test
    void deveAtualizarServico() {
        Servico servico = new Servico("Lavagem simples", new BigDecimal("50.00"));
        Servico.cadastrar(servico);

        Servico.atualizar(servico.getId(), "Lavagem completa", new BigDecimal("80.00"));

        assertEquals("Lavagem completa", Servico.consultar(servico.getId()).getNome());
        assertValor("80.00", Servico.consultar(servico.getId()).getValor());
    }

    @Test
    void deveAtualizarValorParaZero() {
        Servico servico = new Servico("Lavagem", new BigDecimal("50.00"));
        Servico.cadastrar(servico);

        Servico.atualizar(servico.getId(), "Lavagem", BigDecimal.ZERO);

        assertValor("0.00", Servico.consultar(servico.getId()).getValor());
    }

    @Test
    void deveTentarAtualizarServicoInexistente() {
        Servico.atualizar(-1, "Lavagem", new BigDecimal("50.00"));

        assertNull(Servico.consultar(-1));
    }

    @Test
    void deveExcluirServico() {
        Servico servico = new Servico("Polimento", new BigDecimal("100.00"));
        Servico.cadastrar(servico);

        Servico.excluir(servico.getId());

        assertNull(Servico.consultar(servico.getId()));
    }

    @Test
    void deveExcluirServicoSemAfetarOutro() {
        Servico lavagem = new Servico("Lavagem", new BigDecimal("50.00"));
        Servico polimento = new Servico("Polimento", new BigDecimal("100.00"));
        Servico.cadastrar(lavagem);
        Servico.cadastrar(polimento);

        Servico.excluir(lavagem.getId());

        assertNull(Servico.consultar(lavagem.getId()));
        assertEquals("Polimento", Servico.consultar(polimento.getId()).getNome());
    }

    @Test
    void deveTentarExcluirServicoInexistente() {
        Servico.excluir(-1);

        assertNull(Servico.consultar(-1));
    }
}