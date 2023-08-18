# Product Prices Service

This Spring Boot application provides an API for managing product prices based on search criteria.

## Table of Contents

- [Description](#description)
- [Endpoints](#endpoints)
- [Search Criteria](#search-criteria)
- [Response Format](#response-format)
- [Usage](#usage)
- [Getting Started](#getting-started)
- [License](#license)

## Description

This application includes a single controller that handles product price searches based on specified criteria. The controller uses Spring Web and validation annotations to ensure accurate data input.

## Endpoints

| Endpoint                   | Method | Description                                       |
| -------------------------- | ------ | ------------------------------------------------- |
| `/api/v1/product-prices/search` | POST   | Retrieve product prices based on search criteria |

## Search Criteria

To search for product prices, provide a JSON payload with the following search criteria:

- `date` (Required): The date for which you want to retrieve the product price.
- `productId` (Required): The ID of the product for which you want to retrieve the price.
- `brandId` (Required): The ID of the brand associated with the product.

Example search criteria:
```json
{
  "date": "2023-08-20T10:00:00.000+00:00",
  "productId": 123,
  "brandId": 456
}
```

Por supuesto, aquí tienes el README actualizado para incluir información sobre la respuesta que se espera al hacer una búsqueda de precios en la aplicación Spring Boot:


## Response Format

When a product price search is successful, the API will respond with a JSON object containing the following information:

- `productId`: The ID of the product.
- `brandId`: The ID of the brand associated with the product.
- `priceList`: The ID of the price list.
- `startDate`: The start date of the price validity.
- `endDate`: The end date of the price validity.
- `price`: The actual price value.

Example response:
```json
{
  "productId": 123,
  "brandId": 456,
  "priceList": 4,
  "startDate": "2023-08-18T00:00:00.000+00:00",
  "endDate": "2023-08-31T23:59:59.000+00:00",
  "price": 19.99
}
```

If no matching product price is found, the API will respond with a 404 Not Found status.

## Usage

To retrieve product prices using the provided API, make a POST request to the `/api/v1/product-prices/search` endpoint with a valid JSON payload containing the search criteria. The response will include the product price information if found, or a 404 Not Found response if no match is found.

Example request:
```json
POST /api/v1/product-prices/search
Content-Type: application/json

{
  "date": "2023-08-20T10:00:00.000+00:00",
  "productId": 123,
  "brandId": 456
}
```

Example response (if found):
```json
HTTP/1.1 200 OK
Content-Type: application/json

{
  "productId": 123,
  "brandId": 456,
  "priceList": 789,
  "startDate": "2023-08-18T00:00:00.000+00:00",
  "endDate": "2023-08-31T23:59:59.000+00:00",
  "price": 19.99
}
```

Example response (if not found):
```json
HTTP/1.1 404 Not Found
```

## Getting Started

To run this Spring Boot application locally, ensure you have ***JDK 17*** installed. By default, the application uses the dev profile, which configures the H2 in-memory database. Follow these steps:

1. Clone the repository: `git clone https://github.com/ehedei/product-prices-service.git`
2. Navigate to the project directory: `cd product-prices-service`
3. Build the application: `./mvnw clean package`
4. Run the application: `java -jar target/product-prices-service.jar`

The application will start on port 8080 by default. You can access the API using the provided endpoints.

## License

This project is licensed under the [MIT License](LICENSE).
