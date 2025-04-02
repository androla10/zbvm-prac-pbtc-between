package pe.com.zbvm.infrastructure.helpers.mappers;

import java.util.Optional;
import org.springframework.stereotype.Component;
import pe.com.zbvm.domain.entity.Brand;
import pe.com.zbvm.domain.entity.Price;
import pe.com.zbvm.domain.entity.Product;
import pe.com.zbvm.domain.vo.ConsultationDate;
import pe.com.zbvm.domain.vo.Currency;
import pe.com.zbvm.domain.vo.EndDate;
import pe.com.zbvm.domain.vo.Id;
import pe.com.zbvm.domain.vo.PriceCriteria;
import pe.com.zbvm.domain.vo.Priority;
import pe.com.zbvm.domain.vo.StartDate;
import pe.com.zbvm.infrastructure.adapters.input.rest.request.PriceCriteriaRequest;
import pe.com.zbvm.infrastructure.adapters.input.rest.server.model.PriceDto;
import pe.com.zbvm.infrastructure.adapters.output.mysql.data.BrandData;
import pe.com.zbvm.infrastructure.adapters.output.mysql.data.PriceData;
import pe.com.zbvm.infrastructure.adapters.output.mysql.data.ProductData;

/**
 * <br/> PriceMapper <br/>
 * <b>Class</b>: PriceMapper<br/>
 * Copyright: &copy; 2024 Inditex.<br/>
 * Company: Inditex.<br/>
 *
 * @author Inditex <br/>
 * <u>Developed by</u>: <br/>
 * <ul>
 * <li>Zeler Benji Villarreal Marcelo</li>
 * </ul>
 * <u>Changes</u>:<br/>
 * <ul>
 * <li>Oct 05, 2024 Creaci&oacute;n de Clase.</li>
 * </ul>
 * @version 1.0
 */
@Component
public class PriceMapper {

  public PriceDto priceDomainToDto(Price price) {
    var priceDto = new PriceDto();
    priceDto.setPrice(price.priceInDecimal());
    priceDto.setPriceList(price.id().getIdentifier());
    priceDto.setBrandId(price.brand().id().getIdentifier());
    priceDto.setProductId(price.product().id().getIdentifier());
    priceDto.setStartDate(price.startDate().getStartDateWithFormatClient());
    priceDto.setEndDate(price.endDate().getEndDateWithFormatClient());
    priceDto.setCurrency(price.currency().getCode());

    return priceDto;
  }

  public Price priceDataToDomain(PriceData priceData) {
    return Price.builder()
        .id(Id.withIdentifier(priceData.getId()))
        .brand(brandDataToDomain(priceData.getBrand()))
        .product(productDataToDomain(priceData.getProduct()))
        .priority(Priority.withValue(priceData.getPriority()))
        .currency(Currency.withCode(priceData.getCurrency()))
        .startDate(StartDate.withValue(priceData.getStartDate()))
        .endDate(EndDate.withValue(priceData.getEndDate()))
        .price(priceData.getPrice()).build();
  }

  public Product productDataToDomain(ProductData productData) {
    return Product.builder()
        .id(Id.withIdentifier(productData.getId()))
        .name(productData.getName())
        .build();
  }

  public Brand brandDataToDomain(BrandData brandData) {
    return Brand.builder()
        .id(Id.withIdentifier(brandData.getId()))
        .name(brandData.getName())
        .build();
  }

  public PriceCriteria priceCriteriaToDomain(PriceCriteriaRequest priceCriteriaRequest) {
    return PriceCriteria.builder()
        .productId(getIdForProductId(priceCriteriaRequest))
        .brandId(getIdForBrandId(priceCriteriaRequest))
        .consultationDate(ConsultationDate.build(priceCriteriaRequest.getConsultationDate()))
        .build();
  }

  private Id getIdForProductId(PriceCriteriaRequest criteria) {
    return Optional.ofNullable(criteria.getProductId())
        .map(Id::withIdentifier)
        .orElse(Id.withIdNull());
  }

  private Id getIdForBrandId(PriceCriteriaRequest criteria) {
    return Optional.ofNullable(criteria.getBrandId())
        .map(Id::withIdentifier)
        .orElse(Id.withIdNull());
  }
}
