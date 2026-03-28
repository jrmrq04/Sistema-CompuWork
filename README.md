<h2>Sistema de gestión CompuWork</h2>

Requisitos previos:
- Apache Netbeans IDE 22
- Java 8
- JDK 17

Requisitos para hacer pruebas unitarias:
- JUnit 4.13.2
- Hamcrest 1.3

Instrucciones para compilar el programa:
Abrir el IDE Netbeans, y dar click en File > Open Project (Ctrl+Shift+O)
Luego click en Run > Build Project
Y se obtiene el archivo .jar en la carpeta dist dentro del código

Instrucciones para ejecutar el programa:
Asegurese de obtener Java 8 antes de iniciar el proceso, luego ir al CMD para escribir el siguiente comando:
java -jar "ubicacion del archivo sin las comillas"\SistemaCompuWork.jar

Instruccion para ejecutar las pruebas:

Antes de comenzar las pruebas unitarias, asegurese de obtener el JUnit 4.13.2 y el Hamcrest 1.3 para que las pruebas funcionen
correctamente.
En Netbeans con el proyecto abierto se da click en Run > Test Project para ejecutar todos los archivos de prueba
o hacer click en un archivo de prueba Run > Test File


<h2>Diagrama de clases</h2>

```mermaid
classDiagram
    class Empleado {
        <<abstract>>
        #int idEmpleado
        #String nombre
        #String email
        #LocalDate fechaIngreso
        +calcularDesempeno()* double
        +getInfo() String
    }

    class Permanente {
        -double salarioBase
        -String beneficios
        -LocalDate fechaRenovacion
        +calcularDesempeno() double
        +calcularPrestaciones() double
        +getInfo() String
    }

    class Temporal {
        -LocalDate fechaFinContrato
        -String agenciaTemporal
        -double tarifaPorHora
        +calcularDesempeno() double
        +extenderContrato(LocalDate) void
        +getInfo() String
    }

    class Departamento {
        -int idDepartamento
        -String nombre
        -String descripcion
        -List~Empleado~ empleados
        +agregarEmpleado(Empleado) void
        +eliminarEmpleado(Empleado) void
        +buscarEmpleadoPorId(int) Empleado
        +listarEmpleados() List~Empleado~
    }

    class Metrica {
        -String nombre
        -String formula
        -double ponderacion
    }

    class ReporteDesempeno {
        -int idReporte
        -LocalDateTime fechaGeneracion
        -String resultado
        +generarIndividual(Empleado) void
        +generarDepartamento(Departamento) void
        +imprimirReporte() void
    }

    class AsignacionInvalidaException {
        +AsignacionInvalidaException(String)
    }
    class EmpleadoNoEncontradoException {
        +EmpleadoNoEncontradoException(String)
    }
    class DepartamentoNoEncontradoException {
        +DepartamentoNoEncontradoException(String)
    }

    Empleado <|-- Permanente : herencia
    Empleado <|-- Temporal : herencia
    Departamento "1" o-- "*" Empleado : composición
    ReporteDesempeno ..> Empleado : usa (polimorfismo)
    ReporteDesempeno ..> Departamento : usa
    Departamento ..> AsignacionInvalidaException : lanza
    Departamento ..> EmpleadoNoEncontradoException : lanza
    Exception <|-- AsignacionInvalidaException
    Exception <|-- EmpleadoNoEncontradoException
    Exception <|-- DepartamentoNoEncontradoException
```
