# Bus ticketing system application created with Spring Boot as example of Service Oriented Architecture (API Gateway, Services Keycloak)
![v3](https://github.com/HristijanSlavkoski/Bus-Ticketing-System/assets/43710508/0a2070f3-c513-4924-b624-940e33183cdb)

# About the project
<ul style="list-style-type:disc">
  <li>User can register and login through Keycloak</li>
  <li>User can get routes from route-service through API Gateway</li>
  <li>User can book a ticket from routes in the booking-service through API Gateway</li>
  <li>User can confirm or cancel a ticket payment from payment-service through API Gateway</li>
</ul>

<b>Docker</b>

<b>1 )</b> Install <b>Docker Desktop</b>. Here is the installation <b>link</b> : https://docs.docker.com/docker-for-windows/install/

<b>2 )</b> Open <b>Terminal</b> under <b>resources</b> folder to run <b>Keycloak</b> on <b>Docker</b> Container
```
    docker-compose up -d
```
<b>3 )</b> Implement Keycloak Settings
```
    1 ) Open Keycloak on the Browser through localhost:8181
    2 ) Enter username and password (admin : admin)
    3 ) Create Client named for bus-ticketing-system-realm and define it in Keycloak config of user service
    4 ) Change client's access type from public to confidential
    5 ) Get secret key to define clientSecret in Keycloak config of user service
```

<b>Maven></b>

<b>1 )</b> Start Keycloak and Rabbit through Docker

<b>2 )</b> Implement their settings

<b>3 )</b> Download your project from this link `https://github.com/Rapter1990/SpringBootMicroservices`

<b>4 )</b> Go to the project's home directory :  `cd SpringBootMicroservices`

<b>5 )</b> Create a jar file though this command `mvn clean install`

<b>6 )</b> Run the project though this command `mvn spring-boot:run`

### To execute the API's through the gateway (Each of the endpoints support all CRUD operations)
    1) http://localhost:8080/api/user/signup
    2) http://localhost:8080/api/user/login
    3) http://localhost:8080/api/user/{id}
    4) http://localhost:8080/api/route 
    5) http://localhost:8080/api/route/{id}
    6) http://localhost:8080/api/route/start/{startPoint}
    7) http://localhost:8080/api/company
    8) http://localhost:8080/api/company/{id}
    9) http://localhost:8080/api/company-route
    10) http://localhost:8080/api/company-route/{id} 
    11) http://localhost:8080/api/company-route/route/{routeId}
    12) http://localhost:8080/api/company-route/company/{companyId}
    13) http://localhost:8080/api/company-route/cheapest/{routeId}
    14) http://localhost:8080/api/payment 
    15) http://localhost:8080/api/payment/{id}
    16) http://localhost:8080/api/payment/booking/{bookingId}
    15) http://localhost:8080/api/payment/booking/{bookingId}/ispaid
    16) http://localhost:8080/api/ticket
    17) http://localhost:8080/api/ticket/{id}
    18) http://localhost:8080/api/ticket/validity/{validity}
    19) http://localhost:8080/api/ticket/type/{type}
    20) http://localhost:8080/api/ticket-type
    21) http://localhost:8080/api/ticket-type/{id}
    22) http://localhost:8080/api/ticket-type/type/{type}

### Used Dependencies
* Core
  * Spring
    * Spring Boot
    * Spring Security
    * Spring Web
      * RestTemplate
    * Spring Data
      * Spring Data JPA
    * Spring Cloud
      * Spring Cloud Gateway Server
      * Spring Cloud Config Server
      * Spring Cloud Config Client
* Database
  * PostgresSQL
* Message Broker
  * Kafka
* Security
  * Keycloak Server
  * Keycloak OAuth2
  * Keycloak REST API
* Tracing system
  * Zipkin

## Valid Request Body

##### Sign Up
```
    http://localhost:8600/api/user/signup

   {
    "username": "newuser",
    "email": "newuser@example.com",
    "firstName": "New",
    "lastName": "User",
    "enabled": true,
    "credentials": [
        {
            "type": "password",
            "value": "newpassword",
            "temporary": false
        }
      ]
    }

```

##### Login
```
    http://localhost:8080/api/user/login
    Bearer Token : Access Token of User from Keycloak
    {
        "username" : "newuser",
        "password" : "newpassword"
    }

```

## Valid Request Params

##### Get routes By Id
```
    http://localhost:8080/api/route/{id}
    Bearer Token : Access Token of Admin from Keycloak
```

##### Confirm booking By Id
```
    http://localhost:8080/api/booking/{id}/confirm"
    Bearer Token : Access Token of Admin from Keycloak
```

## Valid Request Params and Body

##### Create Booking 
```
    http://localhost:8080/api/booking
    Bearer Token : Access Token from Keycloak
    {
        "userId": 1,
        "companyRouteId": 2,
        "seatNumber": 1,
        "bookingDate": "2023-06-06",
        "status": "PENDING"
    }
```
## No Request or Params

##### Get routes
```
    http://localhost:8080/api/route 
    Bearer Token : Access Token of Admin from Keycloak
```

### Screenshots
![user-service](https://github.com/HristijanSlavkoski/Bus-Ticketing-System/assets/43710508/8e95cdd2-7d33-4e83-a871-29e9cffe88a5)
![Screenshot_1](https://github.com/HristijanSlavkoski/Bus-Ticketing-System/assets/43710508/929eba4b-cd0a-4204-bbda-c972eb009bb7)
![355442532_616612153776315_2959572983001742141_n](https://github.com/HristijanSlavkoski/Bus-Ticketing-System/assets/43710508/71048857-13e1-475f-b1ff-c4170efd5b82)
![355625200_931995691222370_7640875963683870357_n](https://github.com/HristijanSlavkoski/Bus-Ticketing-System/assets/43710508/716cd94d-13d3-4956-8fd7-9f97184d1aff)
