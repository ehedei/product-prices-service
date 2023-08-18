package dev.ehedei.product.prices.service.services.impl;

import dev.ehedei.product.prices.service.domain.ProductPriceModel;
import dev.ehedei.product.prices.service.dtos.PriceSearchCriteriaDto;
import dev.ehedei.product.prices.service.dtos.ProductPriceDto;
import dev.ehedei.product.prices.service.repositories.ProductPriceRepository;
import dev.ehedei.product.prices.service.services.ProductPriceService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@Getter
@Setter
@AllArgsConstructor
public class ProductPriceServiceImpl implements ProductPriceService {

    private ProductPriceRepository productPriceRepository;

    @Override
    public Optional<ProductPriceDto> getProductPriceBySearchCriteria(final PriceSearchCriteriaDto productPriceCriteria) {

        final Optional<ProductPriceModel> productPriceModel = getProductPriceRepository()
                .findFirstByProductIdAndBrand_idAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                        productPriceCriteria.getProductId(),
                        productPriceCriteria.getBrandId(),
                        productPriceCriteria.getDate(),
                        productPriceCriteria.getDate()
                );

        return productPriceModel.map(this::convertProductPriceModelIntoDto);
    }

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
