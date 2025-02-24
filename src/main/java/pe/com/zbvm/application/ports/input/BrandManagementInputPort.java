package pe.com.zbvm.application.ports.input;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.zbvm.application.ports.output.BrandManagementOutputPort;
import pe.com.zbvm.application.usecases.BrandManagementUseCase;
import pe.com.zbvm.domain.entity.Brand;
import pe.com.zbvm.domain.vo.Id;

@Service
@RequiredArgsConstructor
public class BrandManagementInputPort implements BrandManagementUseCase {
  private final BrandManagementOutputPort brandManagementOutputPort;

  @Override
  public Brand getBrandById(Id brandId) {
    return brandManagementOutputPort.findById(brandId);
  }
}
