package pe.com.zbvm.domain.vo;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Optional;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class EndDate {
  private final LocalDateTime value;

  private EndDate(LocalDateTime value) {
    this.value = getDefaultDateWhenIsNull(value);
  }

  public static EndDate withValue(LocalDateTime endDate) {
    return new EndDate(endDate);
  }

  private LocalDateTime getDefaultDateWhenIsNull(LocalDateTime endDate) {
    return Optional.ofNullable(endDate).orElse(LocalDateTime.now());
  }

  public OffsetDateTime getEndDateWithFormatClient() {
    ZoneId zoneId = ZoneId.systemDefault();
    return this.value.atZone(zoneId).toOffsetDateTime();
  }
}