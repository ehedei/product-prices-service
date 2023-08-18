package dev.ehedei.product.prices.service.services;

import dev.ehedei.product.prices.service.dtos.PriceSearchCriteriaDto;
import dev.ehedei.product.prices.service.dtos.ProductPriceDto;

import java.util.Optional;

/**
 * Service interface for managing product prices.
 */
public interface ProductPriceService {

    /**
     * Retrieve a product price based on search criteria.
     *
     * @param productPriceCriteria The criteria to search for a product price.
     * @return An optional containing the product price information if found, otherwise empty.
     */
    Optional<ProductPriceDto> getProductPriceBySearchCriteria(PriceSearchCriteriaDto productPriceCriteria);

}
