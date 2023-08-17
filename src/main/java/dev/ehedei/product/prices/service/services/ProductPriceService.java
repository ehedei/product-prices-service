package dev.ehedei.product.prices.service.services;

import dev.ehedei.product.prices.service.dtos.PriceSearchCriteriaDto;
import dev.ehedei.product.prices.service.dtos.ProductPriceDto;

import java.util.Optional;

public interface ProductPriceService {
    Optional<ProductPriceDto> getProductPriceBySearchCriteria(PriceSearchCriteriaDto productPriceCriteria);

}
