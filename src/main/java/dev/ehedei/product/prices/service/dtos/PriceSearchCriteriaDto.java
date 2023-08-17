package dev.ehedei.product.prices.service.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class PriceSearchCriteriaDto {

    @NotNull(message = "Date can not be null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;

    @NotNull(message = "Product Id can not be null")
    private Long productId;

    @NotNull(message = "Brand Id can not be null")
    private Long brandId;
}
