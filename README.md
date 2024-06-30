# Laboratorio N°3 - Taller de Bases de Datos

Laboratorio de Taller de Bases de Datos desarrollado por el equipo 6.

## Integrantes
* [Javiera Godoy](https://github.com/JavieraGodoy)
* [Ignacio Lara](https://github.com/kappita)
* [David Martinez](https://github.com/davidmartinez-esc)
* [Claudio Palma](https://github.com/claudiopalma2001)
* [Paloma Zepeda](https://github.com/cukidev)
* [Alejandro Rojas](https://github.com/HazmatMask)

## Descripción
PóneleVoluntAPP surge como solución a la necesidad de coordinar eficazmente el flujo de voluntarios espontáneos en situaciones de emergencia en Chile. 

Esta plataforma facilita la gestión de esfuerzos voluntarios, emparejando habilidades y disponibilidades con las necesidades específicas de cada emergencia, y permitiendo a los coordinadores organizar y asignar tareas de forma estratégica para una respuesta ágil y efectiva ante desastres naturales.

El repositorio incluye las siguientes carpetas principales:
* [Carpeta Backend](https://github.com/kappita/lab-tbd/tree/v2.0/backend): Contiene todo el Backend y las clases de las entidades las cuales se distribuyen en los paquetes: Controllers, Entities, Services y Repositories.
* [Carpeta Frontend](https://github.com/kappita/lab-tbd/tree/v2.0/frontend): Contiene todo el Frontend y las vistas utilizadas con Vue.
* [Carpeta Database](https://github.com/kappita/lab-tbd/tree/v2.0/database): Contiene archivo de texto loadData.txt, en el cual se encuentran las sentencias y comandos necesarios tanto para crear la base de datos, como para poblar la Base de Datos.

## Requisitos y Versiones de las Tecnologías.

* [MongoDB Community Edition](https://www.mongodb.com/try/download/community) versión 7.0.12.
* [JJWT](https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt) versión 0.12.5.
* [MongoDB Shell](https://www.mongodb.com/try/download/shell) 2.2.10 ó [MongoDB Compass (GUI)](https://www.mongodb.com/try/download/compass) 1.43.3 (_Stable_).
* [IntelliJ IDEA Ultimate](https://www.jetbrains.com/es-es/idea/download/#section=windows) versión 2024.1, aunque debería funcionar con cualquier versión.
* JDK Versión 17.
* [VUE](https://es.vuejs.org/v2/guide/installation) versión 2.x.
* [axios](https://axios-http.com/es/) versión 1.6.8.

## Instrucciones de Instalación.

1. Clonar el repositorio: Esto proporcionará las tres carpetas principales para el proyecto de Laboratorio. Para clonarlo, se debe usar el siguiente comando en el directorio deseado:
```sh
git clone https://github.com/HazmatMask/lab3-TBD
```
2. Instalar MongoDB + MongoDB Shell o MongoDB Compass (GUI): Es necesario instalar tanto el servidor como una herramienta para acceder a esta. Los tres enlaces han sido listados previamente en Requisitos y Versiones. Community Edition corresponde al servidor, y se puede elegir la Shell de Mongo o Compass, una Interfaz Gráfica de Usuario, la cual posee una implementación propia de la Shell de Mongo en ella.
**DISCLAIMER: Para poder conectarse a la Base de Datos correctamente, se deben configurar las mismas credenciales que están puestas en [aplication.properties](https://github.com/HazmatMask/lab3-TBD/blob/main/backend/src/main/resources/application.properties) en el backend.**


3. Instalar IntelliJ IDEA Ultimate: Ingresar al sitio web oficial de IntelliJ IDEA y seleccionar una opción dependiendo del Sistema Operativo que use:
* Para Windows, utilizar el siguiente [link](https://www.jetbrains.com/idea/download/#section=windows).
* Para Linux, utilizar el siguiente [link](https://www.jetbrains.com/es-es/idea/download/#section=linux).
* Para MacOs, utilizar el siguiente [link](https://www.jetbrains.com/es-es/idea/download/#section=mac).

4. Abrir IntelliJ IDEA Ultimate, abrir un nuevo proyecto y seleccionar la carpeta **Backend**. Debemos esperar unos minutos a que el proyecto se configure y se instalen los plugins necesarios.


5. Configurar las credenciales del aplication.properties del disclaimer del punto 2.


6. Configurar el JDK: Al abrir una clase IntelliJ nos solicitará configurar un JDK. Debemos asegurarnos de seleccionar JDK versión 17. De no tener JDK 17, IntelliJ nos proporciona una opción para instalarlo automáticamente.
- Si no nos aparece esta opción porque ya tenía IntelliJ instalado anteriormente, basta con ir a la navbar. File -> Proyect Structure. En esa sección en el apartado de SDK, se podrá cambiar la versión.

7. Ir al siguiente [link](https://nodejs.org/en/) e instalar la última versión de Node.js, siguiendo las instrucciones de instalación.


8. Abrir una consola / terminal dentro de la carpeta **Frontend** y aplicar el siguiente comando, el cual instalará todas las dependencias necesarias para el proyecto:
```sh
npm install
```
* Si por algún motivo lo anterior falla, se puede utilizar:
```sh
npm install --force
```

Una vez finalizado estos nueve pasos, podemos seguir con las instrucciones de uso.

## Instrucciones de uso

1. Abrir la carpeta de sistema de MongoDB - usualmente en la "C:/Program Files/MongoDB/Server/7.0/bin", y ejecutar el archivo "_mongod_". Esto ejecutará motor principal de la base de datos.


2. Abrir la Shell de MongoDB o MongoDB Compass, y ejecutar en la línea de comando las instrucciones de creación y carga establecidas en el archivo [loadData.txt](https://github.com/HazmatMask/lab3-TBD/blob/main/database/loadData.txt).


3. Utilizando IntelliJ IDEA Ultimate, asegurarse de que se detecte correctamente Spring Boot y Gradle.


4. Antes de ejecutar el proyecto, asegurarse de que en el archivo backend/src/main/resources/application.properties se encuentren las credenciales correctas de la base de datos


5. Ejecutar la aplicación haciendo click en la opción "Run" dentro de IntelliJ IDEA.


6. Dentro de la carpeta Frontend, abrir una consola / terminal. Ejecutar el siguiente comando:
```sh
npm install
```
Una vez instalado, ejecutar el siguiente comando:
```sh
npm run dev
```
7. Con esto ya tenemos levantado FrontEnd y Backend.


8. Con esto, y la Base de Datos poblada, podemos acceder, a través de nuestro navegador, a la dirección de la [App](http://localhost:5173/)
- Es posible cambiar esta ruta modificando los archivos necesarios en el proyecto, pero actualmente está establecida en esa ubicación.
