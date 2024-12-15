# Gestion de examenes backend

### Manual Installation

1. Clone el repositorio en su máquina local desde el repositorio remoto de GitHub o descargue el archivo zip  
- El repositorio se encuentra en el siguiente enlace: [back-examenes-spring-boot](https://github.com/BETTMOSH/back-examenes-spring-boot)  
- Mediantes el comando `git clone [https://github.com/BETTMOSH/back-examenes-spring-boot]` o descargando el archivo zip.
2. Importe el proyecto en su IDE favorito.
3. Actualice las dependencias del proyecto con `Maven version 3.3.5` y `jdk 17 coretto`.
4. Ejecute la aplicación desde la clase principal `GestionExamenesBackendApplication`.
5. Una vez el front haya sido ejecutado, para acceder como admin por defecto es:  
    - `username: bettmosh`  
    - `password: 12345`

### Instalacion de Docker

1. Clone el repositorio en su máquina local desde el repositorio remoto de GitHub o descargue el archivo zip.
2. Abra un terminal y navegue hasta la carpeta raíz del proyecto.
3. Ejecute el comando `docker-compose up` para construir y ejecutar la aplicación en un contenedor Docker.
4. Para detener la aplicación, ejecute el comando `docker-compose down`.

### Variables de entorno

Se ha configurado las siguientes variables de entorno para mas seguridad y el correcto funcionamiento de la aplicación:

- `GE_URL`: URL de la base de datos.
- `GE_USERNAME`: Usuario de la base de datos.
- `GE_PASSWORD`: Contraseña de la base de datos.
- `JWT_SECRET = "claveSecreta"`: Clave secreta para la generación de tokens JWT.
- `JWT_EXPIRATION_TIME = 86400000`: Tiempo de expiración del token JWT en milisegundos.

