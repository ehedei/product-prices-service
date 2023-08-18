package dev.ehedei.product.prices.service.repositories;

import dev.ehedei.product.prices.service.domain.ProductPriceModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ProductPriceRepository extends JpaRepository<ProductPriceModel, Long> {
    Optional<ProductPriceModel> findFirstByProductIdAndBrand_idAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
            Long productId, Long brand, LocalDateTime startDate, LocalDateTime endDate);

}
