package com.hackton.case3.domain.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Type {
    @JsonProperty("feature") FEATURE,
    @JsonProperty("fix") FIX,
    @JsonProperty("error") ERROR
}
