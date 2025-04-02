package pe.com.zbvm.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class Currency {
  private final String code;

  private Currency(String code) {
    this.code = code;
  }

  public static Currency withCode(String currency) {
    if (isInvalidCurrency(currency)) {
      throw new IllegalArgumentException("Invalid currency");
    }
    return new Currency(currency);
  }

  private static boolean isInvalidCurrency(String currency) {
    return java.util.Currency.getAvailableCurrencies().stream()
        .noneMatch(x -> x.getCurrencyCode().compareTo(currency) == 0);
  }
}
