package pe.com.zbvm.domain.vo;


import java.util.Optional;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class Id {
  private final Long id;

  private Id(Long id) {
    this.id = id;
  }

  public static Id withId(long id) {
    return new Id(id);
  }

  public static Id withIdNull() {
    return new Id(null);
  }
}
