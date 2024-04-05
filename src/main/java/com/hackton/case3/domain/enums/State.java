package com.hackton.case3.domain.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum State {
    @JsonProperty("in_queue") IN_QUEUE,
    @JsonProperty("in_processing") IN_PROCESSING,
    @JsonProperty("complete") COMPLETE
}
