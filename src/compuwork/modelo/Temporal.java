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

public class Temporal extends Empleado {
    private LocalDate fechaFinContrato;
    private String agenciaTemporal;
    private double tarifaPorHora;

    public Temporal(int idEmpleado, String nombre, String email, LocalDate fechaIngreso,
                    LocalDate fechaFinContrato, String agenciaTemporal, double tarifaPorHora) {
        super(idEmpleado, nombre, email, fechaIngreso);
        this.fechaFinContrato = fechaFinContrato;
        this.agenciaTemporal = agenciaTemporal;
        this.tarifaPorHora = tarifaPorHora;
    }

    // Getters y Setters
    public LocalDate getFechaFinContrato() { return fechaFinContrato; }
    public void setFechaFinContrato(LocalDate fechaFinContrato) { this.fechaFinContrato = fechaFinContrato; }

    public String getAgenciaTemporal() { return agenciaTemporal; }
    public void setAgenciaTemporal(String agenciaTemporal) { this.agenciaTemporal = agenciaTemporal; }

    public double getTarifaPorHora() { return tarifaPorHora; }
    public void setTarifaPorHora(double tarifaPorHora) { this.tarifaPorHora = tarifaPorHora; }

    @Override
    public double calcularDesempeno() {
        // Simulación: desempeño basado en horas trabajadas y evaluaciones de la agencia
        return tarifaPorHora * 0.05; // ejemplo
    }

    // Método específico de temporales
    public void extenderContrato(LocalDate nuevaFechaFin) {
        if (nuevaFechaFin.isAfter(fechaFinContrato)) {
            this.fechaFinContrato = nuevaFechaFin;
            System.out.println("Contrato extendido hasta: " + nuevaFechaFin);
        } else {
            System.out.println("La nueva fecha debe ser posterior a la actual.");
        }
    }

    @Override
    public String getInfo() {
        return super.getInfo() + String.format(" | Tipo: Temporal | Fin Contrato: %s | Agencia: %s | Tarifa/hora: %.2f",
                fechaFinContrato, agenciaTemporal, tarifaPorHora);
    }
}