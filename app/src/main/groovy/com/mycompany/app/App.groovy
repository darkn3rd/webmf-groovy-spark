package com.mycompany.app
import static spark.Spark.*

get('/', { req, res -> "Hello World!\n" })
get('/hello', { req, res -> "Hello World!\n" })
get('/hello/', { req, res -> "Hello World!\n" })
get("/hello/:name", { req, res -> "Why, Hello ${req.params(":name")}!\n" })