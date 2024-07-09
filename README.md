# Simulador de Planificación de Procesos y Gestión de Memoria

## Desarrolladores

- Fernando Urzúa
- Nicolás Cuadra
- Nicolás López

## Requisitos

- Java 8 o superior
- Compilador de Java (Javac)

## Compilación

Para compilar el proyecto, navegue hasta el directorio que contiene el archivo `Simulador.java` y ejecute el siguiente comando en la terminal:

```bash
javac Simulador.java
```

## Ejecución

Para ejecutar el programa, use el siguiente comando en la terminal:

```bash
java Simulador <algPlanificación> <algAsignación> <numProcesadores> <quantumCPU> <tamañoRAM>
```

Donde:

`<algPlanificación>` es un argumento que representa el algoritmo de Planificación. Este valor debe ser un número entero entre 1 y 2 (1: FIFO, 2: SJF).

`<algAsignación>` es un argumento que representa el algoritmo de Asignación de Memoria. Este valor debe ser un número entero entre 1 y 3 (1: Primer Ajuste, 2: Mejor Ajuste, 3: Peor Ajuste).

`<numProcesadores>` es un argumento que representa la cantidad de procesadores de la CPU. Este valor debe ser un número entero mayor a 0.

`<quantumCPU>` es un argumento que representa el quantum de la CPU. Este valor debe ser un número entero mayor a 0.

`<tamañoRAM>` es un argumento que representa la cantidad de bloques de la memoria RAM. Este valor debe ser un número entero mayor a 99.

Por ejemplo, para iniciar una simulación con algoritmo de Planificación FIFO, algoritmo de Asignación de Mejor Ajuste, 4 procesadores, quantum = 10, y 1024 bloques de memoria, el comando sería:

```bash
java Simulador 1 2 4 10 1024
```
