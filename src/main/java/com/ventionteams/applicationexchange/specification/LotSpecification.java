package com.ventionteams.applicationexchange.specification;

import com.ventionteams.applicationexchange.dto.LotFilterDTO;
import com.ventionteams.applicationexchange.entity.Lot;
import com.ventionteams.applicationexchange.entity.enumeration.Packaging;
import com.ventionteams.applicationexchange.entity.enumeration.Weight;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import static org.springframework.util.CollectionUtils.isEmpty;

public class LotSpecification {

    private LotSpecification() {

    }

    public static Specification<Lot> getFilterSpecification(LotFilterDTO filter) {
        return Specification.where(filter.category() == null ? null : inCategories(filter.category()))
                .or(filter.category() == null ? null : inParentCategories(filter.category()))
                .and(isEmpty(filter.packaging()) ? null : inPackaging(filter.packaging()))
                .and(isEmpty(filter.weights()) ? null : inWeight(filter.weights()))
                .and(isEmpty(filter.locations()) ? null : inLocations(filter.locations()))
                .and(isEmpty(filter.varieties()) ? null : inVarieties(filter.varieties()))
                .and(filter.fromQuantity() == null ? null : fromQuantity(filter.fromQuantity()))
                .and(filter.toQuantity() == null ? null : toQuantity(filter.toQuantity()))
                .and(filter.fromSize() == null ? null : fromSize(filter.fromSize()))
                .and(filter.toSize() == null ? null : toSize(filter.toSize()));
    }

    public static Specification<Lot> inCategories(Integer category) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("category").get("id"), category);
    }

    public static Specification<Lot> inParentCategories(Integer category) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("category").get("parent").get("id"), category);
    }

    private static Specification<Lot> inPackaging(List<Packaging> packaging) {
        return (root, query, criteriaBuilder) -> {
            CriteriaBuilder.In<Packaging> inClause = criteriaBuilder.in(root.get("packaging"));
            for (Packaging pack : packaging) {
                inClause.value(pack);
            }
            return inClause;
        };
    }

    private static Specification<Lot> inWeight(List<Weight> weights) {
        return (root, query, criteriaBuilder) -> {
            CriteriaBuilder.In<Weight> inClause = criteriaBuilder.in(root.get("weight"));
            for (Weight weight : weights) {
                inClause.value(weight);
            }
            return inClause;
        };
    }

    private static Specification<Lot> inLocations(List<Integer> locations) {
        return (root, query, criteriaBuilder) -> {
            CriteriaBuilder.In<Integer> inClause = criteriaBuilder.in(root.get("location").get("id"));
            for (Integer location : locations) {
                inClause.value(location);
            }
            return inClause;
        };
    }

    private static Specification<Lot> inVarieties(List<String> varieties) {
        return (root, query, criteriaBuilder) -> {
            CriteriaBuilder.In<String> inClause = criteriaBuilder.in(root.get("variety"));
            for (String variety : varieties) {
                inClause.value(variety);
            }
            return inClause;
        };
    }

    private static Specification<Lot> fromQuantity(Long fromQuantity) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("quantity"), fromQuantity);
    }

    private static Specification<Lot> toQuantity(Long toQuantity) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("quantity"), toQuantity);
    }

    private static Specification<Lot> fromSize(Integer fromSize) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("size"), fromSize);
    }

    private static Specification<Lot> toSize(Integer toSize) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("size"), toSize);
    }
}
