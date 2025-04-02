package pe.com.zbvm.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class Priority {
  private final Byte value;

  private Priority(Byte value) {
    this.value = value;
  }

  public static Priority withValue(Byte value) {
    return new Priority(value);
  }
}
