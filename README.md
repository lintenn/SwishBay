# SwishBay
Repository for the subject Web Application Technologies. SwishBay is a web application for managing the sale (auctions) of products between users, similar to Ebay.

## Spanish:
### Requisitos: :heavy_check_mark:   :x: 
#### Vendedor - Galo Pérez Gallego 
- Un vendedor debe poder registrar un producto. _(no implica ponerlo en puja directamente)_
- Un vendedor debe poder poner para venta en puja un producto (indicando precio de salida y duración de la puja).
- _Un vendedor debe poder ver los productos que los demás vendedores tienen registrados o puja._ ❓
- Un vendedor debe poder ver los productos que tiene en venta.
- Un vendedor debe poder **buscar y aplicar filtrados** de búsqueda sobre los productos que tiene en venta.
- Un vendedor debe poder ver los productos que ha vendido _(y a quién)_. ❔
- Un vendedor debe poder retirar sus productos registrados o en puja o que ha vendido.
- Un vendedor debe poder editar sus productos registrados o en puja o que ha vendido.
- Un vendedor debe poder realizar la asignación del producto vendido al comprador que corresponda _(y lo notifica)_. ❔

#### Comprador - Miguel Oña Guerrero
- Un comprador debe poder ver los productos que hay registrados por vendedores. 
- Un comprador debe poder ver los productos que hay en puja por vendedores.
- ~~Un comprador debe poder comprar un producto en venta.~~ ❓
- Un comprador debe poder pujar un producto en puja con la intención de comprarlo.
- Un comprador debe poder marcar productos (estén en puja o no) como favoritos para hacer **seguimientos**. ❔
- Un comprador debe poder añadir fondos a su cuenta. ❔ 💥💥💥💥💥💥💥💥💥💥💥💥💥💥💥💥💥💥💥💥💥💥💥💥💥
- Un comprador debe poder ver en un listado los productos que ha comprado _(y a quién)_.
- Un comprador debe poder **buscar y aplicar filtrados** de búsqueda sobre los productos que ha comprado.
- _Un comprador debe poder devolver sus productos comprados_. ❔
- Un comprador debe poder ver en un listado los productos que sigue como favoritos.
- Un comprador debe poder **buscar y aplicar filtrados** de búsqueda sobre los productos que sigue como favoritos.
- Un comprador debe poder quitar productos de los que sigue como favoritos.
- Un comprador debe poder ser notificado cuando se ha cerrado el proceso de puja y si si finalmente se queda con el producto.

#### Administrador y Login/Registro - Luis Miguel García Marín
- Un administrador debe poder ver en un listado las categorías del sistema.
- Un administrador debe poder añadir categorías al sistema.
- Un administrador debe poder actualizar categorías del sistema.
- Un administrador debe poder eliminar categorías del sistema.
- Un administrador debe poder ver en un listado los usuarios del sistema.
- Un administrador debe poder añadir usuarios al sistema.
- _Un administrador debe poder modificar los usuarios del sistema_. 
- Un administrador debe poder eliminar usuarios del sistema.
- Un administrador debe poder ver en un listado los productos del sistema.
- Un administrador debe poder añadir productos al sistema.
- Un administrador debe poder modificar productos del sistema.
- Un administrador debe poder eliminar productos del sistema.
- Un administrador debe poder realizar **filtrados y búsquedas** sobre los usuarios y los productos. 
- Un administrador debe poder dar de alta (o asignar los permisos correspondientes) a los usuarios de marketing. 
- Un USUARIO debe poder registrarse en el sistema como Vendedor o Comprador (los administradores se añaden en la base de datos y estos añadirán al personal de marketing), indicando Nombre, Apellidos, Domicilio, Ciudad de residencia, _Edad_, Sexo (en los Compradores además se indicarán las categorías preferidas). 
- Un USUARIO debe poder iniciar sesión en el sistema.

#### Marketing - Angel Joaquín Rodríguez Mercado
- Un personal de marketing debe poder ver en un listado los usuarios **compradores** del sistema.
- Un personal de marketing debe poder **buscar y aplicar filtrados** de búsqueda sobre los usuarios compradores del sistema.
- Un personal de marketing debe poder crear grupos (también llamadas *listas*) de usuarios.
- Un personal de marketing debe poder modificar sus grupos creados (como su nombre).
- Un personal de marketing debe poder añadir usuarios a sus grupos creados.
- Un personal de marketing debe poder eliminar usuarios de sus grupos creados.
- Un personal de marketing debe poder eliminar sus grupos creados.
- Un personal de marketing debe poder consultar los mensajes de grupos de usuarios. ❔
- Un personal de marketing debe poder enviar mensajes a sus grupos de usuarios.
- _Un personal de marketing debe poder modificar mensajes de sus grupos de usuarios_. ❔
- _Un personal de marketing debe poder eliminar mensajes enviados a sus grupos de usuarios_.
- Un personal de marketing debe poder gestionar la recepción de los mensajes en la bandeja de entrada de mensajes de los usuarios compradores. ❓
- Un personal de marketing debe poder notificar a los usuarios de que sus productos favoritos se han abierto para puja.
