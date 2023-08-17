package dev.ehedei.product.prices.service.repositories;

import dev.ehedei.product.prices.service.domain.ProductPriceModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPriceRepository extends JpaRepository<ProductPriceModel, Long> {
}
