# Stock Fetcher API
## Description

This is a Spring Boot application which is able to fetch
current prices for various publicly traded stocks.

It exposes a RESTful API with two endpoints which can be
consumed to get the stock price information either for a single
stock or a list of stocks, respectively.

## Requirements
You will need an API key from IEX for their IEX Cloud API.


## Invocation
1. Pull down the application using your favorite git client or CLI
2. Replace the ```TOKEN``` field in the PriceFetchService.java class with your IEX Cloud API key.
3. Run ```./gradlew bootRun```
4. Call ```GET /stock?ticker=<symbol>``` or ```GET /stocks?tickers=<comma separated list of symbols>```

## Example
Request: 
``` GET /stock?ticker=DAL ```

Response:

```
HTTP 200 OK

{
    "name": "DAL",
    "price": "30.82"
}
```

Request: 
``` GET /stocks?ticker=DAL, GOOGL, TSLA ```

Response:

```
HTTP 200 OK

[
    {
        "name": "DAL",
        "price": "30.82"
    },
    {
        "name": "GOOGL",
        "price": "1628.52"
    },
    {
        "name": "TSLA",
        "price": "2238.75"
    }
]
```


## LICENSE
This entire project is licensed under the MIT License.

See [LICENCE.md](LICENSE)