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
  private final LocalDateTime startDate;

  private StartDate(LocalDateTime startDate) {
    this.startDate = getDefaultDateWhenIsNull(startDate);
  }

  private LocalDateTime getDefaultDateWhenIsNull(LocalDateTime startDate) {
    return Optional.ofNullable(startDate).orElse(LocalDateTime.now());
  }

  public OffsetDateTime getStartDateWithFormatClient() {
    ZoneId zoneId = ZoneId.systemDefault();
    return this.startDate.atZone(zoneId).toOffsetDateTime();
  }

  public static StartDate withStartDate(LocalDateTime startDate) {
    return new StartDate(startDate);
  }
}
