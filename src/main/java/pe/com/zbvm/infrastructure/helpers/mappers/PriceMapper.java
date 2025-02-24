package pe.com.zbvm.infrastructure.helpers.mappers;

import java.math.BigDecimal;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class PriceMapper {

  public PriceDto priceDomainToDto(Price price) {
    var priceDto = new PriceDto();
    priceDto.setPrice(price.priceInDecimal());
    priceDto.setPriceList(price.id().getId());
    priceDto.setBrandId(price.brand().id().getId());
    priceDto.setProductId(price.product().id().getId());
    priceDto.setStartDate(price.startDate().getStartDateWithFormatClient());
    priceDto.setEndDate(price.endDate().getEndDateWithFormatClient());
    priceDto.setCurrency(price.currency().getCurrency());

    return priceDto;
  }

  public Price priceDataToDomain(PriceData priceData) {
    return Price.builder()
        .id(Id.withId(priceData.getId()))
        .brand(brandDataToDomain(priceData.getBrand()))
        .product(productDataToDomain(priceData.getProduct()))
        .priority(Priority.withPriority(priceData.getPriority()))
        .currency(Currency.withCurrency(priceData.getCurrency()))
        .startDate(StartDate.withStartDate(priceData.getStartDate()))
        .endDate(EndDate.withEndDate(priceData.getEndDate()))
        .price(priceData.getPrice()).build();
  }

  public PriceData priceToData(Price price) {
    return PriceData.builder()
        .id(price.id().getId())
        .brand(brandToData(price.brand()))
        .product(productToData(price.product()))
        .startDate(price.startDate().getStartDate())
        .endDate(price.endDate().getEndDate())
        .currency(price.currency().getCurrency())
        .price(price.price())
        .priority(price.priority().getPriority())
        .build();
  }

  public Product productDataToDomain(ProductData productData) {
    return Product.builder()
        .id(Id.withId(productData.getId()))
        .name(productData.getName())
        .build();
  }

  public Brand brandDataToDomain(BrandData brandData) {
    return Brand.builder()
        .id(Id.withId(brandData.getId()))
        .name(brandData.getName())
        .build();
  }

  public BrandData brandToData(Brand brand) {
    return BrandData.builder()
                    .id(brand.id().getId())
                    .build();
  }

  public ProductData productToData(Product product) {
    return ProductData.builder()
        .id(product.id().getId())
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
        .map(Id::withId)
        .orElse(Id.withIdNull());
  }

  private Id getIdForBrandId(PriceCriteriaRequest criteria) {
    return Optional.ofNullable(criteria.getBrandId())
        .map(Id::withId)
        .orElse(Id.withIdNull());
  }
}
