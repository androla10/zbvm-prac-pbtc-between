package pe.com.zbvm.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class Priority {
  private final Byte priority;

  private Priority(Byte priority) {
    this.priority = priority;
  }

  public static Priority withPriority(Byte priority) {
    return new Priority(priority);
  }
}
