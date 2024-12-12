My Art List
===========

**Tienda en Línea de Arte**

Descripción
-----------

My Art List es una aplicación web desarrollada en Java utilizando Spring Boot y Spring Security. Es una plataforma en línea que permite a los usuarios explorar y comprar pinturas y obras de arte de diversos artistas. Los usuarios pueden registrarse, iniciar sesión, añadir productos a su carrito, proceder al pago y gestionar su perfil personal.

Características
---------------
*   **Inicio**: Cuenta con una gran colección de obras artísticas para que el usuario sea capaz de analizarlas.

*   **Catálogo de Productos**: Navega por una amplia selección de pinturas y obras de arte.

*   **Registro y Autenticación**: Los usuarios pueden registrarse y autenticarse utilizando un sistema de seguridad robusto con JWT.

*   **Carrito de Compras**: Añade productos al carrito y gestiona tu lista de compras.

*   **Proceso de Pago**: Procede al pago utilizando tarjetas almacenadas en tu perfil.

*   **Perfil de Usuario**: Visualiza y edita tu información personal, gestiona tus tarjetas y revisa tu historial de compras.

*   **Gestión de Tarjetas**: Añade nuevas tarjetas y consulta los detalles de las existentes.

*   **Historial de Compras**: Revisa las compras anteriores y visualiza los detalles de cada una.

*   **Funciones de Administrador**: Gestiona productos, pinturas y artistas disponibles en la plataforma.


Requisitos
----------

*   **Java** 17 o superior

*   **Maven** 3.6 o superior

*   **MySQL** 5.7 o superior

*   Un **IDE** compatible con Spring Framework (IntelliJ IDEA, Eclipse, etc.)


Instalación
-----------

### 1\. Clonar el Repositorio

git clone https://github.com/tuusuario/my-art-list.git

cd my-art-list

### 2\. Configurar la Base de Datos

*   Crea una base de datos en MySQL llamada **springsecurity**.

*   Ejecuta el script SQL proporcionado en **src/main/resources/schema.sql** para crear las tablas necesarias.

*   Modifica el archivo **application.properties** o **application.yml** para configurar los detalles de la base de datos:

spring.datasource.url=jdbc:mysql://localhost:3306/springsecurity

spring.datasource.username=root

spring.datasource.password=sqlMD3

### 3\. Construir y Ejecutar la Aplicación

#### Usando el IDE

*   Importa el proyecto como un proyecto Maven existente.

*   Ejecuta la clase **SpringSecurityApplication.java** como una aplicación Spring Boot.


Manual de Usuario
-----------------

### Flujo de Utilización del Proyecto

#### 1\. Registro de Usuario

1.  Navega a la página de registro haciendo clic en "Register" o accediendo a **http://localhost:8090/register**.

2.  Completa el formulario con tus datos personales:

   *   Nombre

   *   Apellido

   *   Correo electrónico

   *   Contraseña

3.  Haz clic en "Registrar". Si el registro es exitoso, serás redirigido a una página de confirmación.


#### 2\. Iniciar Sesión

1.  Accede a la página de inicio de sesión en **http://localhost:8090/login**.

2.  Ingresa tu correo electrónico y contraseña.

3.  Haz clic en "Iniciar Sesión". Serás redirigido a la página principal.


#### 3\. Navegar por el Catálogo de Productos

1.  En la página principal o a través del menú, navega a "eShop" o **http://localhost:8090/eShop**.

2.  Explora las pinturas y obras de arte disponibles.

3.  Haz clic en un producto para ver más detalles.


#### 4\. Añadir Productos al Carrito

1.  En la página de detalles del producto, haz clic en "Agregar al carrito".

2.  Puedes continuar navegando y agregando más productos.


#### 5\. Ver el Carrito de Compras

1.  Haz clic en "Carrito" en el menú o accede a **http://localhost:8090/carrito**.

2.  Revisa los productos en tu carrito, modifica las cantidades o elimina productos.

3.  El total se actualizará automáticamente.


#### 6\. Proceder al Pago

1.  En el carrito, haz clic en "Proceder al Pago".

2.  Serás redirigido a la página de opciones de pago.

