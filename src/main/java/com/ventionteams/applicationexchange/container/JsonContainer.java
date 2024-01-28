package com.ventionteams.applicationexchange.container;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ventionteams.applicationexchange.dto.CategoryCreateEditDto;
import com.ventionteams.applicationexchange.dto.CategoryReadDto;
import com.ventionteams.applicationexchange.dto.LotReadDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public record JsonContainer(List<MockUser> users, List<CategoryCreateEditDto> categories, List<LotReadDTO> lots) {
}

class MockUser {
    @JsonProperty("user_id")
    Integer id;
    @JsonProperty("first_name")
    String firstName;
    @JsonProperty("last_name")
    String lastName;
    @JsonProperty("email")
    String email;
    @JsonProperty("role")
    String role;
    @JsonProperty("password")
    String password;
}

class MockLot {
    @JsonProperty("lot_id")
    Integer id;
    @JsonProperty("title")
    String title;
    @JsonProperty("product_type")
    String productType;
    @JsonProperty("product_subtype")
    String productSubtype;
    @JsonProperty("quantity")
    Integer quantity;
    @JsonProperty("price_per_unit")
    Double pricePerUnit;
    @JsonProperty("location")
    MockLocation location;
    @JsonProperty("description")
    String description;
    @JsonProperty("status")
    String status;
    @JsonProperty("image_url")
    String imageUrl;
}

class MockLocation {
    @JsonProperty("country")
    String country;
    @JsonProperty("region")
    String region;
}
