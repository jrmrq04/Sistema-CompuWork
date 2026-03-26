/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compuwork.modelo;

/**
 *
 * @author jrmrq
 */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReporteDesempeno {
    private int idReporte;
    private LocalDateTime fechaGeneracion;
    private String resultado; // Podría ser un objeto más complejo, pero simplificamos a String

    public ReporteDesempeno(int idReporte) {
        this.idReporte = idReporte;
        this.fechaGeneracion = LocalDateTime.now();
    }

    // Getters
    public int getIdReporte() { return idReporte; }
    public LocalDateTime getFechaGeneracion() { return fechaGeneracion; }
    public String getResultado() { return resultado; }

    // Método para generar reporte individual usando las métricas (simulación)
    public void generarIndividual(Empleado emp) {
        if (emp == null) {
            throw new IllegalArgumentException("El empleado no puede ser nulo.");
        }
        double desempeno = emp.calcularDesempeno(); // polimorfismo
        StringBuilder sb = new StringBuilder();
        sb.append("=== REPORTE INDIVIDUAL ===\n");
        sb.append(emp.getInfo()).append("\n");
        sb.append("Desempeño calculado: ").append(desempeno).append("\n");
        // Aquí se podrían aplicar las métricas, pero por simplicidad solo mostramos el valor
        sb.append("Fecha del reporte: ").append(fechaGeneracion.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        this.resultado = sb.toString();
    }

    // Método para generar reporte por departamento
    public void generarDepartamento(Departamento depto) {
        if (depto == null) {
            throw new IllegalArgumentException("El departamento no puede ser nulo.");
        }
        List<Empleado> empleados = depto.listarEmpleados();
        StringBuilder sb = new StringBuilder();
        sb.append("=== REPORTE POR DEPARTAMENTO ===\n");
        sb.append(depto.toString()).append("\n\n");
        double promedio = 0;
        for (Empleado e : empleados) {
            double d = e.calcularDesempeno();
            sb.append(e.getInfo()).append(" -> Desempeño: ").append(d).append("\n");
            promedio += d;
        }
        if (!empleados.isEmpty()) {
            promedio /= empleados.size();
            sb.append("\n--- Promedio de desempeño del departamento: ").append(promedio);
        } else {
            sb.append("El departamento no tiene empleados.");
        }
        sb.append("\nFecha del reporte: ").append(fechaGeneracion.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        this.resultado = sb.toString();
    }

    // Método para mostrar el reporte (podría ser un getter)
    public void imprimirReporte() {
        System.out.println(resultado);
    }
}