package compuwork.modelo;

import compuwork.excepciones.AsignacionInvalidaException;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import static org.junit.Assert.*;

public class ReporteDesempenoTest {
    private Permanente empleadoP;
    private Departamento departamento;
    private ReporteDesempeno reporte;

    @Before
    public void setUp() {
        empleadoP = new Permanente(1, "Emanuel", "emanuelfr@hotmail.com", LocalDate.now(), 3000.0, "Pleno", LocalDate.now().plusYears(1));
        departamento = new Departamento(1, "Ventas", "Area de ventas");
        reporte = new ReporteDesempeno(100);
    }

    @Test
    public void testGenerarIndividualValido() {
        reporte.generarIndividual(empleadoP);
        String resultado = reporte.getResultado();
        
        assertNotNull("El resultado del reporte no debe ser nulo", resultado);
        assertTrue("El reporte debe contener el nombre del empleado", resultado.contains("Emanuel"));
        assertTrue("El reporte debe tener el título correcto", resultado.contains("REPORTE INDIVIDUAL"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGenerarIndividualNulo() {
        reporte.generarIndividual(null);
    }

    @Test
    public void testGenerarDepartamentoValido() throws AsignacionInvalidaException {
        departamento.agregarEmpleado(empleadoP);
        reporte.generarDepartamento(departamento);
        String resultado = reporte.getResultado();
        
        assertNotNull("El resultado del reporte del departamento no debe ser nulo", resultado);
        assertTrue("El reporte debe incluir el nombre del departamento", resultado.contains("Ventas"));
        assertTrue("El reporte debe incluir a los empleados del departamento", resultado.contains("Emanuel"));
        assertTrue("El reporte debe incluir el cálculo promedio", resultado.contains("Promedio de desempeño"));
    }

    @Test
    public void testGenerarDepartamentoVacio() {
        reporte.generarDepartamento(departamento); // Sin empleados
        String resultado = reporte.getResultado();
        
        assertTrue("Debe indicar que no hay empleados", resultado.contains("El departamento no tiene empleados"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGenerarDepartamentoNulo() {
        reporte.generarDepartamento(null);
    }
}
