import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

    class ClienteTest {

        @Test
        void deveCriarUmCliente() {

            Cliente cliente = new Cliente("Daniel", "31999999999");

            assertNotNull(cliente);
        }

        @Test
        void deveRetornarNomeDoCliente() {

            Cliente cliente = new Cliente("Daniel", "31999999999");

            assertEquals("Daniel", cliente.getNome());
        }
        @Test
        void deveRetornarTelefoneDoCliente() {
            Cliente cliente = new Cliente("Daniel", "31999999999");

            assertEquals("31999999999", cliente.getTelefone());
        }

    }


