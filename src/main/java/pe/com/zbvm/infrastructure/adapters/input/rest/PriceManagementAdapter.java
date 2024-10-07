package pe.com.zbvm.infrastructure.adapters.input.rest;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pe.com.zbvm.application.usecases.PriceManagementUseCase;
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
}
