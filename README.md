# My Art List

## Tienda en Línea de Arte

### Descripción
My Art List es una aplicación web desarrollada en Java utilizando Spring Boot y Spring Security. Es una plataforma en línea que permite a los usuarios explorar y comprar pinturas y obras de arte de diversos artistas. Los usuarios pueden registrarse, iniciar sesión, añadir productos a su carrito, proceder al pago y gestionar su perfil personal.

### Características
- **Catálogo de Productos**: Navega por una amplia selección de pinturas y obras de arte.
- **Registro y Autenticación**: Sistema robusto de autenticación con JWT.
- **Carrito de Compras**: Añade productos al carrito y gestiona tus compras.
- **Proceso de Pago**: Usa tarjetas almacenadas en tu perfil para realizar pagos.
- **Perfil de Usuario**: Visualiza y edita tu información personal, tarjetas y compras.
- **Historial de Compras**: Revisa compras anteriores y sus detalles.
- **Gestión de Tarjetas**: Añade nuevas tarjetas y consulta las existentes.

---

### Requisitos
- **Java**: 17 o superior
- **Maven**: 3.6 o superior
- **MySQL**: 5.7 o superior
- **IDE Compatible**: IntelliJ IDEA, Eclipse, etc.

---

### Instalación

#### 1. Clonar el Repositorio
```bash
git clone https://github.com/tuusuario/my-art-list.git
cd my-art-list
```

#### 2. Configurar la Base de Datos
1. Crea una base de datos en MySQL llamada `springsecurity`.
2. Ejecuta el script SQL en `src/main/resources/schema.sql` para crear las tablas necesarias.
3. Configura los detalles de la base de datos en `application.properties` o `application.yml`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/springsecurity
spring.datasource.username=root
spring.datasource.password=sqlMD3
```

#### 3. Construir y Ejecutar la Aplicación
- **Usando Maven**:
```bash
mvn clean install
mvn spring-boot:run
```
- **Usando un IDE**:
    - Importa el proyecto como un proyecto Maven existente.
    - Ejecuta la clase `SpringSecurityApplication.java` como aplicación Spring Boot.

---

### Manual de Usuario

#### Flujo de Utilización
1. **Registro de Usuario**:
    - Accede a `http://localhost:8090/register`.
    - Completa el formulario con tus datos y haz clic en "Registrar".

2. **Iniciar Sesión**:
    - Accede a `http://localhost:8090/login`.
    - Ingresa tu correo y contraseña.

3. **Navegar por el Catálogo**:
    - Explora productos en `http://localhost:8090/eShop`.

4. **Agregar al Carrito**:
    - En los detalles de un producto, haz clic en "Agregar al carrito".

5. **Revisar el Carrito**:
    - Ve a `http://localhost:8090/carrito` para gestionar productos.

6. **Proceder al Pago**:
    - Selecciona una tarjeta existente o añade una nueva para confirmar el pago.

7. **Gestionar Perfil**:
    - Accede a `http://localhost:8090/perfil` para editar datos personales, revisar compras y gestionar tarjetas.

8. **Cerrar Sesión**:
    - Haz clic en "Cerrar Sesión" en el menú.

---

### Autenticación JWT

La aplicación utiliza JSON Web Tokens (JWT) para la autenticación. A continuación, se describe el proceso:

1. **Registro**:
    - Las credenciales se almacenan encriptadas en la base de datos.

2. **Generación del Token**:
    - Tras validar las credenciales, se genera un JWT con claims personalizados y se envía al cliente.

3. **Validación del Token**:
    - El servidor valida el token en cada solicitud protegida.

4. **Renovación y Expiración**:
    - Los tokens tienen un tiempo de vida limitado por seguridad.

5. **Cierre de Sesión**:
    - Se invalidan las cookies y el cliente elimina el token.

---

### Estructura del Proyecto
- **`src/main/java`**:
    - **`auth`**: Controladores y servicios de autenticación.
    - **`controller`**: Manejo de solicitudes HTTP.
    - **`entities`**: Entidades JPA.
    - **`repository`**: Acceso a datos.
    - **`security`**: Configuración y filtros de seguridad.
    - **`jwt`**: Clases para el manejo de JWT.
    - **`service`**: Lógica de negocio.
- **`src/main/resources`**:
    - **`templates`**: Vistas Thymeleaf.
    - **`static`**: CSS, JS e imágenes.
    - **`application.yml`**: Configuración de la aplicación.

---

### Base de Datos
La base de datos incluye:
- **`usuarios`**: Información personal.
- **`sec_user`**: Credenciales.
- **`productos`**: Lista de productos.
- **`ventas`**: Historial de compras.
- **`carrito_productos`**: Relación carrito-productos.
- **`tarjetas`**: Información de tarjetas.
- **`generos`**, **`carreras_profesionales`**: Datos de perfil.

---

### Contribuciones
1. Haz un fork del repositorio.
2. Crea una rama para tu función o corrección: `git checkout -b feature/nueva-funcionalidad`.
3. Realiza commits descriptivos.
4. Envía un pull request.

---

### Licencia
Este proyecto está bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.

---

### Contacto
- **Autor**: Diaz Guerrero Alan Mauricio | Ingeniero Computacional
- **Correo**: alan.diazg@hotmail.com
- **GitHub**: [AlanDiazG](https://github.com/AlanDiazG)

### Agradecimientos
Gracias por utilizar My Art List. ¡Esperamos que disfrutes de tu experiencia comprando arte en línea!

