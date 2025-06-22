package org.example.projectgt.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
    UNCATEGORIED_ERROR(1001, "Uncategorized error"),
    INVALID_KEY(1002, "Invalid key"),
    PRODUCT_EXISTED(1003, "Product already existed"),
    PRODUCT_NOT_EXIST(1004, "Product does not exist"),
    HARDWARE_NOT_EXIST(105, "Hardware does not exist"),
    PRODUCT_NAME_INVALID(1006, "Product name is invalid"),
    PRODUCT_DESCRIPTION_INVALID(1007, "Product description is invalid"),
    PRODUCT_PRICE_INVALID(1008, "Product price is invalid"),
    CATEGORY_NAME_INVALID(1009, "Category name is invalid"),
    CATEGORY_EXISTED(1010, "Category already existed"),
    ;
    int code;
    String message;
}
