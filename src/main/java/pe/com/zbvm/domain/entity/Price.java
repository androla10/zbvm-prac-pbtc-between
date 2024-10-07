package pe.com.zbvm.domain.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.Builder;
import pe.com.zbvm.domain.vo.Currency;
import pe.com.zbvm.domain.vo.EndDate;
import pe.com.zbvm.domain.vo.Id;
import pe.com.zbvm.domain.vo.Priority;
import pe.com.zbvm.domain.vo.StartDate;

@Builder
public record Price(Id id, Brand brand, StartDate startDate, EndDate endDate, Product product,
                    BigDecimal price, Currency currency, Priority priority) {

  public Double priceInDecimal() {
    var price = getPriceWithTwoDecimal();
    return price.doubleValue();
  }

  private BigDecimal getPriceWithTwoDecimal() {
    return this.price.setScale(2, RoundingMode.HALF_EVEN);
  }
}
