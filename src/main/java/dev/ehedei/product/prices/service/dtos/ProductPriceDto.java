package dev.ehedei.product.prices.service.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class ProductPriceDto {
    private Long id;

    private Long productId;

    private Long brandId;

    private Date startDate;

    private Date endDate;

    private Double price;
}
