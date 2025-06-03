# 🌍 Clustering con Árbol Generador Mínimo (AGM)

Este proyecto implementa un sistema de **clustering basado en algoritmos de grafos**, específicamente utilizando el **Árbol Generador Mínimo (AGM)** como estructura base. El objetivo es agrupar nodos (puntos geográficos) representados sobre un mapa, mostrando los clusters de forma visual.

🧠 Tecnologías y conceptos aplicados
- Java – Lenguaje principal del proyecto.
- Programación orientada a objetos (POO) – Uso de clases como Grafo, AGM, BFS, ClusterAGM.
- Algoritmos de grafos – Implementación de:
- Árbol Generador Mínimo (Prim/Kruskal).
- Búsqueda en amplitud (BFS).
- JUnit Tests – Archivos de prueba incluidos (AGMTest.java, BFStest.java, etc.).
- Interfaz gráfica con JMapViewer – Visualización del grafo y clusters sobre un mapa.
- Lectura de archivos externos – Instancias leídas desde .txt.
- Eclipse project structure – Contiene .classpath, .project, y configuración de Java para Eclipse.

## 🚀 Funcionalidades principales

- Construcción de grafos completos desde una instancia de puntos.
- Cálculo del Árbol Generador Mínimo (AGM).
- División del AGM en múltiples clusters usando BFS.
- Visualización del grafo y los clusters sobre un mapa interactivo.
- Lectura de instancias desde archivo `.txt`.
- Tests unitarios de los componentes clave.

## 🧪 Tecnologías y herramientas

- **Java** – Lógica del sistema y estructura del proyecto.
- **POO** – Diseño orientado a objetos.
- **Algoritmos de grafos** – AGM (Prim o Kruskal), BFS.
- **JMapViewer** – Visualización de mapas y nodos.
- **JUnit** – Pruebas unitarias de `Grafo`, `AGM`, `ClusterAGM`.
- **Eclipse** – Proyecto estructurado para abrir directamente desde Eclipse IDE.

## 🗂️ Estructura del proyecto
<pre> ```
cluster-AGM/
│
├── src/
│ ├── interfaceGrafo/Mapa.java # Interfaz gráfica con el mapa
│ ├── logica/ # Clases de lógica del sistema
│ │ ├── Grafo.java
│ │ ├── AGM.java
│ │ ├── BFS.java
│ │ └── ClusterAGM.java
│ └── test/ # Clases de prueba JUnit
│ ├── AGMTest.java
│ ├── BFStest.java
│ ├── ClusterAGMTest.java
│ └── GrafoTest.java
│
├── instancia1.txt # Archivo de entrada de datos
├── JMapViewer.jar # Biblioteca para visualización de mapas
└── .classpath, .project, .settings/ # Archivos del entorno Eclipse
```</pre>

## 🔧 Cómo ejecutar

1. Abrí el proyecto con **Eclipse IDE**.
2. Asegurate de tener configurado Java 8 o superior.
3. Ejecutá la clase `Mapa.java` para iniciar la interfaz gráfica.
4. Cargá la instancia de puntos desde `instancia1.txt`.

> 💡 Podés modificar la cantidad de clusters cambiando la cantidad de cortes realizados sobre el AGM.


## 👩‍💻 Autora

Aldana Filiberto y Juliana Nuñez
Estudiantes de Licenciatura en Sistemas  

---

Este proyecto fue desarrollado como parte de una práctica académica para aplicar estructuras de grafos, programación orientada a objetos y visualización de datos geográficos.




