# Development-Books

DevelopmentBooks is a backend application that calculates the optimal price of a shopping basket containing books, applying discounts based on unique book combinations.The application takes a list of books and returns the minimum possible price after applying the best discounts.

The system supports the following books:

1. Clean Code (Robert Martin, 2008)
2. The Clean Coder (Robert Martin, 2011)
3. Clean Architecture (Robert Martin, 2017)
4. Test Driven Development by Example (Kent Beck, 2003)
5. Working Effectively with Legacy Code (Michael Feathers, 2004)

Find More Information related to Kata https://stephane-genicot.github.io/DevelopmentBooks.html

## **PricingRules**

Each book costs 50 EUR.
Discounts are applied when buying different books:
- different books → 5% discount
- different books → 10% discount
- different books → 20% discount
- different books → 25% discount

If books are repeated:
- Discounts apply only to unique sets
- Remaining books are calculated separately

## **Purpose**

Develop an application using TDD(Test Driven Development) that calculates the total price of a basket of books based on discount rules.

## **Tools used for developing this application**

1. Java 21 & Spring Boot 4.0.5
2. Build tool: Apache Maven 3.9.14

## **How to build the application**

1. Clone this repository
   https://github.com/manohar-goud/2025-DEV1-XX-DevelopmentBooks
2. You can build the project and run the tests by running `mvn clean install` 

## **How to run the application**
1. By default, the application will start on port 8080. If you want the application to run on different port 8082, you can pass additional parameter --server.port=8082 while starting the application
2. Once successfully built, you can run the service by one of these commands:

   ```
   java -jar target/development-books-0.0.1-SNAPSHOT.jar

                       or
   
   java -jar target/development-books-0.0.1-SNAPSHOT.jar --server.port=8082
   ```


## 📌 API Documentation

### 🔹 1. Calculate Basket Price
- **Endpoint:** `/basket/calculatePrice`
- **Method:** `POST`
- **Description:** Returns Total Basket Price

#### ✅ Input Request:
```json
{
    "basket": [
        {
            "bookName": "CLEAN_CODE",
            "quantity": 2
        },
        {
            "bookName": "THE_CLEAN_CODER",
            "quantity": 2
        },
        {
            "bookName": "CLEAN_ARCHITECTURE",
            "quantity": 2
        },
        {
            "bookName": "TEST_DRIVEN_DEVELOPMENT_BY_EXAMPLE",
            "quantity": 1
        },
        {
            "bookName": "WORKING_EFFECTIVELY_WITH_LEGACY_CODE",
            "quantity": 1
        }
    ]
}
```
#### ✅ Success Response:
```json
 {
    "basketPrice": 320.0
 }