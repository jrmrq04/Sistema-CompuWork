/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package compuwork.prueba;

/**
 *
 * @author jrmrq
 */

import compuwork.modelo.*;
import compuwork.excepciones.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaCompuWork {
    private static List<Empleado> empleados = new ArrayList<>();
    private static List<Departamento> departamentos = new ArrayList<>();
    private static int nextEmpleadoId = 1;
    private static int nextDeptoId = 1;
    private static int nextReporteId = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n=== SISTEMA DE GESTIÓN COMPUWORK ===");
            System.out.println("1. Gestionar Empleados");
            System.out.println("2. Gestionar Departamentos");
            System.out.println("3. Asignar Empleado a Departamento");
            System.out.println("4. Visualizar Empleados por Departamento");
            System.out.println("5. Generar Reporte Individual");
            System.out.println("6. Generar Reporte por Departamento");
            System.out.println("7. Salir");
            System.out.print("Seleccione opción: ");
            opcion = leerEntero(sc);

            switch (opcion) {
                case 1 -> menuEmpleados(sc);
                case 2 -> menuDepartamentos(sc);
                case 3 -> asignarEmpleadoADepartamento(sc);
                case 4 -> visualizarEmpleadosPorDepto(sc);
                case 5 -> generarReporteIndividual(sc);
                case 6 -> generarReporteDepartamento(sc);
                case 7 -> System.out.println("Saliendo del sistema de gestión de datos...");
                default -> System.out.println("ERROR!!! Esta opción es invalida... Volver a selecionar una opción");
            }
        } while (opcion != 7);
        sc.close();
    }

    // Métodos auxiliares para entrada segura
    private static int leerEntero(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.print("Ingrese un número válido: ");
            sc.next();
        }
        int num = sc.nextInt();
        sc.nextLine(); // limpiar buffer
        return num;
    }

    private static double leerDouble(Scanner sc) {
        while (!sc.hasNextDouble()) {
            System.out.print("Ingrese un número decimal válido: ");
            sc.next();
        }
        double num = sc.nextDouble();
        sc.nextLine();
        return num;
    }

    // Menú Empleados
    private static void menuEmpleados(Scanner sc) {
        System.out.println("\n--- Gestión de Empleados ---");
        System.out.println("1. Crear empleado permanente");
        System.out.println("2. Crear empleado temporal");
        System.out.println("3. Actualizar empleado");
        System.out.println("4. Eliminar empleado");
        System.out.println("5. Listar todos los empleados");
        System.out.print("Opción: ");
        int op = leerEntero(sc);
        switch (op) {
            case 1 -> crearPermanente(sc);
            case 2 -> crearTemporal(sc);
            case 3 -> actualizarEmpleado(sc);
            case 4 -> eliminarEmpleado(sc);
            case 5 -> listarEmpleados();
            default -> System.out.println("Opción no válida.");
        }
    }

    private static void crearPermanente(Scanner sc) {
        System.out.println("\n--- Nuevo Empleado Permanente ---");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Email (Utliza el @ ej: @hotmail.com, @gmail.com, @outlook.com): ");
        String email = sc.nextLine();
        System.out.print("Año de ingreso (YYYY): ");
        int año = leerEntero(sc);
        System.out.print("Mes de ingreso (1-12): ");
        int mes = leerEntero(sc);
        System.out.print("Día de ingreso: ");
        int dia = leerEntero(sc);
        LocalDate fechaIngreso = LocalDate.of(año, mes, dia);
        System.out.print("Salario base: ");
        double salario = leerDouble(sc);
        System.out.print("Beneficios (texto): ");
        String beneficios = sc.nextLine();
        System.out.print("Año de renovación: ");
        int añoR = leerEntero(sc);
        System.out.print("Mes de renovación: ");
        int mesR = leerEntero(sc);
        System.out.print("Día de renovación: ");
        int diaR = leerEntero(sc);
        LocalDate fechaRenov = LocalDate.of(añoR, mesR, diaR);

        Empleado e = new Permanente(nextEmpleadoId++, nombre, email, fechaIngreso, salario, beneficios, fechaRenov);
        empleados.add(e);
        System.out.println("Empleado creado con ID: " + e.getIdEmpleado());
    }

    private static void crearTemporal(Scanner sc) {
        System.out.println("\n--- Nuevo Empleado Temporal ---");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Año de ingreso (YYYY): ");
        int año = leerEntero(sc);
        System.out.print("Mes de ingreso (1-12): ");
        int mes = leerEntero(sc);
        System.out.print("Día de ingreso: ");
        int dia = leerEntero(sc);
        LocalDate fechaIngreso = LocalDate.of(año, mes, dia);
        System.out.print("Año de fin de contrato: ");
        int añoF = leerEntero(sc);
        System.out.print("Mes de fin: ");
        int mesF = leerEntero(sc);
        System.out.print("Día de fin: ");
        int diaF = leerEntero(sc);
        LocalDate fechaFin = LocalDate.of(añoF, mesF, diaF);
        System.out.print("Agencia temporal: ");
        String agencia = sc.nextLine();
        System.out.print("Tarifa por hora: ");
        double tarifa = leerDouble(sc);

        Empleado e = new Temporal(nextEmpleadoId++, nombre, email, fechaIngreso, fechaFin, agencia, tarifa);
        empleados.add(e);
        System.out.println("Empleado creado con ID: " + e.getIdEmpleado());
    }

    private static void actualizarEmpleado(Scanner sc) {
        listarEmpleados();
        System.out.print("Ingrese ID del empleado a actualizar: ");
        int id = leerEntero(sc);
        Empleado emp = buscarEmpleadoPorId(id);
        if (emp == null) {
            System.out.println("OH NO! Empleado no encontrado.");
            return;
        }
        System.out.println("Actualizando datos (deje vacío para no modificar):");
        System.out.print("Nuevo nombre (" + emp.getNombre() + "): ");
        String nombre = sc.nextLine();
        if (!nombre.isBlank()) emp.setNombre(nombre);
        System.out.print("Nuevo email (" + emp.getEmail() + "): ");
        String email = sc.nextLine();
        if (!email.isBlank()) emp.setEmail(email);
        // Para simplificar, no se actualizan fechas ni atributos específicos.
        System.out.println("Empleado actualizado!");
    }

    private static void eliminarEmpleado(Scanner sc) {
        listarEmpleados();
        System.out.print("Ingrese ID del empleado a eliminar: ");
        int id = leerEntero(sc);
        Empleado emp = buscarEmpleadoPorId(id);
        if (emp == null) {
            System.out.println("Empleado no encontrado.");
            return;
        }
        // Remover de todos los departamentos primero
        for (Departamento d : departamentos) {
            try {
                d.eliminarEmpleado(emp);
            } catch (EmpleadoNoEncontradoException e) {
                // No estaba en ese departamento, ignorar
            }
        }
        empleados.remove(emp);
        System.out.println("Empleado eliminado.");
    }

    private static void listarEmpleados() {
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
        } else {
            System.out.println("\n--- Lista de Empleados ---");
            for (Empleado e : empleados) {
                System.out.println(e.getInfo());
            }
        }
    }

    private static Empleado buscarEmpleadoPorId(int id) {
        return empleados.stream().filter(e -> e.getIdEmpleado() == id).findFirst().orElse(null);
    }

    // Menú Departamentos
    private static void menuDepartamentos(Scanner sc) {
        System.out.println("\n--- Gestión de Departamentos ---");
        System.out.println("1. Crear departamento");
        System.out.println("2. Modificar departamento");
        System.out.println("3. Eliminar departamento");
        System.out.println("4. Listar departamentos");
        System.out.print("Opción: ");
        int op = leerEntero(sc);
        switch (op) {
            case 1 -> crearDepartamento(sc);
            case 2 -> modificarDepartamento(sc);
            case 3 -> eliminarDepartamento(sc);
            case 4 -> listarDepartamentos();
            default -> System.out.println("Opción no válida.");
        }
    }

    private static void crearDepartamento(Scanner sc) {
        System.out.println("\n--- Nuevo Departamento ---");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Descripción: ");
        String desc = sc.nextLine();
        Departamento d = new Departamento(nextDeptoId++, nombre, desc);
        departamentos.add(d);
        System.out.println("Departamento creado con ID: " + d.getIdDepartamento());
    }

    private static void modificarDepartamento(Scanner sc) {
        listarDepartamentos();
        System.out.print("Ingrese ID del departamento a modificar: ");
        int id = leerEntero(sc);
        Departamento d = buscarDepartamentoPorId(id);
        if (d == null) {
            System.out.println("Departamento no encontrado.");
            return;
        }
        System.out.print("Nuevo nombre (" + d.getNombre() + "): ");
        String nombre = sc.nextLine();
        if (!nombre.isBlank()) d.setNombre(nombre);
        System.out.print("Nueva descripción (" + d.getDescripcion() + "): ");
        String desc = sc.nextLine();
        if (!desc.isBlank()) d.setDescripcion(desc);
        System.out.println("Departamento actualizado.");
    }

    private static void eliminarDepartamento(Scanner sc) {
        listarDepartamentos();
        System.out.print("Ingrese ID del departamento a eliminar: ");
        int id = leerEntero(sc);
        Departamento d = buscarDepartamentoPorId(id);
        if (d == null) {
            System.out.println("Departamento no encontrado.");
            return;
        }
        // Opcional: reasignar empleados? Por simplicidad, no se hace.
        departamentos.remove(d);
        System.out.println("Departamento eliminado.");
    }

    private static void listarDepartamentos() {
        if (departamentos.isEmpty()) {
            System.out.println("No hay departamentos registrados.");
        } else {
            System.out.println("\n--- Lista de Departamentos ---");
            for (Departamento d : departamentos) {
                System.out.println(d);
            }
        }
    }

    private static Departamento buscarDepartamentoPorId(int id) {
        return departamentos.stream().filter(d -> d.getIdDepartamento() == id).findFirst().orElse(null);
    }

    // Asignar empleado a departamento
    private static void asignarEmpleadoADepartamento(Scanner sc) {
        listarEmpleados();
        System.out.print("Ingrese ID del empleado: ");
        int idEmp = leerEntero(sc);
        Empleado emp = buscarEmpleadoPorId(idEmp);
        if (emp == null) {
            System.out.println("Empleado no encontrado.");
            return;
        }
        listarDepartamentos();
        System.out.print("Ingrese ID del departamento: ");
        int idDepto = leerEntero(sc);
        Departamento depto = buscarDepartamentoPorId(idDepto);
        if (depto == null) {
            System.out.println("Departamento no encontrado.");
            return;
        }
        try {
            depto.agregarEmpleado(emp);
        } catch (AsignacionInvalidaException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Visualizar empleados por departamento
    private static void visualizarEmpleadosPorDepto(Scanner sc) {
        listarDepartamentos();
        System.out.print("Ingrese ID del departamento: ");
        int id = leerEntero(sc);
        Departamento d = buscarDepartamentoPorId(id);
        if (d == null) {
            System.out.println("Departamento no encontrado.");
            return;
        }
        List<Empleado> empleadosDepto = d.listarEmpleados();
        if (empleadosDepto.isEmpty()) {
            System.out.println("El departamento no tiene empleados.");
        } else {
            System.out.println("\n--- Empleados del departamento " + d.getNombre() + " ---");
            for (Empleado e : empleadosDepto) {
                System.out.println(e.getInfo());
            }
        }
    }

    // Generar reporte individual
    private static void generarReporteIndividual(Scanner sc) {
        listarEmpleados();
        System.out.print("Ingrese ID del empleado: ");
        int id = leerEntero(sc);
        Empleado emp = buscarEmpleadoPorId(id);
        if (emp == null) {
            System.out.println("Empleado no encontrado.");
            return;
        }
        ReporteDesempeno reporte = new ReporteDesempeno(nextReporteId++);
        try {
            reporte.generarIndividual(emp);
            System.out.println("\n--- REPORTE GENERADO ---");
            reporte.imprimirReporte();
        } catch (Exception e) {
            System.out.println("Error al generar reporte: " + e.getMessage());
        }
    }

    // Generar reporte por departamento
    private static void generarReporteDepartamento(Scanner sc) {
        listarDepartamentos();
        System.out.print("Ingrese ID del departamento: ");
        int id = leerEntero(sc);
        Departamento d = buscarDepartamentoPorId(id);
        if (d == null) {
            System.out.println("Lo sentimos... Departamento no encontrado.");
            return;
        }
        ReporteDesempeno reporte = new ReporteDesempeno(nextReporteId++);
        try {
            reporte.generarDepartamento(d);
            System.out.println("\n--- REPORTE GENERADO ---");
            reporte.imprimirReporte();
        } catch (Exception e) {
            System.out.println("Error al generar reporte: " + e.getMessage());
        }
    }
}
