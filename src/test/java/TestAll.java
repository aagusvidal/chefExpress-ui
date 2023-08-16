import ejemplo.HolaJava;
import org.junit.*; // Importa las anotaciones de JUnit

public class TestAll {
    @Test
    public void testSaludo() {
        HolaJava o = new HolaJava();
        assert "Hola, Mundo! (en Java)".equals(o.saluda("Mundo"));
    }
}