package com.azure.redis.dto;

import lombok.Data;

@Data
public class UserReferenceType {

    private String referenceType;

    private String apiCode;

    private Boolean isDefault;

    private Boolean isActive;
}
