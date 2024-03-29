package dev.ehedei.product.prices.service.dtos;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) for representing product price information sent to client.
 */
@Data
public class ProductPriceDto {

    private Long productId;

    private Long brandId;

    private Long priceList;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Double price;
}
