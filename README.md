# EasyTravel

Aplicación de gestión de viajes desarrollada para el proyecto de la Unidad Didáctica 1 de la asignatura de Acceso a
Datos.

## Descripción

EasyTravel es una aplicación de consola desarrollada en Java que permite gestionar información relacionada con la
organización de viajes.

La aplicación permite gestionar clientes, viajes, destinos, hoteles y reservas, almacenando la información de forma
persistente mediante ficheros binarios y permitiendo exportarla a XML.

## Funcionalidades

La aplicación permite:

- Gestionar clientes.
- Gestionar viajes.
- Gestionar destinos.
- Gestionar hoteles.
- Gestionar reservas.
- Mostrar registros.
- Añadir registros.
- Modificar registros.
- Eliminar registros.
- Realizar búsquedas.
- Comprobar relaciones entre los diferentes datos.
- Evitar eliminaciones que puedan dejar datos relacionados sin referencia.
- Exportar la información a XML mediante XStream.
- Validar diferentes tipos de datos introducidos por el usuario.

### Búsquedas

La aplicación permite realizar diferentes tipos de búsquedas:

- Clientes por ID, DNI y email.
- Viajes por origen, destino, fecha y tipo de viaje.
- Destinos por ciudad, país y tipo de destino.
- Hoteles por número de estrellas y precio máximo.
- Reservas por ID, cliente, viaje y estado.

## Datos gestionados

La aplicación utiliza cinco entidades principales:

- `Cliente`
- `Viaje`
- `Destino`
- `Hotel`
- `Reserva`

Las entidades están relacionadas entre sí para representar la organización de los viajes y sus reservas.

## Almacenamiento

Los datos se almacenan mediante ficheros binarios utilizando serialización de objetos Java.

Los ficheros utilizados son:

- `FicheroCliente.dat`
- `FicheroViaje.dat`
- `FicheroDestino.dat`
- `FicheroHotel.dat`
- `FicheroReserva.dat`

Los ficheros se encuentran dentro de la carpeta:

`datos/dat`

Además, la aplicación permite generar ficheros XML dentro de:

`datos/xml`

- `Clientes.xml`
- `Viajes.xml`
- `Destinos.xml`
- `Hoteles.xml`
- `Reservas.xml`

## Tecnologías

- Java
- IntelliJ IDEA
- Git
- GitHub
- Ficheros binarios
- Serialización de objetos
- XML
- XStream

## Estructura del proyecto

El proyecto contiene las clases correspondientes a las diferentes entidades, las clases encargadas de generar los datos
iniciales y las clases utilizadas para trabajar con listas y exportar la información a XML.

La clase principal de la aplicación es:

`Main`

## Puesta en marcha

### 1. Clonar el repositorio

Desde una terminal:

```bash
git clone https://github.com/Lorena-Arbe-Sanchez/Proyecto_AD.git
```

### 2. Abrir el proyecto

Abrir con IntelliJ IDEA la carpeta del proyecto dentro del repositorio clonado.

### 3. Comprobar las dependencias

Comprobar que el proyecto dispone de las dependencias necesarias para utilizar XStream.

### 4. Ejecutar la aplicación

Ejecutar la clase `Main`.

La aplicación se ejecutará mediante una interfaz de consola y mostrará el menú principal de EasyTravel.

## Uso

Desde el menú principal se puede acceder a:

1. Gestión de clientes
2. Gestión de viajes
3. Gestión de destinos
4. Gestión de hoteles
5. Gestión de reservas
6. Búsquedas
7. Exportación a XML
8. Salir

La aplicación solicita los datos necesarios en cada operación y realiza comprobaciones para evitar introducir
información incorrecta o relaciones inexistentes.

## Estado del proyecto

Proyecto finalizado para la Unidad Didáctica 1 de Acceso a Datos.

## Posibles mejoras futuras

Aunque el proyecto cumple con las funcionalidades planteadas para la Unidad Didáctica 1, se podrían añadir diferentes
mejoras en futuras versiones:

- Permitir la lectura de los datos directamente desde los ficheros XML.
- Añadir generación y lectura de ficheros JSON.
- Añadir una interfaz gráfica para facilitar el uso de la aplicación.
- Mejorar la gestión y visualización de los datos.
- Añadir nuevas funcionalidades relacionadas con la gestión de viajes y reservas.
