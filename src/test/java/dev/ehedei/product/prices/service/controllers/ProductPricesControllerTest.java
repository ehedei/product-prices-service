package dev.ehedei.product.prices.service.controllers;

import dev.ehedei.product.prices.service.dtos.ProductPriceDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("dev")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductPricesControllerTest {

    private static final Long PRODUCT_ID = 35455L;
    private static final Long BRAND_ID = 1L;

    private static final String END_POINT = "/api/v1/product-prices/search";

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    private TestRestTemplate testRestTemplate;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @ParameterizedTest(name = "Test {index}: petición a las {0} del producto 35455 para la brand 1 (ZARA)")
    @MethodSource("getArguments")
    void getProductPrices_ValidCriteria_ReturnsProductPrice(final String date, final double price, final long priceList) {
        final ResponseEntity<ProductPriceDto> responseEntity = sendRequest(date);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        final ProductPriceDto productPriceDto = responseEntity.getBody();

        assertNotNull(productPriceDto, "Response is null");
        assertEquals(price, productPriceDto.getPrice(), "Prices are not equal");
        assertEquals(priceList, productPriceDto.getPriceList(), "Price lists are not equal");
    }

    @Test
    void testGetProductPrices_ReturnsNotFound() {
        final ResponseEntity<ProductPriceDto> responseEntity = sendRequest("1900-01-01T10:00:00.000+00:00");
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode(), "Http Status code expected (404) is not correct");
    }

    @Test
    void testGetProductPrices_InvalidCriteria_ReturnsBadRequest() {
        final ResponseEntity<ProductPriceDto> responseEntity = sendRequest("Bad Request");
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode(), "Http Status code expected (400) is not correct");
    }

    private static Stream<Arguments> getArguments() {
        return Stream.of(
                Arguments.of("2020-06-14T10:00:00.000+00:00", 35.50, 1L),
                Arguments.of("2020-06-14T16:00:00.000+00:00", 25.45, 2L),
                Arguments.of("2020-06-14T21:00:00.000+00:00", 35.50, 1L),
                Arguments.of("2020-06-15T10:00:00.000+00:00", 30.50, 3L),
                Arguments.of("2020-06-16T21:00:00.000+00:00", 38.95, 4L)
        );
    }

    private ResponseEntity<ProductPriceDto> sendRequest(final String date) {
        final Map<String, Object> payload = buildPayload(date);

        return testRestTemplate.postForEntity(
                        END_POINT,
                        payload,
                        ProductPriceDto.class);
    }

    private Map<String, Object> buildPayload(final String date) {
        final Map<String, Object> payload = new HashMap<>();
        payload.put("productId", PRODUCT_ID);
        payload.put("brandId", BRAND_ID);
        payload.put("date", date);

        return payload;
    }
}