package com.ventionteams.applicationexchange.dto.read;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ventionteams.applicationexchange.entity.enumeration.BidStatus;
import com.ventionteams.applicationexchange.entity.enumeration.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BidReadDto{
        @JsonProperty("bid_id")
        private Long id;
        @JsonProperty("user_id")
        private UUID userId;
        @JsonProperty("lot_id")
        private Long lotId;
        private BidStatus status;
        private Double amount;
        private Currency currency;

}
