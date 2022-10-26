package com.example.advancedintegrationtest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CatService {
    @Value("${cat.url}")
    private String url;

    public Cat getFact() {
        return new RestTemplate().getForObject(url, Cat.class);
    }
}
