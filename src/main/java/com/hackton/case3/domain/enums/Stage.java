package com.hackton.case3.domain.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Stage {
    @JsonProperty("analysis") ANALYSIS,
    @JsonProperty("development") DEVELOPMENT,
    @JsonProperty("testing") TESTING,
    @JsonProperty("review") REVIEW,
    @JsonProperty("release") RELEASE
}
