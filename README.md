# API REST para Gestión de Precios

Esta es una API REST construida con **Spring Boot** que sigue la arquitectura hexagonal. La API expone un servicio en el endpoint `/prices` para gestionar información relacionada con precios.

## Requisitos del Sistema

- **JDK**: 17
- **Maven**: 3+

## Instalación y Configuración

1. **Clonar el Repositorio**:
   ```bash
   git clone https://github.com/androla10/zbvm-prac-pbtc-between
   cd zbvm-prac-pbtc-between
   ```

2. **Configurar el Entorno**:
   Asegúrate de tener **JDK 17** y **Maven 3+** instalados. Puedes verificar las versiones con los siguientes comandos:
   ```bash
   java -version
   mvn -version
   ```

3. **Construir el Proyecto**:
   Ejecuta el siguiente comando en la raíz del proyecto para construir la aplicación:
   ```bash
   mvn clean install
   ```

## Ejecutar la Aplicación

Para levantar el proyecto, utiliza el siguiente comando:
```bash
mvn spring-boot:run
```

La aplicación estará disponible en `http://localhost:8091/prices`.

## Endpoints

### Obtener Precios

- **GET** `/prices`
    - Descripción: Obtiene una lista de todos los precios.
    - Body (JSON):
        ```json
        [
            {
              "brandId": 1,
              "startDate": "2020-06-15T16:00:00-05:00",
              "endDate": "2020-12-31T23:59:59-05:00",
              "priceList": 4,
              "productId": 35455,
              "price": 38.95,
              "currency": "EUR" 
            }
      ]
        ```
      
## Pruebas

La aplicación incluye pruebas de integración y unitarias. Puedes ejecutarlas utilizando el siguiente comando:
```bash
mvn test
```

Las pruebas están configuradas para asegurar la correcta funcionalidad de la API y su lógica de negocio.

## Postman
Puedes encontrar en la ruta raíz la colección de postman para su uso.
[Postman Collections](Inditex%20Core%20Platform.postman_collection.json).

## Contribuciones

Si deseas contribuir, por favor abre un **issue** o envía un **pull request**.