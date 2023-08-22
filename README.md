# JSON-Data-Processing-with-Backend
This is an app that take a request with JSON from an HTTP server and returns JSON with new data.

The request includes several order that have a product name, quantity, and value.

The response provides the total quantity of all orders, the total value of all orders, and the sum of the digits for the total quantity.

## How to Use
Clone this repository onto your computer, install all dependencies, and click run. In a tool that allows you to test API routes (such as Postman or Insomnia), send your JSON to the route ```/process orders```

Your JSON must be in the following format:

```
{
"orders": [
  {
    "product": "Product 1",
    "quantity": 3,
    "unit_price": 10.0
  },
  {
    "product": "Product 2",
    "quantity": 5,
    "unit_price": 25.0
  },
  {
    "product": "Product 3",
    "quantity": 2,
    "unit_price": 50.0
  } ]
}
```

The response you recieve will look like the following:

```
{
    "total_order_value": 255.0,
    "sum_digits": 1,
    "total_orders": 10
}
```

## Technologies Used
* Java
* Spring Boot
* Dependencies:
  * Spring Boot JPA
  * Spring Boot Web
  * Hibernate
  * JSON Simple
  * Jackson

## About the Engineer
MJ Shelton is an Associate Technical Consultant at AHEAD
