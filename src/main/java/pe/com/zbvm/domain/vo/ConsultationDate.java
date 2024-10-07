package pe.com.zbvm.domain.vo;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Optional;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * <br/> ConsultationDate <br/>
 * <b>Class</b>: ConsultationDate<br/>
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
@EqualsAndHashCode
@ToString
public class ConsultationDate {
  private final LocalDateTime date;

  private ConsultationDate(LocalDateTime date) {
    this.date = date;
  }

  public static ConsultationDate build(OffsetDateTime offsetDateTime) {
    return Optional.ofNullable(offsetDateTime)
        .map(OffsetDateTime::toLocalDateTime)
        .map(ConsultationDate::new)
        .orElse(ConsultationDate.buildWithoutNull());
  }

  public static ConsultationDate buildWithoutNull() {
    return new ConsultationDate(null);
  }

  public boolean isValid() {
    return Optional.ofNullable(date).isPresent();
  }
}
