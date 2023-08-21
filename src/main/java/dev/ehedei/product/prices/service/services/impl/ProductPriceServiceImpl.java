package dev.ehedei.product.prices.service.services.impl;

import dev.ehedei.product.prices.service.domain.ProductPriceModel;
import dev.ehedei.product.prices.service.dtos.PriceSearchCriteriaDto;
import dev.ehedei.product.prices.service.dtos.ProductPriceDto;
import dev.ehedei.product.prices.service.exceptions.InvalidPriceSearchCriteriaException;
import dev.ehedei.product.prices.service.repositories.ProductPriceRepository;
import dev.ehedei.product.prices.service.services.ProductPriceService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * Implementation of the ProductPriceService interface.
 */
@Service
@Slf4j
@Getter
@Setter
@AllArgsConstructor
public class ProductPriceServiceImpl implements ProductPriceService {

    private ProductPriceRepository productPriceRepository;

    /**
     * Retrieve a product price based on search criteria.
     *
     * @param productPriceCriteria The criteria to search for a product price.
     * @return An optional containing the product price information if found, otherwise empty.
     * @throws InvalidPriceSearchCriteriaException when productPriceCriteria is null.
     */
    @Override
    public Optional<ProductPriceDto> getProductPriceBySearchCriteria(final PriceSearchCriteriaDto productPriceCriteria) {

        if (Objects.isNull(productPriceCriteria)) {
            final String message = "PriceSearchCriteriaDto must not be null";
            log.error(message);
            throw new InvalidPriceSearchCriteriaException(message);
        }

        final Optional<ProductPriceModel> productPriceModel = getProductPriceRepository()
                .findFirstByProductIdAndBrand_idAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                        productPriceCriteria.getProductId(),
                        productPriceCriteria.getBrandId(),
                        productPriceCriteria.getDate(),
                        productPriceCriteria.getDate()
                );

        return productPriceModel.map(this::convertProductPriceModelIntoDto);
    }

    /**
     * Convert a ProductPriceModel into a ProductPriceDto.
     *
     * @param productPriceModel The ProductPriceModel to be converted.
     * @return The corresponding ProductPriceDto.
     */
    protected ProductPriceDto convertProductPriceModelIntoDto(final ProductPriceModel productPriceModel) {
        final ProductPriceDto productPriceDto = new ProductPriceDto();
        productPriceDto.setProductId(productPriceModel.getProductId());
        productPriceDto.setPrice(productPriceModel.getPrice());
        productPriceDto.setPriceList(productPriceModel.getPriceList());
        productPriceDto.setStartDate(productPriceModel.getStartDate());
        productPriceDto.setEndDate(productPriceModel.getEndDate());

        if (Objects.nonNull(productPriceModel.getBrand())) {
            productPriceDto.setBrandId(productPriceModel.getBrand().getId());
        }

        return productPriceDto;
    }
}
