package pe.com.zbvm.domain.entity;

import lombok.Builder;
import pe.com.zbvm.domain.vo.Id;

/**
 * <br/> Product <br/>
 * <b>Class</b>: Product<br/>
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
@Builder
public record Product(Id id, String name) {
}
