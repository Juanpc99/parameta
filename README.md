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

## Consideraciones Técnicas

- Se decide utilizar el tipo de dato `Date` para las fechas y no `LocalDate`, ya que la prueba **expresa directamente que se debe utilizar `Date`** para almacenar la fecha.

- El servicio SOAP es **consumido directamente desde la clase `EmpleadoService`**, que actúa como caso de uso principal en la arquitectura.

- Desde el servicio SOAP, se utiliza el componente **`EmpleadoRepository`** para almacenar el registro en base de datos.

- De esta forma, se cumple con el requerimiento de que el servicio REST **no realice el guardado directo**, sino que delegue esta responsabilidad al **servicio SOAP como intermediario**.


### URL de consumo

```http
http://localhost:8081/api/save?nombres=Juan%20%20Pablo&apellidos=Caro%20Vargas&tipoDocumento=CC&numeroDocumento=1000858956&fechaNacimiento=1999-04-13&fechaVinculacion=2025-04-12&cargo=Lider&salario=2000

