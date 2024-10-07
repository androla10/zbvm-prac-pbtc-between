package pe.com.zbvm.domain.vo;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * <br/> PriceCriteria<br/>
 * <b>Class</b>: PriceCriteria<br/>
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
@Getter
@Builder
@EqualsAndHashCode
@ToString
public class PriceCriteria {
  private Id productId;
  private Id brandId;
  private ConsultationDate consultationDate;
}