3.  Selecciona una tarjeta existente o añade una nueva tarjeta.

4.  Revisa el total a pagar y confirma el pago.


#### 7\. Añadir una Nueva Tarjeta

1.  En la página de pago, si no tienes tarjetas o deseas añadir una nueva, haz clic en "Añadir Nueva Tarjeta".

2.  Completa el formulario con los detalles de la tarjeta:

   *   Tipo de tarjeta (Visa, MasterCard, Amex)

   *   Número de tarjeta

   *   Nombre y apellido del titular

   *   Fecha de expiración

   *   Saldo inicial

3.  Guarda la tarjeta y regresa al proceso de pago.


#### 8\. Confirmación de Pago

1.  Después de confirmar el pago, recibirás una notificación de que el pago ha sido exitoso.

2.  Serás redirigido a una página de confirmación.


#### 9\. Gestionar Perfil de Usuario

1.  Accede a tu perfil haciendo clic en "Mi Perfil" en el menú o visitando **http://localhost:8090/perfil**.

2.  **Ver Datos Personales**: Revisa tu información como nombre, correo, teléfono, dirección, género y carrera profesional.

3.  **Editar Datos Personales**:

   *   Haz clic en "Editar Datos Personales".

   *   Modifica la información que desees.

   *   Guarda los cambios.

4.  **Ver Historial de Compras**: En la sección de compras anteriores, puedes ver todas las compras realizadas.

   *   Haz clic en "Detalles" para ver más información sobre una compra específica.

5.  **Gestionar Tarjetas**:

   *   Revisa las tarjetas asociadas a tu cuenta.

   *   Añade nuevas tarjetas si es necesario.


#### 10\. Funciones de Administrador

**Acceso de Administrador:**

*   **Correo electrónico**: **alan@correo.com**

*   **Contraseña**: **password**


Una vez autenticado como administrador, tendrás acceso a opciones adicionales en el menú.

**Como administrador, puedes:**

*   **Gestionar Productos**:

   *   Añadir nuevos productos al catálogo.

   *   Modificar o eliminar productos existentes.

*   **Gestionar Pinturas**:

   *   Añadir nuevas pinturas a la colección.

   *   Editar detalles de pinturas existentes.

*   **Gestionar Artistas**:

   *   Añadir nuevos artistas a la base de datos.

   *   Actualizar información de artistas existentes.


**Cómo Utilizar las Funciones de Administrador:**

*   **Añadir un Producto**:

   1.  Navega a "Gestionar Productos" en el menú.

   2.  Haz clic en "Añadir Producto".

   3.  Completa el formulario con los detalles del producto:

      *   Clave del producto

      *   Nombre

      *   Precio

      *   Descripción

      *   Stock

   4.  Haz clic en "Guardar" para añadir el producto al catálogo.

*   **Modificar o Eliminar un Producto**:

   1.  En la lista de productos, localiza el producto que deseas modificar o eliminar.

   2.  Haz clic en "Modificar" para editar los detalles o en "Eliminar" para quitar el producto.

*   **Añadir una Pintura**:

   1.  Navega a "Gestionar Pinturas" en el menú.

   2.  Haz clic en "Añadir Pintura".

   3.  Completa el formulario con los detalles de la pintura:

      *   Título de la obra

      *   Año de creación

      *   Técnica utilizada

      *   Detalles adicionales

      *   ID del artista asociado

   4.  Haz clic en "Guardar" para añadir la pintura.

*   **Modificar o Eliminar una Pintura**:

   1.  En la lista de pinturas, localiza la que deseas modificar o eliminar.

   2.  Haz clic en "Modificar" para editar los detalles o en "Eliminar" para quitar la pintura.

*   **Añadir un Artista**:

   1.  Navega a "Gestionar Artistas" en el menú.

   2.  Haz clic en "Añadir Artista".

   3.  Completa el formulario con los detalles del artista:

      *   Nombre

      *   Nacionalidad

   4.  Haz clic en "Guardar" para añadir al artista.

*   **Modificar o Eliminar un Artista**:

   1.  En la lista de artistas, encuentra el artista que deseas modificar o eliminar.

   2.  Haz clic en "Modificar" para editar la información o en "Eliminar" para quitar al artista.

  

