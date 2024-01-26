package com.ventionteams.applicationexchange.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Data {
    @JsonProperty("lots")
    private List<Lot> lots;
    @JsonIgnore
    private List<Category> categories;
    @JsonIgnore
    private List<User> users;

}