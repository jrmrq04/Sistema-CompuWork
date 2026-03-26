package compuwork.modelo;

import org.junit.Test;
import java.time.LocalDate;
import static org.junit.Assert.*;

public class EmpleadoTest {

    @Test
    public void testCalcularDesempenoPermanente() {
        LocalDate ingreso = LocalDate.now().minusYears(2); // 2 años de antigüedad
        Permanente p = new Permanente(1, "Ana", "anamaria@gmail.com", ingreso, 2000.0, "Seguro", LocalDate.now().plusYears(1));
        
        // Desempeño: salarioBase * 0.1 + antiguedad * 0.5
        // 2000 * 0.1 + 2 * 0.5 = 200 + 1 = 201.0
        double esperado = 201.0;
        double resultado = p.calcularDesempeno();
        
        assertEquals("El cálculo de desempeño de Permanente es incorrecto", esperado, resultado, 0.001);
    }

    @Test
    public void testCalcularPrestacionesPermanente() {
        Permanente p = new Permanente(1, "Ana", "anamaria@gmail.com", LocalDate.now(), 2000.0, "Seguro", LocalDate.now().plusYears(1));
        
        // Prestaciones: salarioBase * 0.0833
        // 2000 * 0.0833 = 166.6
        double esperado = 166.6;
        double resultado = p.calcularPrestaciones();
        
        assertEquals("El cálculo de prestaciones de Permanente es incorrecto", esperado, resultado, 0.001);
    }

    @Test
    public void testCalcularDesempenoTemporal() {
        Temporal t = new Temporal(2, "Juan", "juan0541@outlook.com", LocalDate.now(), LocalDate.now().plusMonths(6), "AgenciaX", 15.0);
        
        // Desempeño: tarifaPorHora * 0.05
        // 15.0 * 0.05 = 0.75
        double esperado = 0.75;
        double resultado = t.calcularDesempeno();
        
        assertEquals("El cálculo de desempeño de Temporal es incorrecto", esperado, resultado, 0.001);
    }

    @Test
    public void testExtenderContratoValido() {
        LocalDate finInicial = LocalDate.now().plusMonths(1);
        Temporal t = new Temporal(2, "Juan", "juan0541@outlook.com", LocalDate.now(), finInicial, "AgenciaX", 15.0);
        
        LocalDate nuevaExt = LocalDate.now().plusMonths(3);
        t.extenderContrato(nuevaExt);
        
        assertEquals("La fecha de fin de contrato debió actualizarse a la nueva fecha", nuevaExt, t.getFechaFinContrato());
    }

    @Test
    public void testExtenderContratoInvalido() {
        LocalDate finInicial = LocalDate.now().plusMonths(3);
        Temporal t = new Temporal(2, "Juan", "juan0541@outlook.com", LocalDate.now(), finInicial, "AgenciaX", 15.0);
        
        LocalDate nuevaExt = LocalDate.now().plusMonths(1); // Fecha más temprana que finInicial
        t.extenderContrato(nuevaExt);
        
        assertEquals("La fecha de fin de contrato no debió actualizarse", finInicial, t.getFechaFinContrato());
    }
}