**Notas Importantes para el Administrador:**

*   Asegúrate de proporcionar información precisa y completa al añadir o modificar registros.

*   Al eliminar elementos, confirma que deseas proceder, ya que esta acción puede ser irreversible.

*   Los cambios realizados afectarán a todos los usuarios de la plataforma.


#### 11\. Cerrar Sesión

*   Para salir de tu cuenta, haz clic en "Cerrar Sesión" en el menú.


Autenticación JWT
-----------------

### Descripción del Proceso de Autenticación con JWT

La aplicación utiliza **JSON Web Tokens (JWT)** para manejar la autenticación y autorización de los usuarios de manera segura y eficiente. A continuación se detalla el proceso:

#### 1\. Registro de Usuarios

*   Cuando un usuario se registra, sus credenciales (correo electrónico y contraseña) se almacenan en la base de datos de autenticación (**sec\_user**).

*   La contraseña se almacena **encriptada** utilizando BCrypt para garantizar su seguridad.


#### 2\. Inicio de Sesión y Generación del Token JWT

*   Al iniciar sesión, el usuario proporciona sus credenciales.

*   La aplicación valida las credenciales contra la base de datos de autenticación.

*   Si la autenticación es exitosa:

   *   Se genera un **JWT** que contiene:

      *   **Claims personalizados** como el email, nombre completo y roles del usuario.

      *   El **Issuer (iss)** e **Issued At (iat)**.

      *   La **Fecha de Expiración (exp)** que determina la validez del token.

   *   El token JWT se firma utilizando una **clave secreta** definida en la configuración de la aplicación.

   *   El token se envía al cliente y se almacena en una **cookie segura** o se incluye en los encabezados de las solicitudes subsecuentes.


#### 3\. Validación del Token JWT en Solicitudes Posteriores

*   Cada vez que el cliente realiza una solicitud a una ruta protegida:

   *   El token JWT se envía al servidor (por ejemplo, en una cookie o en el encabezado **Authorization**).

   *   Un **filtro de autenticación JWT** intercepta la solicitud y realiza las siguientes acciones:

      *   **Extrae** el token JWT de la solicitud.

      *   **Valida** el token:

         *   Verifica la firma utilizando la clave secreta.

         *   Comprueba la fecha de expiración.

         *   Valida el formato y contenido del token.

      *   **Extrae los claims** relevantes (correo electrónico, roles, etc.).

      *   **Autentica al usuario** en el contexto de seguridad de Spring utilizando los datos del token.

*   Si el token es válido, la solicitud procede y el usuario tiene acceso a los recursos protegidos.


#### 4\. Manejo de Autorización

*   La aplicación utiliza **roles y permisos** para controlar el acceso a distintas partes de la aplicación.

*   Los roles del usuario (por ejemplo, **USER**, **ADMIN**) se extraen del token JWT y se utilizan para determinar si el usuario está autorizado para acceder a ciertos recursos o realizar acciones específicas.

*   La configuración de seguridad se define utilizando **Spring Security**, donde se especifican qué rutas requieren autenticación y qué roles son necesarios.


#### 5\. Renovación y Expiración del Token

*   Los tokens JWT tienen una **vida limitada** por razones de seguridad.

*   Si un token ha expirado, el usuario debe autenticarse nuevamente para obtener un nuevo token.

*   La aplicación maneja las excepciones de tokens expirados y redirige al usuario al proceso de inicio de sesión.


#### 6\. Cierre de Sesión

*   Al cerrar sesión, se invalidan las sesiones y se eliminan las cookies que almacenan el token JWT.

*   Aunque el token JWT es **stateless**, el cliente al eliminar el token previene su uso posterior.


### Seguridad y Buenas Prácticas

*   **Clave Secreta**: La clave utilizada para firmar los tokens JWT es confidencial y se define en el archivo de configuración.

*   **Almacenamiento Seguro**: Los tokens JWT se almacenan en cookies con las banderas **HttpOnly** y **Secure** para prevenir ataques XSS y asegurar que solo se envían a través de HTTPS.

