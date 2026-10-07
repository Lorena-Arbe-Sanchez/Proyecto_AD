# EasyTravel

Aplicación de gestión de viajes desarrollada para el proyecto de la Unidad Didáctica 1 de la asignatura de Acceso a Datos.

## Descripción

EasyTravel será una aplicación que permitirá gestionar viajes, clientes, reservas y otra información relacionada con la organización de viajes.

La aplicación utilizará ficheros para almacenar y gestionar la información de forma persistente.

## Funcionalidades previstas

- Gestión de clientes.
- Gestión de viajes.
- Gestión de reservas.
- Gestión de destinos.
- Gestión de hoteles.
- Altas, bajas y modificaciones de registros.
- Búsqueda de información.
- Exportación de datos a XML mediante XStream.
- Control de errores.

Como posibles mejoras se plantea añadir:

- Mejoras en las búsquedas y gestión de datos.
- Imágenes asociadas a los destinos.
- Lectura de ficheros XML.
- Generación y lectura de ficheros JSON.
- Interfaz gráfica.

## Tecnologías

- Java
- IntelliJ IDEA
- Git / GitHub
- Ficheros binarios y de texto
- XML
- XStream

## Estructura inicial

El proyecto contará inicialmente con las siguientes clases principales:

- `Cliente`
- `Viaje`
- `Reserva`

Posteriormente se añadirán otras clases como:

- `Destino`
- `Hotel`

## Almacenamiento

La aplicación utilizará ficheros para almacenar los datos de las diferentes entidades.

Ficheros previstos inicialmente:

- `clientes.dat`
- `viajes.dat`
- `reservas.dat`

También se podrán añadir ficheros para destinos y hoteles.

## Puesta en marcha

1. Clonar el repositorio en la terminal mediante el comando `git clone https://github.com/Lorena-Arbe-Sanchez/Proyecto_AD.git`.
2. Abrir el proyecto (la carpeta `Proyecto` dentro del repositorio `Proyecto_AD`) con IntelliJ IDEA.
3. Comprobar las dependencias necesarias.
4. Ejecutar la clase principal (`Main`) de la aplicación.

Las instrucciones de instalación y ejecución se completarán cuando el proyecto esté finalizado.