
Link al repositorio: https://github.com/usuario/saludo_con_firebase.git

# Saludo con Firebase

Esta es una aplicación de Android que permite mostrar un saludo personalizado en función de la hora del día y permite al usuario cambiar el color de fondo de la interfaz a través de un conjunto de opciones predefinidas. Además, la aplicación usa `SharedPreferences` para guardar las preferencias del usuario, como el color de fondo, y garantizar que se mantengan incluso después de cerrar y reabrir la aplicación.

## Funcionalidades

1. **Saludo personalizado**: La aplicación muestra un saludo que cambia según la hora del día:
    - **Buenos días**: entre las 5:00 AM y las 13:00 PM.
    - **Buenas tardes**: entre las 14:00 PM y las 22:00 PM.
    - **Buenas noches**: entre las 22:00 PM y las 5:00 AM.

2. **Cambio de color de fondo**: Desde la pantalla de configuración, el usuario puede cambiar el color de fondo de la aplicación a uno de los siguientes colores:
    - Blanco
    - Gris
    - Rojo
    - Verde
    - Azul

3. **Persistencia de preferencias**: Los colores seleccionados por el usuario se guardan en `SharedPreferences` y se aplican automáticamente cada vez que el usuario vuelve a abrir la aplicación, incluso después de cerrar y reiniciar la aplicación.

4. **Navegación entre pantallas**:
    - La **pantalla principal** muestra el saludo y un botón para ir a la pantalla de configuración.
    - La **pantalla de configuración** permite al usuario elegir un nuevo color de fondo y volver a la pantalla principal.

## Estructura de la aplicación

La aplicación está dividida en dos actividades principales:

1. **MainActivity**:
    - Muestra el saludo adecuado según la hora del día.
    - Ofrece un botón que navega a la pantalla de configuración.

2. **SettingsActivity**:
    - Permite al usuario seleccionar el color de fondo.
    - El color de fondo se guarda en `SharedPreferences` y se aplica de inmediato.

## Instalación

1. Clona este repositorio:
   ```bash
   git clone https://github.com/usuario/saludo_con_firebase.git
