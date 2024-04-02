package com.ventionteams.applicationexchange.dto.read;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ventionteams.applicationexchange.entity.Image;
import com.ventionteams.applicationexchange.entity.Location;
import com.ventionteams.applicationexchange.entity.enumeration.Currency;
import com.ventionteams.applicationexchange.entity.enumeration.LengthUnit;
import com.ventionteams.applicationexchange.entity.enumeration.LotStatus;
import com.ventionteams.applicationexchange.entity.enumeration.Packaging;
import com.ventionteams.applicationexchange.entity.enumeration.Weight;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class LotReadDTO {
    @JsonProperty("lot_id")
    private Long id;
    private String title;
    @JsonProperty("category_id")
    private Integer categoryId;
    @JsonProperty("category_name")
    private String category;
    private Long quantity;
    private Weight weight;
    @JsonProperty("price_per_unit")
    private Double pricePerUnit;
    @JsonProperty("image_url")
    private List<Image> images = new ArrayList<>();
    private Location location;
    private String description;
    private LotStatus status;
    @JsonProperty("expiration_date")
    private Instant expirationDate;
    private Integer fromSize;
    private Integer toSize;
    private Packaging packaging;
    @JsonProperty("created_at")
    private Instant createdAt;
    @JsonProperty("created_by")
    private String createdBy;
    @JsonProperty("total_price")
    private Long totalPrice;
    @JsonProperty("start_price")
    private Long startPrice;
    @JsonProperty("length_unit")
    private LengthUnit lengthUnit;
    private BidReadDto leading;
    private BidReadDto users;
    @JsonProperty("bid_quantity")
    private Integer bidQuantity;
    private Currency currency;
    @JsonProperty("reject_message")
    private String rejectMessage;
}
