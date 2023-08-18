package dev.ehedei.product.prices.service.controllers;


import dev.ehedei.product.prices.service.dtos.PriceSearchCriteriaDto;
import dev.ehedei.product.prices.service.dtos.ProductPriceDto;
import dev.ehedei.product.prices.service.services.ProductPriceService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/product-prices")
@Validated
@Getter @Setter @AllArgsConstructor
public class ProductPricesController {

    private ProductPriceService productPriceService;

    @PostMapping("/search")
    public ResponseEntity<ProductPriceDto> getProductPrices(@RequestBody @Valid final PriceSearchCriteriaDto priceSearchCriteriaDto) {
        final Optional<ProductPriceDto> productPriceDto = getProductPriceService().getProductPriceBySearchCriteria(priceSearchCriteriaDto);
        return productPriceDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
