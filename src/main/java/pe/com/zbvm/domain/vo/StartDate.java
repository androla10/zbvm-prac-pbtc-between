package pe.com.zbvm.domain.vo;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Optional;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@ToString
@EqualsAndHashCode
@Slf4j
public class StartDate {
  private final LocalDateTime value;

  private StartDate(LocalDateTime value) {
    this.value = getDefaultDateWhenIsNull(value);
  }

  private LocalDateTime getDefaultDateWhenIsNull(LocalDateTime value) {
    return Optional.ofNullable(value).orElse(LocalDateTime.now());
  }

  public OffsetDateTime getStartDateWithFormatClient() {
    ZoneId zoneId = ZoneId.systemDefault();
    return this.value.atZone(zoneId).toOffsetDateTime();
  }

  public static StartDate withValue(LocalDateTime value) {
    return new StartDate(value);
  }
}
