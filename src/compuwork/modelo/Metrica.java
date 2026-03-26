/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compuwork.modelo;

/**
 *
 * @author jrmrq
 */
public class Metrica {
    private String nombre;
    private String formula;
    private double ponderacion; // peso en el cálculo global

    public Metrica(String nombre, String formula, double ponderacion) {
        this.nombre = nombre;
        this.formula = formula;
        this.ponderacion = ponderacion;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getFormula() { return formula; }
    public void setFormula(String formula) { this.formula = formula; }

    public double getPonderacion() { return ponderacion; }
    public void setPonderacion(double ponderacion) { this.ponderacion = ponderacion; }
}