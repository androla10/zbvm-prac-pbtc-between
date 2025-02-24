package pe.com.zbvm.application.ports.output;

import pe.com.zbvm.domain.entity.Brand;
import pe.com.zbvm.domain.vo.Id;

public interface BrandManagementOutputPort {
  Brand findById(Id brandId);
}
