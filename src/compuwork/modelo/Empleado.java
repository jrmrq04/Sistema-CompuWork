package compuwork.modelo;

import java.time.LocalDate;

public abstract class Empleado {
    protected int idEmpleado;
    protected String nombre;
    protected String email;
    protected LocalDate fechaIngreso;

    public Empleado(int idEmpleado, String nombre, String email, LocalDate fechaIngreso) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.email = email;
        this.fechaIngreso = fechaIngreso;
    }

    // Getters y Setters (encapsulamiento)
    public int getIdEmpleado() { return idEmpleado; }
    public void setIdEmpleado(int idEmpleado) { this.idEmpleado = idEmpleado; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(LocalDate fechaIngreso) { this.fechaIngreso = fechaIngreso; }

    // Método abstracto que obliga a las subclases a implementar su propia lógica de desempeño
    public abstract double calcularDesempeno();

    // Método concreto que muestra información básica (puede ser sobrescrito si se desea)
    public String getInfo() {
        return String.format("ID: %d | Nombre: %s | Email: %s | Ingreso: %s",
                idEmpleado, nombre, email, fechaIngreso);
    }
}