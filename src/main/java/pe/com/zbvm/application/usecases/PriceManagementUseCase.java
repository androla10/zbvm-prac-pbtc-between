package pe.com.zbvm.application.usecases;

import java.math.BigDecimal;
import java.util.List;
import pe.com.zbvm.domain.entity.Brand;
import pe.com.zbvm.domain.entity.Price;
import pe.com.zbvm.domain.entity.Product;
import pe.com.zbvm.domain.vo.Currency;
import pe.com.zbvm.domain.vo.EndDate;
import pe.com.zbvm.domain.vo.PriceCriteria;
import pe.com.zbvm.domain.vo.Priority;
import pe.com.zbvm.domain.vo.StartDate;

/**
 * <br/> PriceManagementUseCase <br/>
 * <b>Class</b>: PriceManagementUseCase<br/>
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
public interface PriceManagementUseCase {
  List<Price> findAllByCriteria(PriceCriteria priceCriteria);
  Price createPrice(Brand brand,
                    StartDate startDate,
                    EndDate endDate,
                    Product product,
                    BigDecimal price,
                    Currency currency,
                    Priority priority);
  Price persistPrice(Price price);
}
