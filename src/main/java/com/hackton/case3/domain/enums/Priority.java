package com.hackton.case3.domain.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Priority {
    @JsonProperty("high") HIGH,
    @JsonProperty("medium") MEDIUM,
    @JsonProperty("low") LOW
}
