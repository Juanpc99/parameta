# Prueba Técnica Developer - Parametra

Este proyecto consiste en una aplicación Spring Boot que permite **almacenar empleados** en una base de datos utilizando una arquitectura compuesta por:

-  Un servicio **REST (GET)** que recibe los datos del empleado por `queryParams`
-  Un servicio **SOAP interno** que es consumido por el servicio REST
-  Persistencia en base de datos H2 para facilitar las pruebas sin depender de una base de datos externa

---

## Atributos del Empleado

El objeto `Empleado` contiene los siguientes campos:

| Campo                    | Tipo    |
|--------------------------|---------|
| `nombres`                | String  |
| `apellidos`              | String  |
| `tipoDocumento`          | String  |
| `numeroDocumento`        | String  |
| `fechaNacimiento`        | Date    |
| `fechaVinculacion`       | Date    |
| `cargo`                  | String  |
| `salario`                | Double  |

---

## Endpoint REST

Se expone un único endpoint `GET` que recibe todos los datos del empleado por **query parameters** y los envía al **servicio SOAP**, el cual realiza el almacenamiento en base de datos.
El servicio REST retorna un objeto `Empleado` con la siguiente estructura JSON:

```json
{
  "id": 1,
  "nombres": "Juan  Pablo",
  "apellidos": "Caro Vargas",
  "tipoDocumento": "afsd",
  "numeroDocumento": "1000858956",
  "fechaNacimiento": "1999-04-13T05:00:00.000+00:00",
  "fechaVinculacion": "2021-04-12T05:00:00.000+00:00",
  "cargo": "Lider",
  "salario": 2000.0,
  "edad": {
    "anos": 25,
    "meses": 11,
    "dias": 30
  },
  "antiguedad": {
    "anos": 4,
    "meses": 0,
    "dias": 0
  }
}
```

## Consideraciones Técnicas

- Se decide utilizar el tipo de dato `Date` para las fechas y no `LocalDate`, ya que la prueba **expresa directamente que se debe utilizar `Date`** para almacenar la fecha.

- El servicio SOAP es **consumido directamente desde la clase `EmpleadoService`**, que actúa como caso de uso principal en la arquitectura.

- Desde el servicio SOAP, se utiliza el componente **`EmpleadoRepository`** para almacenar el registro en base de datos.

- De esta forma, se cumple con el requerimiento de que el servicio REST **no realice el guardado directo**, sino que delegue esta responsabilidad al **servicio SOAP como intermediario**.

- Los archivos de la carpeta soap.cliet se generaron con el comando wsimport -keep -p com.parameta.soap.client http://localhost:8081/ws/empleado.wsdl

- Se utilizo Java 8 para poder soportar SOAP de manera nativa en el JDK


## Prerrequisitos para ejecutar el proyecto

- **Java 8**
- **Gradle 8.13**
- **Spring Boot 2.4.4**



### URL de consumo REST

```http
http://localhost:8081/api/save?nombres=Juan%20%20Pablo&apellidos=Caro%20Vargas&tipoDocumento=CC&numeroDocumento=1000858956&fechaNacimiento=1999-04-13&fechaVinculacion=2025-04-12&cargo=Lider&salario=2000
```

### URL de consumo del servicio SOAP directamente


#### URL
```http
http://localhost:8081/ws
```

#### Cuerpo

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:emp="http://127.0.0.1/empleado">
   <soapenv:Header/>
   <soapenv:Body>
      <emp:guardarEmpleadoRequest>
         <emp:nombres>Juan</emp:nombres>
         <emp:apellidos>Caro</emp:apellidos>
         <emp:tipoDocumento>CC</emp:tipoDocumento>
         <emp:numeroDocumento>123456789</emp:numeroDocumento>
         <emp:fechaNacimiento>1999-09-10</emp:fechaNacimiento>
         <emp:fechaVinculacion>2024-04-12</emp:fechaVinculacion>
         <emp:cargo>Ingeniero</emp:cargo>
         <emp:salario>5000000.0</emp:salario>
      </emp:guardarEmpleadoRequest>
   </soapenv:Body>
</soapenv:Envelope>
```
