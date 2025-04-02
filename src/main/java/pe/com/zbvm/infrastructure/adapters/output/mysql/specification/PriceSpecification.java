package pe.com.zbvm.infrastructure.adapters.output.mysql.specification;

import jakarta.persistence.criteria.Join;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.domain.Specification;
import pe.com.zbvm.infrastructure.adapters.output.mysql.data.BrandData;
import pe.com.zbvm.infrastructure.adapters.output.mysql.data.PriceData;
import pe.com.zbvm.infrastructure.adapters.output.mysql.data.ProductData;

/**
 * <br/> PriceSpecification <br/>
 * <b>Class</b>: PriceSpecification<br/>
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
public class PriceSpecification {

  private PriceSpecification() {
    throw new IllegalStateException("PriceSpecification class");
  }

  public static Specification<PriceData> hasProduct(Long productId) {
    return (price, cq, cb) -> {
      Join<PriceData, ProductData> joinProduct = price.join("product");
      if (Optional.ofNullable(productId).isEmpty()) {
        return cb.conjunction();
      }
      return cb.equal(joinProduct.get("id"), productId);
    };
  }

  public static Specification<PriceData> hasBrand(Long brandId) {
    return (price, cq, cb) -> {
      Join<PriceData, BrandData> joinProduct = price.join("brand");
      if (Optional.ofNullable(brandId).isEmpty()) {
        return cb.conjunction();
      }
      return cb.equal(joinProduct.get("id"), brandId);
    };
  }

  public static Specification<PriceData> betweenStartDateAndEndDate(
      LocalDateTime consultationDate) {
    return (price, cq, cb) -> {
      if (Optional.ofNullable(consultationDate).isEmpty()) {
        return cb.conjunction();
      }

      return cb.between(cb.literal(consultationDate), price.get("startDate"), price.get("endDate"));
    };
  }
}
