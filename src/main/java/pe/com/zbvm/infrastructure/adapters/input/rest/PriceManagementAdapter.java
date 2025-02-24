package pe.com.zbvm.infrastructure.adapters.input.rest;

import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pe.com.zbvm.application.usecases.BrandManagementUseCase;
import pe.com.zbvm.application.usecases.PriceManagementUseCase;
import pe.com.zbvm.domain.entity.Brand;
import pe.com.zbvm.domain.entity.Price;
import pe.com.zbvm.domain.entity.Product;
import pe.com.zbvm.domain.vo.Currency;
import pe.com.zbvm.domain.vo.EndDate;
import pe.com.zbvm.domain.vo.Id;
import pe.com.zbvm.domain.vo.Priority;
import pe.com.zbvm.domain.vo.StartDate;
import pe.com.zbvm.infrastructure.adapters.input.rest.request.PriceCriteriaRequest;
import pe.com.zbvm.infrastructure.adapters.input.rest.server.PricesApi;
import pe.com.zbvm.infrastructure.adapters.input.rest.server.model.PriceDto;
import pe.com.zbvm.infrastructure.helpers.mappers.PriceMapper;

/**
 * <br/> PriceManagementAdapter <br/>
 * <b>Class</b>: PriceManagementAdapter<br/>
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
 * <li>Oct 05, 2024 Creaci&oacute;n de Clase.</li>
 * </ul>
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class PriceManagementAdapter implements PricesApi {

  private final PriceManagementUseCase priceManagementUseCase;
  private final BrandManagementUseCase brandManagementUseCase;
  private final PriceMapper mapper;

  @Override
  public ResponseEntity<List<PriceDto>> getAll(OffsetDateTime consultationDate,
                                               Long productId,
                                               Long brandId) {
    PriceCriteriaRequest criteria = PriceCriteriaRequest.builder()
        .brandId(brandId)
        .productId(productId)
        .consultationDate(consultationDate)
        .build();

    return Optional.of(
            priceManagementUseCase.findAllByCriteria(mapper.priceCriteriaToDomain(criteria)))
        .map(prices -> prices.stream()
            .map(mapper::priceDomainToDto)
            .toList())
        .filter(listPrice -> !listPrice.isEmpty())
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.noContent().build());
  }

  @Override
  @Transactional
  public ResponseEntity<PriceDto> create(PriceDto priceDto) {
    Brand brand = brandManagementUseCase.getBrandById(Id.withId(priceDto.getBrandId()));
    Product product = Product.builder().id(Id.withId(35455L)).name("ZAPATILLA SKATE CUERDAS").build();
    StartDate startDate = StartDate.withStartDate(priceDto.getStartDate().toLocalDateTime());
    EndDate endDate = EndDate.withEndDate(priceDto.getEndDate().toLocalDateTime());
    Currency currency = Currency.withCurrency(priceDto.getCurrency());
    Priority priority = Priority.withPriority(Byte.valueOf("1"));

    Price price = priceManagementUseCase.createPrice(brand,
        startDate,
        endDate,
        product,
        BigDecimal.valueOf(priceDto.getPrice()),
        currency,
        priority
    );

    return Optional.of(priceManagementUseCase.persistPrice(price))
        .map(mapper::priceDomainToDto)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.noContent().build());
  }
}
