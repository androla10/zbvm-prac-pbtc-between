package pe.com.zbvm.infrastructure.helpers.mappers;

import org.springframework.stereotype.Component;
import pe.com.zbvm.domain.entity.Product;
import pe.com.zbvm.domain.vo.Id;

@Component
public class ProductMapper {

  public Product productDtoToDomain(Long id, String name) {
    return Product.builder()
                  .id(Id.withId(id))
                  .name(name)
                  .build();
  }
}
