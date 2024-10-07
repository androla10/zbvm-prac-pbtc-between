package pe.com.zbvm.infrastructure.adapters.output.mysql.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <br/> ProductData <br/>
 * <b>Class</b>: ProductData<br/>
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
@Table(name = "Product")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductData {
  @Id
  @Column(name = "ID")
  private Long id;

  @Column(name = "NAME")
  private String name;
}
