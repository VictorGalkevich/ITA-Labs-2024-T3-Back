package com.ventionteams.applicationexchange.entity;

import com.ventionteams.applicationexchange.entity.enumeration.LotSortField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LotSortCriteria {
    private LotSortField field;
    private Sort.Direction order;
}