*   **Datos Sensibles**: Se evita incluir información sensible en los tokens JWT. Solo se incluyen los datos necesarios para la autenticación y autorización.

*   **Protección Contra Ataques**: Se implementan medidas para proteger contra ataques comunes como CSRF, XSS e inyecciones.


Estructura del Proyecto
-----------------------

*   **src/main/java**: Código fuente de la aplicación.

   *   **auth**: Controladores y servicios relacionados con la autenticación y registro de usuarios.

   *   **controller**: Controladores para manejar las solicitudes HTTP.

      *   **pago**: Controladores relacionados con el proceso de pago.

      *   **perfil**: Controladores para manejar el perfil del usuario.

      *   **venta**: Controladores para gestionar las ventas.

      *   **administrador**: Controladores para las funciones de administrador.

   *   **entities**: Entidades JPA que representan las tablas de la base de datos.

   *   **repository**: Interfaces de repositorios para acceso a datos.

   *   **security**: Configuración de seguridad, filtros y proveedores de autenticación.

      *   **jwt**: Clases relacionadas con el manejo de JWT (generación, validación, filtros).

   *   **service**: Interfaces e implementaciones de servicios para la lógica de negocio.

      *   **tarjeta**, **venta**, **usuario**, **pintura**, **artista**, **producto**: Servicios específicos para cada funcionalidad.

*   **src/main/resources**:

   *   **templates**: Vistas Thymeleaf (HTML).

      *   **pago**: Vistas relacionadas con el proceso de pago.

      *   **perfil**: Vistas del perfil de usuario.

      *   **venta**: Vistas para visualizar detalles de ventas.

      *   **administrador**: Vistas para las funciones de administrador (gestión de productos, pinturas y artistas).

   *   **static**: Archivos estáticos como CSS, JavaScript e imágenes.

   *   **application.properties**: Configuración de la aplicación.


Base de Datos
-------------

La base de datos **springsecurity** incluye las siguientes tablas:

*   **usuarios**: Almacena la información personal de los usuarios.

*   **sec\_user**: Tabla de autenticación que almacena las credenciales seguras de los usuarios.

*   **productos**: Lista de productos disponibles en la tienda.

*   **pinturas**: Detalles de las pinturas disponibles.

*   **artistas**: Información sobre los artistas.

*   **ventas**: Registra las ventas y carritos de los usuarios.

*   **carrito\_productos**: Relaciona los productos con los carritos.

*   **tarjetas**: Almacena la información de las tarjetas de los usuarios.

*   **generos**, **carreras\_profesionales**: Tablas que almacenan información relacionada con el perfil del usuario.


Notas Adicionales
-----------------

*   **Seguridad de Datos**: Se han implementado medidas para asegurar que los datos personales y sensibles se manejan adecuadamente.

*   **Validaciones**: Se utilizan validaciones tanto en el frontend como en el backend para asegurar la integridad de los datos.

*   **Entorno de Desarrollo**: Aunque se han implementado medidas de seguridad, en un entorno de producción se deben reforzar y cumplir con regulaciones como PCI DSS para el manejo de información de tarjetas.


Contribuciones
--------------

Las contribuciones son bienvenidas. Puedes seguir estos pasos:

1.  Haz un fork del repositorio.

2.  Crea una rama nueva con la funcionalidad o corrección (**git checkout -b feature/nueva-funcionalidad**).

3.  Realiza tus cambios y realiza commits descriptivos.

4.  Envía un pull request explicando detalladamente los cambios realizados.


Licencia
--------

Este proyecto está bajo la Licencia MIT. Consulta el archivo **LICENSE** para más detalles.

Contacto
--------

Si tienes preguntas o sugerencias, no dudes en ponerte en contacto:

*   **Autor**: Alan Mauricio Díaz Guerrero

*   **Correo electrónico**: [alan.diazg@hotmail.com](mailto:alan.diazg@hotmail.com)

*   **GitHub**: [AlanDiazG](https://github.com/AlanDiazG)


Agradecimientos
---------------

Gracias por utilizar My Art List, proyecto final del diplomado Desarrollo de sistemas con tecnología Java. Esperamos que disfrutes de tu experiencia comprando y explorando arte en línea.