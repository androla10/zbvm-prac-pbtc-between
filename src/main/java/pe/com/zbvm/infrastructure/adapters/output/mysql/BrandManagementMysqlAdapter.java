package pe.com.zbvm.infrastructure.adapters.output.mysql;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.com.zbvm.application.ports.output.BrandManagementOutputPort;
import pe.com.zbvm.domain.entity.Brand;
import pe.com.zbvm.domain.vo.Id;
import pe.com.zbvm.infrastructure.adapters.output.mysql.repository.BrandManagementRepository;
import pe.com.zbvm.infrastructure.adapters.output.mysql.repository.PriceManagementRepository;
import pe.com.zbvm.infrastructure.helpers.mappers.BrandMapper;
import pe.com.zbvm.infrastructure.helpers.mappers.PriceMapper;

@Component
@RequiredArgsConstructor
public class BrandManagementMysqlAdapter implements BrandManagementOutputPort {
  private final BrandManagementRepository repository;
  private final BrandMapper mapper;
  private final EntityManager entityManager;

  @Override
  public Brand findById(Id brandId) {
    var searchBrand = repository.findById(brandId.getId());
    return searchBrand.map(mapper::brandDataToDomain)
        .orElse(null);
  }
}
