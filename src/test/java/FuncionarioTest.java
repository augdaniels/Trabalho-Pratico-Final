import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FuncionarioTest {

    @Test
    void construtorDeveGuardarNomeECargo() {
        Funcionario f = new Funcionario("Carlos", "Lavador");
        assertEquals("Carlos", f.getNome());
        assertEquals("Lavador", f.getCargo());
    }

    @Test
    void construtorDeveAceitarNomeComUmCaractere() {
        Funcionario f = new Funcionario("A", "Gerente");
        assertEquals("A", f.getNome());
    }

    @Test
    void construtorDeveLancarErroComNomeNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> new Funcionario(null, "Lavador"));
    }
}