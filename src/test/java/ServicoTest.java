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
}