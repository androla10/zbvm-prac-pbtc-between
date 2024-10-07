package pe.com.zbvm.infrastructure.adapters.output.mysql;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import pe.com.zbvm.application.ports.output.PriceManagementOutputPort;
import pe.com.zbvm.domain.entity.Price;
import pe.com.zbvm.domain.vo.PriceCriteria;
import pe.com.zbvm.infrastructure.adapters.output.mysql.data.PriceData;
import pe.com.zbvm.infrastructure.adapters.output.mysql.repository.PriceManagementRepository;
import pe.com.zbvm.infrastructure.adapters.output.mysql.specification.PriceSpecification;
import pe.com.zbvm.infrastructure.helpers.mappers.PriceMapper;

/**
 * <br/> PriceManagementMySqlAdapter <br/>
 * <b>Class</b>: PriceManagementMySqlAdapter<br/>
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
public class PriceManagementMySqlAdapter implements PriceManagementOutputPort {

  private final PriceManagementRepository repository;
  private final PriceMapper mapper;
  private final EntityManager entityManager;

  @Override
  public List<Price> getAll() {
    return repository.findAll().stream()
        .map(mapper::priceDataToDomain).toList();
  }

  @Override
  public List<Price> findFirstByCriteriaOrderByDescPriority(PriceCriteria priceCriteria) {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<PriceData> cq = criteriaBuilder.createQuery(PriceData.class);
    Root<PriceData> root = cq.from(PriceData.class);

    Specification<PriceData> predicates =
        Specification.where(PriceSpecification.hasProduct(priceCriteria.getProductId().getId()))
            .and(PriceSpecification.hasBrand(priceCriteria.getBrandId().getId()))
            .and(PriceSpecification.betweenStartDateAndEndDate(priceCriteria.getConsultationDate()
                .getDate()))
            .and(PriceSpecification.orderByDescPriority());

    cq.where(predicates.toPredicate(root, cq, criteriaBuilder));

    TypedQuery<PriceData> query = entityManager.createQuery(cq);
    if (priceCriteria.getConsultationDate().isValid()) {
      query.setMaxResults(1);
    }
    return query.getResultList()
        .stream().map(mapper::priceDataToDomain)
        .toList();
  }
}
