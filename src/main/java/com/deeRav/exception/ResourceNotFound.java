package com.deeRav.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFound extends  RuntimeException{
    String resourceName;
    String fieldName;
    Integer fieldValue;

    public ResourceNotFound(String resourceName, String fieldName, Integer fieldValue) {

        super(String.format(" %s not found with %s : %s",resourceName,fieldName,fieldValue ));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
