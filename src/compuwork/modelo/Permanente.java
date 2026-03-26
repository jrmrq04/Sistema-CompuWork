/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compuwork.modelo;

/**
 *
 * @author jrmrq
 */

import java.time.LocalDate;

public class Permanente extends Empleado {
    private double salarioBase;
    private String beneficios;
    private LocalDate fechaRenovacion;

    public Permanente(int idEmpleado, String nombre, String email, LocalDate fechaIngreso,
                      double salarioBase, String beneficios, LocalDate fechaRenovacion) {
        super(idEmpleado, nombre, email, fechaIngreso);
        this.salarioBase = salarioBase;
        this.beneficios = beneficios;
        this.fechaRenovacion = fechaRenovacion;
    }

    // Getters y Setters
    public double getSalarioBase() { return salarioBase; }
    public void setSalarioBase(double salarioBase) { this.salarioBase = salarioBase; }

    public String getBeneficios() { return beneficios; }
    public void setBeneficios(String beneficios) { this.beneficios = beneficios; }

    public LocalDate getFechaRenovacion() { return fechaRenovacion; }
    public void setFechaRenovacion(LocalDate fechaRenovacion) { this.fechaRenovacion = fechaRenovacion; }

    @Override
    public double calcularDesempeno() {
        // Ejemplo de cálculo basado en salario y antigüedad (simulado)
        double antiguedad = java.time.temporal.ChronoUnit.YEARS.between(fechaIngreso, LocalDate.now());
        return salarioBase * 0.1 + antiguedad * 0.5;
    }

    // Método específico de permanentes
    public double calcularPrestaciones() {
        // Cálculo de prestaciones (ejemplo: prima de servicios)
        return salarioBase * 0.0833; // 8.33% del salario mensual
    }

    @Override
    public String getInfo() {
        return super.getInfo() + String.format(" | Tipo: Permanente | Salario Base: %.2f | Beneficios: %s | Renovación: %s",
                salarioBase, beneficios, fechaRenovacion);
    }
}