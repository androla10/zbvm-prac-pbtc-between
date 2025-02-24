package pe.com.zbvm.application.usecases;

import pe.com.zbvm.domain.entity.Brand;
import pe.com.zbvm.domain.vo.Id;

public interface BrandManagementUseCase {
  Brand getBrandById(Id brandId);
}
