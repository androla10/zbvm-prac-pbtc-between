package pe.com.zbvm.domain.vo;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * <br/> CurrencyTest <br/>
 * <b>Class</b>: CurrencyTest<br/>
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
 * <li>Apr 02, 2025 Creaci&oacute;n de Clase.</li>
 * </ul>
 * @version 1.0
 */
class CurrencyTest {

  @Test
  @DisplayName("Return True When Currency Is Not Null")
  void returnTrueWhenCurrencyIsNotNull() {
    var currencyCodeExpected = "PEN";

    var currency = Currency.withCode(currencyCodeExpected);

    assertAll(
        () -> assertNotNull(currency),
        () -> assertEquals(currencyCodeExpected, currency.getCode())
    );
  }

  @Test
  @DisplayName("Occurs Exception When Currency Is Invalid")
  void occursExceptionWhenCurrencyIsInvalid() {
    // Arrange
    var currencyCodeExpected = "INVALID_CURRENCY";

    // Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> Currency.withCode(currencyCodeExpected));

    // Assert
    assertEquals("Invalid currency", exception.getMessage());
  }
}