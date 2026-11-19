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
        @Test
        void deveGerarIdAutomaticamente() {
            Cliente cliente = new Cliente("Daniel", "31999999999");

            assertTrue(cliente.getIdCliente() > 0);
        }
        @Test
        void deveAlterarNomeDoCliente() {
            Cliente cliente = new Cliente("Daniel", "31999999999");

            cliente.setNome("Carlos");

            assertEquals("Carlos", cliente.getNome());
        }

    }


