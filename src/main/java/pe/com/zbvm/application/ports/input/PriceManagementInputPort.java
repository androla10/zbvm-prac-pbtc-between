package pe.com.zbvm.application.ports.input;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.zbvm.application.ports.output.PriceManagementOutputPort;
import pe.com.zbvm.application.usecases.PriceManagementUseCase;
import pe.com.zbvm.domain.entity.Price;
import pe.com.zbvm.domain.vo.PriceCriteria;

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
}
