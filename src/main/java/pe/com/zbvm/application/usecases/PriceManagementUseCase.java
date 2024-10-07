package pe.com.zbvm.application.usecases;

import java.util.List;
import pe.com.zbvm.domain.entity.Price;
import pe.com.zbvm.domain.vo.PriceCriteria;

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
}
