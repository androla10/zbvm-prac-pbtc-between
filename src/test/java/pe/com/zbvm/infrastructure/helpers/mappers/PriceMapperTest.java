package pe.com.zbvm.infrastructure.helpers.mappers;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.com.zbvm.domain.entity.Brand;
import pe.com.zbvm.domain.entity.Price;
import pe.com.zbvm.domain.entity.Product;
import pe.com.zbvm.domain.vo.Currency;
import pe.com.zbvm.domain.vo.EndDate;
import pe.com.zbvm.domain.vo.Id;
import pe.com.zbvm.domain.vo.StartDate;
import pe.com.zbvm.infrastructure.adapters.output.mysql.data.BrandData;
import pe.com.zbvm.infrastructure.adapters.output.mysql.data.PriceData;
import pe.com.zbvm.infrastructure.adapters.output.mysql.data.ProductData;

/**
 * <br/> PriceMapperTest <br/>
 * <b>Class</b>: PriceMapperTest<br/>
 * Copyright: &copy; 2024 Inditex.<br/>
 * Company: Inditex.<br/>
 *
 * @author Inditex <br/>
 * <u>Developed by</u>: <br/>
 * <ul>
 * <li>Zeler Benji Villarreal Marcelo</li>
 * </ul>
 * <u>Changes</u>:<br/>
 * <ul>
 * <li>Oct 06, 2024 Creaci&oacute;n de Clase.</li>
 * </ul>
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
class PriceMapperTest {

  @InjectMocks
  private PriceMapper mapper;

  @Test
  @DisplayName("Return A PriceDto When Send Price Of Type Domain")
  void returnPriceDtoWhenSendPriceDomain() {
    Price price = Price.builder()
        .id(Id.withIdentifier(1))
        .brand(Brand.builder().id(Id.withIdentifier(1)).build())
        .product(Product.builder().id(Id.withIdentifier(1)).build())
        .startDate(StartDate.withValue(LocalDateTime.now()))
        .price(new BigDecimal("10.3"))
        .currency(Currency.withCode("EUR"))
        .endDate(EndDate.withValue(LocalDateTime.now()))
        .build();

    var actual = mapper.priceDomainToDto(price);

    assertAll(
        () -> assertEquals(1, actual.getBrandId()),
        () -> assertEquals(1, actual.getProductId()),
        () -> assertNotNull(actual.getStartDate()),
        () -> assertNotNull(actual.getEndDate()),
        () -> assertEquals(Double.valueOf("10.3"), actual.getPrice()),
        () -> assertEquals("EUR", actual.getCurrency())
    );
  }

  @Test
  @DisplayName("Return PriceDomain When Send PriceData")
  void returnPriceDomainWhenSendPriceData() {
    PriceData priceData = PriceData.builder()
        .id(1L)
        .priceList(1)
        .priority((byte) 1)
        .brand(BrandData.builder().id(1L).build())
        .product(ProductData.builder().id(1L).build())
        .startDate(LocalDateTime.now())
        .endDate(LocalDateTime.now())
        .price(new BigDecimal("10.3"))
        .currency("EUR")
        .build();

    var actual = mapper.priceDataToDomain(priceData);

    assertAll(
        () -> assertEquals(1, actual.id().getIdentifier()),
        () -> assertEquals(Double.valueOf("10.3"), actual.price().doubleValue()),
        () -> assertEquals(Byte.valueOf("1"), actual.priority().getValue()),
        () -> assertNotNull(actual.brand().id().getIdentifier()),
        () -> assertNotNull(actual.product().id().getIdentifier()),
        () -> assertNotNull(actual.startDate().getValue()),
        () -> assertNotNull(actual.endDate().getValue()),
        () -> assertEquals("EUR", actual.currency().getCode()),
        () -> assertNotNull(actual.brand().id().getIdentifier()),
        () -> assertNotNull(actual.product().id().getIdentifier()),
        () -> assertNotNull(actual.endDate().getValue()),
        () -> assertNotNull(actual.endDate().getValue())
    );
  }
}