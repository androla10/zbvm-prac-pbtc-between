package pe.com.zbvm.application.ports.input;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.zbvm.application.ports.output.PriceManagementOutputPort;
import pe.com.zbvm.application.usecases.PriceManagementUseCase;
import pe.com.zbvm.domain.entity.Brand;
import pe.com.zbvm.domain.entity.Price;
import pe.com.zbvm.domain.entity.Product;
import pe.com.zbvm.domain.vo.Currency;
import pe.com.zbvm.domain.vo.EndDate;
import pe.com.zbvm.domain.vo.Id;
import pe.com.zbvm.domain.vo.PriceCriteria;
import pe.com.zbvm.domain.vo.Priority;
import pe.com.zbvm.domain.vo.StartDate;

/**
 * <br/> PriceManagementInputPort <br/>
 * <b>Class</b>: PriceManagementInputPort<br/>
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
@Service
@RequiredArgsConstructor
public class PriceManagementInputPort implements PriceManagementUseCase {

  private final PriceManagementOutputPort priceManagementOutputPort;

  @Override
  public List<Price> findAllByCriteria(PriceCriteria priceCriteria) {
    return priceManagementOutputPort.findFirstByCriteriaOrderByDescPriority(priceCriteria);
  }

  @Override
  public Price createPrice(Brand brand,
                           StartDate startDate,
                           EndDate endDate,
                           Product product,
                           BigDecimal price,
                           Currency currency,
                           Priority priority) {
    return Price.builder()
        .id(Id.withIdNull())
        .startDate(startDate)
        .endDate(endDate)
        .brand(brand)
        .product(product)
        .price(price)
        .currency(currency)
        .priority(priority)
        .build();
  }

  @Override
  public Price persistPrice(Price price) {
    return Optional.of(priceManagementOutputPort.persist(price))
        .orElseThrow(() -> new RuntimeException("Ha ocurrido un error al insertar en base de datos"));
  }
}
