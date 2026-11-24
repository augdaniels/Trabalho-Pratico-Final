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
    @Test
    void idDeveComecarEmZero() {
        Funcionario f = new Funcionario("Carlos", "Lavador");
        assertEquals(0, f.getIdFuncionario());
    }

    @Test
    void setIdDeveGuardarValorNormal() {
        Funcionario f = new Funcionario("Carlos", "Lavador");
        f.setIdFuncionario(5);
        assertEquals(5, f.getIdFuncionario());
    }

    @Test
    void setIdDeveAceitarZero() {
        Funcionario f = new Funcionario("Carlos", "Lavador");
        f.setIdFuncionario(0);
        assertEquals(0, f.getIdFuncionario());
    }

    @Test
    void setIdDeveLancarErroComValorNegativo() {
        Funcionario f = new Funcionario("Carlos", "Lavador");
        assertThrows(IllegalArgumentException.class,
                () -> f.setIdFuncionario(-1));
    }
    @Test
    void setNomeDeveAlterarONome() {
        Funcionario f = new Funcionario("Carlos", "Lavador");
        f.setNome("Pedro");
        assertEquals("Pedro", f.getNome());
    }

    @Test
    void setNomeDeveAceitarNomeComUmCaractere() {
        Funcionario f = new Funcionario("Carlos", "Lavador");
        f.setNome("B");
        assertEquals("B", f.getNome());
    }

    @Test
    void setNomeDeveLancarErroComNomeVazio() {
        Funcionario f = new Funcionario("Carlos", "Lavador");
        assertThrows(IllegalArgumentException.class, () -> f.setNome("   "));
    }
    @Test
    void setCargoDeveAlterarOCargo() {
        Funcionario f = new Funcionario("Carlos", "Lavador");
        f.setCargo("Atendente");
        assertEquals("Atendente", f.getCargo());
    }

    @Test
    void setCargoDeveAceitarCargoComUmCaractere() {
        Funcionario f = new Funcionario("Carlos", "Lavador");
        f.setCargo("X");
        assertEquals("X", f.getCargo());
    }

    @Test
    void setCargoDeveLancarErroComCargoNulo() {
        Funcionario f = new Funcionario("Carlos", "Lavador");
        assertThrows(IllegalArgumentException.class, () -> f.setCargo(null));
    }
}