/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compuwork.modelo;

/**
 *
 * @author jrmrq
 */

import java.util.ArrayList;
import java.util.List;
import compuwork.excepciones.EmpleadoNoEncontradoException;
import compuwork.excepciones.AsignacionInvalidaException;

public class Departamento {
    private int idDepartamento;
    private String nombre;
    private String descripcion;
    private List<Empleado> empleados;

    public Departamento(int idDepartamento, String nombre, String descripcion) {
        this.idDepartamento = idDepartamento;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.empleados = new ArrayList<>();
    }

    // Getters y Setters
    public int getIdDepartamento() { return idDepartamento; }
    public void setIdDepartamento(int idDepartamento) { this.idDepartamento = idDepartamento; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public List<Empleado> getEmpleados() { return new ArrayList<>(empleados); } // copia defensiva

    // Métodos de gestión de empleados en el departamento
    public void agregarEmpleado(Empleado e) throws AsignacionInvalidaException {
        if (e == null) {
            throw new AsignacionInvalidaException("No se puede asignar un empleado nulo.");
        }
        if (empleados.contains(e)) {
            throw new AsignacionInvalidaException("El empleado ya está asignado a este departamento.");
        }
        empleados.add(e);
        System.out.println("Empleado " + e.getNombre() + " agregado al departamento " + nombre);
    }

    public void eliminarEmpleado(Empleado e) throws EmpleadoNoEncontradoException {
        if (!empleados.remove(e)) {
            throw new EmpleadoNoEncontradoException("Empleado con ID " + e.getIdEmpleado() + " no encontrado en el departamento.");
        }
        System.out.println("Empleado " + e.getNombre() + " eliminado del departamento " + nombre);
    }

    public List<Empleado> listarEmpleados() {
        return new ArrayList<>(empleados);
    }

    // Método para buscar un empleado por ID dentro del departamento
    public Empleado buscarEmpleadoPorId(int id) {
        return empleados.stream()
                .filter(e -> e.getIdEmpleado() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return String.format("Departamento %d: %s - %s (%d empleados)", idDepartamento, nombre, descripcion, empleados.size());
    }
}