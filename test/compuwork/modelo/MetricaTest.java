package compuwork.modelo;

import org.junit.Test;
import static org.junit.Assert.*;

public class MetricaTest {
    @Test
    public void testCreacionYGetters() {
        Metrica m = new Metrica("Calidad", "Errores/Total", 0.4);
        
        assertEquals("El nombre no es el esperado", "Calidad", m.getNombre());
        assertEquals("La fórmula no es la esperada", "Errores/Total", m.getFormula());
        assertEquals("La ponderación no es la esperada", 0.4, m.getPonderacion(), 0.001);
        
        m.setPonderacion(0.5);
        assertEquals("La ponderación modificada no es la esperada", 0.5, m.getPonderacion(), 0.001);
    }
}
