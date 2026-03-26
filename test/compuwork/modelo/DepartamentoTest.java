package compuwork.modelo;

import compuwork.excepciones.AsignacionInvalidaException;
import compuwork.excepciones.EmpleadoNoEncontradoException;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import static org.junit.Assert.*;

public class DepartamentoTest {

    private Departamento departamento;
    private Permanente empleadoPermanente;
    private Temporal empleadoTemporal;

    @Before
    public void setUp() {
        departamento = new Departamento(10, "Sistemas", "Departamento de TI");
        empleadoPermanente = new Permanente(1, "Ana", "anamaria@outlook.com", LocalDate.now(), 2000.0, "Salud", LocalDate.now().plusYears(1));
        empleadoTemporal = new Temporal(2, "Juan", "juan0541@gmail.com", LocalDate.now(), LocalDate.now().plusMonths(6), "Agencia", 20.0);
    }

    @Test
    public void testAgregarEmpleadoValido() throws AsignacionInvalidaException {
        departamento.agregarEmpleado(empleadoPermanente);
        assertEquals("El departamento debe tener 1 empleado tras agregarlo", 1, departamento.getEmpleados().size());
        assertEquals("El empleado agregado debe poder encontrarse por ID", empleadoPermanente, departamento.buscarEmpleadoPorId(1));
    }

    @Test(expected = AsignacionInvalidaException.class)
    public void testAgregarEmpleadoNulo() throws AsignacionInvalidaException {
        departamento.agregarEmpleado(null);
    }

    @Test(expected = AsignacionInvalidaException.class)
    public void testAgregarEmpleadoDuplicado() throws AsignacionInvalidaException {
        departamento.agregarEmpleado(empleadoPermanente);
        departamento.agregarEmpleado(empleadoPermanente);
    }

    @Test
    public void testEliminarEmpleadoExistente() throws AsignacionInvalidaException, EmpleadoNoEncontradoException {
        departamento.agregarEmpleado(empleadoTemporal);
        assertEquals(1, departamento.getEmpleados().size());
        
        departamento.eliminarEmpleado(empleadoTemporal);
        assertEquals("El departamento debe estar vacío para luego de eliminar al empleado", 0, departamento.getEmpleados().size());
    }

    @Test(expected = EmpleadoNoEncontradoException.class)
    public void testEliminarEmpleadoInexistente() throws EmpleadoNoEncontradoException {
        departamento.eliminarEmpleado(empleadoPermanente); // no ha sido agregado
    }
}
