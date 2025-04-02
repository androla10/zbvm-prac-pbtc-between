package pe.com.zbvm.domain.vo;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.OffsetDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * <br/> ConsultationDateTest <br/>
 * <b>Class</b>: ConsultationDateTest<br/>
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
class ConsultationDateTest {

  @Test
  @DisplayName("Return True When Consultation Date Is Not Null")
  void returnTrueWhenConsultationDateIsNotNull() {
    var expected = true;
    var offsetDateTime = OffsetDateTime.now();

    var consultationDate = ConsultationDate.build(offsetDateTime);

    assertAll(
        () -> assertEquals(expected, consultationDate.isValid())
    );
  }

  @Test
  @DisplayName("Return False When Consultation Date Is Null")
  void returnFalseWhenConsultationDateIsNull() {
    var expected = false;

    var consultationDate = ConsultationDate.build(null);

    assertAll(
        () -> assertEquals(expected, consultationDate.isValid())
    );
  }
}