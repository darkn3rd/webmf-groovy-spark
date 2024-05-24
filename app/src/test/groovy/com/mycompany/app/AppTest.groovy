package com.mycompany.app

import spock.lang.Specification
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import spark.Spark
import static spark.Spark.*

class AppTest extends Specification {

    def setupSpec() {
        Spark.port(4567) // Set the port for Spark server
        get('/', { req, res -> "Hello World!\n" })
        get('/hello', { req, res -> "Hello World!\n" })
        get('/hello/', { req, res -> "Hello World!\n" })
        get("/hello/:name", { req, res -> "Why, Hello ${req.params(":name")}!\n" })
        Spark.awaitInitialization() // Wait for server to initialize
    }

    def cleanupSpec() {
        Spark.stop()
    }

    def "test root route"() {
        given:
        CloseableHttpClient httpClient = HttpClients.createDefault()
        HttpGet request = new HttpGet("http://localhost:4567/")

        when:
        CloseableHttpResponse response = httpClient.execute(request)
        String responseBody = response.getEntity().getContent().getText()

        then:
        response.statusLine.statusCode == 200
        responseBody == "Hello World!\n"

        cleanup:
        response.close()
        httpClient.close()
    }

    def "test /hello route"() {
        given:
        CloseableHttpClient httpClient = HttpClients.createDefault()
        HttpGet request = new HttpGet("http://localhost:4567/hello")

        when:
        CloseableHttpResponse response = httpClient.execute(request)
        String responseBody = response.getEntity().getContent().getText()

        then:
        response.statusLine.statusCode == 200
        responseBody == "Hello World!\n"

        cleanup:
        response.close()
        httpClient.close()
    }

    def "test /hello/ route"() {
        given:
        CloseableHttpClient httpClient = HttpClients.createDefault()
        HttpGet request = new HttpGet("http://localhost:4567/hello/")

        when:
        CloseableHttpResponse response = httpClient.execute(request)
        String responseBody = response.getEntity().getContent().getText()

        then:
        response.statusLine.statusCode == 200
        responseBody == "Hello World!\n"

        cleanup:
        response.close()
        httpClient.close()
    }

    def "test /hello/:name route"() {
        given:
        CloseableHttpClient httpClient = HttpClients.createDefault()
        HttpGet request = new HttpGet("http://localhost:4567/hello/John")

        when:
        CloseableHttpResponse response = httpClient.execute(request)
        String responseBody = response.getEntity().getContent().getText()

        then:
        response.statusLine.statusCode == 200
        responseBody == "Why, Hello John!\n"

        cleanup:
        response.close()
        httpClient.close()
    }
}
