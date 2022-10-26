package com.example.advancedintegrationtest;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.jsonResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@WireMockTest(httpPort = 8080)
@TestPropertySource(properties = "cat.url=http://localhost:8080")
public class CatServiceTest {
    @Autowired
    private CatService catService;

    @Test
    void testCatService(WireMockRuntimeInfo runtimeInfo) {
        Cat cat=new Cat();
        cat.setFact("cat");
        cat.setLength(4);
        stubFor(get("/").willReturn(jsonResponse(cat, HttpStatus.OK.value())));
        Cat cat1 = catService.getFact();
        assertEquals(cat,cat1);
    }
}
