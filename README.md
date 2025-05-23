# hotel-application

## API Reference

### Get all hotels

```http
GET /property-view/hotels
```

Receive all hotels in short form.

### Get a hotel by ID

```http
GET /property-view/hotels/{id}
```

| Parameter | Type   | Required | Description     |
|:----------|:-------|:---------|:----------------|
| `id`      | `Long` | Yes      | ID of the hotel |

Receive detailed information about hotel by id.

### Search hotels with filters

```http
GET /property-view/search
```

| Query Parameter | Type                     | Required | Description                           |
|:----------------|:-------------------------|:---------|:--------------------------------------|
| `name`          | `List<string> or string` | No       | Hotel name                            |
| `brand`         | `List<string> or string` | No       | Brand of the hotel                    |
| `city`          | `List<string> or string` | No       | City where the hotel is located       |
| `country`       | `List<string> or string` | No       | Country where the hotel is located    |
| `amenities`     | `List<string> or string` | No       | List of amenities to filter hotels by |

Search hotels by criteria.

### Create a new hotel

```http
POST /property-view/hotels
```

| Field                  | Type           | Required | Description                 | Constraints               |
|------------------------|----------------|----------|-----------------------------|---------------------------|
| `name`                 | `string`       | Yes      | Name of the hotel           | Not blank                 |
| `description`          | `string`       | No       | Description of the hotel    |                           |
| `brand`                | `string`       | Yes      | Brand of the hotel          | Not blank                 |
| `address.houseNumber`  | `integer`      | Yes      | House number in address     | Positive number           |
| `address.street`       | `string`       | Yes      | Street name                 | Not blank                 |
| `address.city`         | `string`       | Yes      | City                        | Not blank                 |
| `address.country`      | `string`       | Yes      | Country                     | Not blank                 |
| `address.postCode`     | `string`       | Yes      | Postal code                 | Not blank                 |
| `contacts.phone`       | `string`       | Yes      | Contact phone number        | Not blank                 |
| `contacts.email`       | `string`       | Yes      | Contact email               | Valid email format        |
| `arrivalTime.checkIn`  | `string`       | Yes      | Check-in time               | Not blank, format "HH:MM" |
| `arrivalTime.checkOut` | `string`       | No       | Check-out time              | Format "HH:MM"            |
| `amenities`            | `List<string>` | No       | List of available amenities |                           |

Returns the created hotel object.

### Add amenities to a hotel

```http
POST /property-view/hotels/{id}/amenities
```

### Path Parameters

| Parameter | Type   | Required | Description     |
|:----------|:-------|:---------|:----------------|
| `id`      | `Long` | Yes      | ID of the hotel |

### Request Body

| Parameter   | Type           | Required | Description                  |
|:------------|:---------------|:---------|:-----------------------------|
| `amenities` | `List<string>` | Yes      | List of new amenities to add |

Adds new amenities to an existing hotel.

### Get hotel histogram data

```http
GET /property-view/histogram/{param}
```

| Parameter | Type     | Required | Description                                                  |
|:----------|:---------|:---------|:-------------------------------------------------------------|
| `param`   | `string` | Yes      | Parameter to generate histogram (e.g., city, country, brand) |

Returns a histogram with counts based on the provided parameter.

## Installation & Running

### Clone the repository

```bash
git clone https://github.com/vanzoneway/hotel-application.git
```

### Run the application

From the root of the project.

```bash
mvn spring-boot:run
```

## Database Configuration

hotel-application uses an H2 database by default.
If you want to use PostgreSQL run application with ENV variable ACTIVE_PROFILE=postgres and change default values
in application-postgres.yml or create another application-xxxxx.yml for another config and use ACTIVE_PROFILE=xxxxx.

### Tests

There are some unit and integration tests for service and controllers layers to show example how it must be covered
with RestAssured, MockMvc and other configuration parameters.

## Technologies Used

- **Spring Boot**
- **Spring Web**
- **Spring Data JPA**
- **Liquibase**
- **H2 Database (default, but configurable)**
- **Maven**
- **MapStruct**
- **Spring Validation**
- **Lombok**
- **OpenApi (Swagger)**
- **RestAssured**
- **JUnit**
- **Spring Test**

## Author

Developed by Ivan Zinovich.