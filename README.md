
<h1 align="center"> Exchange and Currency Service </h1> <br>

<p align="center">
  Exchange and Currency microservices description.
</p>


## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Requirements](#requirements)
- [Quick Start](#quick-start)
- [Testing](#testing)
- [API](#requirements)
- [Acknowledgements](#acknowledgements)




## Introduction

[![Heroku](https://heroku-badge.herokuapp.com/?app=heroku-badge)]

This microservices are created to achieve retrieving current rates from service provider and calculate the amount of currency, also saving to values memory database.


## Features

* Include a list of
* Netflix Zuul API GateWay 
* Currency Microservice
* Exchange Microservice
* Cache Mechanism (implemented to improve performance on each service call)

### Local
* [Java 8 SDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Maven](https://maven.apache.org/download.cgi)
* Spring Boot
* H2 Memory Database
* Netflix Zuul API GATEWAY
* Mockito for REST API Testing
* Google Guava Cache


## Quick Start
The microservices are deployed to heroku cloud servers based on amazon web services.

                                                        API GATEWAY
                                                    ==================
                                      Currency Microservice <--> Exchange Microservice
                                      
- The exchange rate is taken from currencylayer service provider.
- The each currency service call is stored in H2 memory database.
- Cached rate is using with limited timeout while calculating amount of currency convertion.
- Please check the API documentation on swagger for detailed informations.
  (https://app.swaggerhub.com/apis-docs/ayciceksamet/ExchangeRateAPI/1.0.1)


### Run and Test Project on Heroku

Please refresh again if you have any error, so heroku dyno could be sleeped. To activate refresh request.

Please be sure that the services are idle :

http://api-gateway34.herokuapp.com/api/ccservice/conversion/ping

http://api-gateway34.herokuapp.com/api/exservice/exchange/ping

http://currencyservice34.herokuapp.com/conversion/ping

https://exchangeservice34.herokuapp.com/exchange/ping

```bash
GET https://api-gateway34.herokuapp.com/api/exservice/exchangerate/USDTRY
```
{
    "error": null,
    "rateValue": 109.650636
}

```bash
POST https://api-gateway34.herokuapp.com/api/ccservice/conversion/10/USD/TRY
```
{
    "transactionID": "3a085772-ff41-4682-ac4c-a95fcb23c05c",
    "error": null,
    "amountValue": 59.102050
}

```bash
POST https://api-gateway34.herokuapp.com/api/ccservice/conversion/getbyid/3a085772-ff41-4682-ac4c-a95fcb23c05c

[
    {
        "id": -4687967664085091257,
        "date": 1576709389000,
        "transactionID": "3a085772-ff41-4682-ac4c-a95fcb23c05c",
        "amountValue": null
    },
    {
        "id": -3707712850300667250,
        "date": 1576709406000,
        "transactionID": "3a085772-ff41-4682-ac4c-a95fcb23c05c",
        "amountValue": null
    },
    {
        "id": -3398004407288260754,
        "date": 1576709390000,
        "transactionID": "3a085772-ff41-4682-ac4c-a95fcb23c05c",
        "amountValue": null
    },
    {
        "id": -2902293203733159129,
        "date": 1576709390000,
        "transactionID": "3a085772-ff41-4682-ac4c-a95fcb23c05c",
        "amountValue": null
    },
    {
        "id": -2073374941150600414,
        "date": 1576709399000,
        "transactionID": "3a085772-ff41-4682-ac4c-a95fcb23c05c",
        "amountValue": null
    },
    {
        "id": 419561526615336176,
        "date": 1576709389000,
        "transactionID": "3a085772-ff41-4682-ac4c-a95fcb23c05c",
        "amountValue": null
    },
    {
        "id": 4579450716977525960,
        "date": 1576709288000,
        "transactionID": "3a085772-ff41-4682-ac4c-a95fcb23c05c",
        "amountValue": null
    }
]
```


## Testing
REST API calls and status are tested with Mockito Framework.


## API
https://app.swaggerhub.com/apis-docs/ayciceksamet/ExchangeRateAPI/1.0.1

