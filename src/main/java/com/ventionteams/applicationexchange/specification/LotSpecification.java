package com.ventionteams.applicationexchange.specification;

import com.ventionteams.applicationexchange.dto.LotFilterDTO;
import com.ventionteams.applicationexchange.entity.Category;
import com.ventionteams.applicationexchange.entity.Location;
import com.ventionteams.applicationexchange.entity.Lot;
import com.ventionteams.applicationexchange.entity.enumeration.Packaging;
import com.ventionteams.applicationexchange.entity.enumeration.Weight;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import static org.springframework.util.CollectionUtils.isEmpty;

public class LotSpecification {

    private LotSpecification() {

    }

    public static Specification<Lot> getFilterSpecification(LotFilterDTO filter) {
        return Specification.where(isEmpty(filter.categories()) ? null : inCategories(filter.categories()))
                .and(isEmpty(filter.packaging()) ? null : inPackaging(filter.packaging()))
                .and(isEmpty(filter.weights()) ? null : inWeight(filter.weights()))
                .and(isEmpty(filter.locations()) ? null : inLocations(filter.locations()))
                .and(isEmpty(filter.varieties()) ? null : inVarieties(filter.varieties()))
                .and(filter.fromQuantity() == null ? null : fromQuantity(filter.fromQuantity()))
                .and(filter.toQuantity() == null ? null : toQuantity(filter.toQuantity()))
                .and(filter.fromSize() == null ? null : fromSize(filter.fromSize()))
                .and(filter.toSize() == null ? null : toSize(filter.toSize()));
    }

    public static Specification<Lot> inCategories(List<Category> categories) {
        return (root, query, criteriaBuilder) -> {
            Join<Category, Lot> join = root.join("category_id", JoinType.INNER);
            CriteriaBuilder.In<Category> inClause = criteriaBuilder.in(join.get("id"));
            for (Category category : categories) {
                inClause.value(category);
            }
            return inClause;
        };
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

    private static Specification<Lot> inLocations(List<Location> locations) {
        return (root, query, criteriaBuilder) -> {
            Join<Category, Lot> join = root.join("location_id", JoinType.INNER);
            CriteriaBuilder.In<Location> inClause = criteriaBuilder.in(join.get("id"));
            for (Location location : locations) {
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
                criteriaBuilder.greaterThan(root.get("quantity"), fromQuantity);
    }

    private static Specification<Lot> toQuantity(Long toQuantity) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThan(root.get("quantity"), toQuantity);
    }

    private static Specification<Lot> fromSize(Integer fromSize) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("size"), fromSize);
    }

    private static Specification<Lot> toSize(Integer toSize) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThan(root.get("size"), toSize);
    }
}
