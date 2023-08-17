package dev.ehedei.product.prices.service.services.impl;

import dev.ehedei.product.prices.service.dtos.PriceSearchCriteriaDto;
import dev.ehedei.product.prices.service.dtos.ProductPriceDto;
import dev.ehedei.product.prices.service.repositories.ProductPriceRepository;
import dev.ehedei.product.prices.service.services.ProductPriceService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Getter @Setter @AllArgsConstructor
public class ProductPriceServiceImpl implements ProductPriceService {

    private ProductPriceRepository productPriceRepository;

    @Override
    public Optional<ProductPriceDto> getProductPriceBySearchCriteria(final PriceSearchCriteriaDto productPriceCriteria) {
        return Optional.empty();
    }
}
