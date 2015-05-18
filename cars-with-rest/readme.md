# Spring Data Rest with Cars

To create a new car POST with Content-Type application/json to `http://localhost:8080/api/cars` with the following content:

```
{ "make": "Volvo", "model": "C30", "doors" : [{ "location" : "front-left"}, { "location" : "front-right"} ] }
```

To query the first five cars GET `http://localhost:8080/api/cars/?size=5`.