import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class VeiculoTest {

    @Test
    void testGetPlaca() {
        Veiculo veiculo = new Veiculo("ABC1234", "Gol", null);

        assertEquals("ABC1234", veiculo.getPlaca());
    }

    @Test
    void testSetPlaca() {
        Veiculo veiculo = new Veiculo("ABC1234", "Gol", null);

        veiculo.setPlaca("XYZ5678");

        assertEquals("XYZ5678", veiculo.getPlaca());
    }

    @Test
    void testGetModelo() {
        Veiculo veiculo = new Veiculo("ABC1234", "Gol", null);

        assertEquals("Gol", veiculo.getModelo());
    }

    @Test
    void testSetModelo() {
        Veiculo veiculo = new Veiculo("ABC1234", "Gol", null);

        veiculo.setModelo("Onix");

        assertEquals("Onix", veiculo.getModelo());
    }

    @Test
    void testIdAutomatico() {
        Veiculo veiculo1 = new Veiculo("ABC1234", "Gol", null);
        Veiculo veiculo2 = new Veiculo("XYZ5678", "Onix", null);

        assertEquals(veiculo1.getIdVeiculo() + 1,
                veiculo2.getIdVeiculo());
    }

    @Test
    void testGetCliente() {
        Veiculo veiculo = new Veiculo("ABC1234", "Gol", null);

        assertNull(veiculo.getCliente());
    }
    }