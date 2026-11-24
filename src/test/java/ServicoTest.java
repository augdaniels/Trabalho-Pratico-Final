import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServicoTest {

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
}