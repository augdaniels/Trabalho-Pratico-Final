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
    }