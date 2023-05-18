package com.example.Bank.fakeapistore;

import com.sun.net.httpserver.HttpHandler;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final RestTemplate restTemplate;

    @Value("${fake-api.store.url}")
    private String apiUrl;

    public List<Product> findAllProducts() {
        var products = restTemplate.getForObject(apiUrl, Product[].class);
        return Arrays.asList(products);
    }

    public List<Product> findAllProductsWithExchange() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "Application/Json");
        HttpEntity entity = new HttpEntity<>(headers);
        var products = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, Product[].class);
        return Arrays.asList(products.getBody());
    }
}
