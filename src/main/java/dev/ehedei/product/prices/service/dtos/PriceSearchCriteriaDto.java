package dev.ehedei.product.prices.service.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PriceSearchCriteriaDto {

    @NotNull(message = "Date can not be null")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private LocalDateTime date;

    @NotNull(message = "Product Id can not be null")
    private Long productId;

    @NotNull(message = "Brand Id can not be null")
    private Long brandId;
}
