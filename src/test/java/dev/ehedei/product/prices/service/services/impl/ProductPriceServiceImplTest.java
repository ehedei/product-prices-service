package dev.ehedei.product.prices.service.services.impl;

import dev.ehedei.product.prices.service.domain.BrandModel;
import dev.ehedei.product.prices.service.domain.ProductPriceModel;
import dev.ehedei.product.prices.service.dtos.PriceSearchCriteriaDto;
import dev.ehedei.product.prices.service.dtos.ProductPriceDto;
import dev.ehedei.product.prices.service.exceptions.InvalidPriceSearchCriteriaException;
import dev.ehedei.product.prices.service.repositories.ProductPriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class ProductPriceServiceImplTest {

    private static final Random random = new Random();

    @Mock
    private ProductPriceRepository productPriceRepository;

    @InjectMocks
    private ProductPriceServiceImpl productPriceService;

    private Long brandId;
    private Long productId;
    private Long priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double price;


    @BeforeEach
    public void setUp() {
        final ProductPriceModel productPrice = new ProductPriceModel();

        brandId = random.nextLong(100);
        final BrandModel brand = new BrandModel();
        brand.setId(brandId);
        productPrice.setBrand(brand);

        productId = random.nextLong(10000);
        productPrice.setProductId(productId);

        price = random.nextDouble(3.50, 1000.00);
        productPrice.setPrice(price);

        priceList = random.nextLong(100);
        productPrice.setPriceList(priceList);

        startDate = LocalDateTime.now();
        productPrice.setStartDate(startDate);

        endDate = LocalDateTime.now().plusDays(5);
        productPrice.setEndDate(endDate);

        MockitoAnnotations.openMocks(this);
        when(productPriceRepository
                .findFirstByProductIdAndBrand_idAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                        null, null, null,null)).thenReturn(Optional.empty());

        when(productPriceRepository
                .findFirstByProductIdAndBrand_idAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                        anyLong(), anyLong(), any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(Optional.of(productPrice));

    }

    @Test
    @DisplayName("getProductPriceBySearchCriteria must return empty optional if no price is found")
    void getProductPriceBySearchCriteria_ReturnsEmpty() {
        final PriceSearchCriteriaDto searchCriteriaDto = new PriceSearchCriteriaDto();
        final Optional<ProductPriceDto> result = productPriceService.getProductPriceBySearchCriteria(searchCriteriaDto);

        assertTrue(result.isEmpty(), "Optional of ProductPriceDto must be empty");
    }

    @Test
    @DisplayName("getProductPriceBySearchCriteria must return optional with PriceSearchCriteriaDto")
    void getProductPriceBySearchCriteria_ReturnsDto() {
        final PriceSearchCriteriaDto searchCriteriaDto = new PriceSearchCriteriaDto();
        searchCriteriaDto.setDate(startDate.plusDays(1));
        searchCriteriaDto.setProductId(productId);
        searchCriteriaDto.setBrandId(brandId);

        final Optional<ProductPriceDto> result = productPriceService.getProductPriceBySearchCriteria(searchCriteriaDto);

        assertTrue(result.isPresent(), "Optional of ProductPriceDto must be present");
        final ProductPriceDto productPriceDto = result.get();
        assertEquals(price, productPriceDto.getPrice(), "Prices must be equal");
        assertEquals(priceList, productPriceDto.getPriceList(), "Price Lists must be equal");
        assertEquals(brandId, productPriceDto.getBrandId(), "Brand ids must be equal");
        assertEquals(productId, productPriceDto.getProductId(), "Product ids must be equal");
        assertTrue(startDate.isEqual(productPriceDto.getStartDate()), "Start dates must be equal");
        assertTrue(endDate.isEqual(productPriceDto.getEndDate()), "End dates must be equal");
    }

    @Test
    @DisplayName("getProductPriceBySearchCriteria must throw InvalidPriceSearchCriteriaException is PriceSearchCriteria is null")
    void getProductPriceBySearchCriteria_ThrowsExceptionWhenSearchCriteriaIsNull() {
        assertThrows(InvalidPriceSearchCriteriaException.class,
                () -> productPriceService.getProductPriceBySearchCriteria(null),
                "InvalidPriceSearchCriteriaException must be thrown");
    }
}