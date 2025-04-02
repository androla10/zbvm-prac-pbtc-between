package pe.com.zbvm.domain.vo;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class Id {
  private final Long identifier;

  private Id(Long identifier) {
    this.identifier = identifier;
  }

  public static Id withIdentifier(long id) {
    return new Id(id);
  }

  public static Id withIdNull() {
    return new Id(null);
  }
}
