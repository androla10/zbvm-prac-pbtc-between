package pe.com.zbvm.infrastructure.helpers.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.com.zbvm.domain.entity.Brand;
import pe.com.zbvm.domain.vo.Id;
import pe.com.zbvm.infrastructure.adapters.output.mysql.data.BrandData;

@Component
@RequiredArgsConstructor
public class BrandMapper {

  public BrandData brandToData(Brand brand) {
    return BrandData.builder()
        .id(brand.id().getId())
        .name(brand.name())
        .build();
  }

  public Brand brandDataToDomain(BrandData brandData) {
    return Brand.builder()
        .id(Id.withId(brandData.getId()))
        .name(brandData.getName())
        .build();
  }
}
