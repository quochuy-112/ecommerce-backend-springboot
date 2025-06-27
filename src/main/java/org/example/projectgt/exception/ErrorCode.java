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
    CATEGORY_NOT_EXIST(1011, "Category does not exist"),
    CATEGORYGROUP_NAME_INVALID(1012, "Category group name is invalid"),
    CATEGORY_GROUP_NOT_EXIST(1013, "Category group does not exist"),
    CATEGORY_ID_INVALID(1014, "Category id is invalid"),
    CATEGORY_GROUP_EXISTED(1015, "Category group already existed"),
    SUBCATEGORY_NAME_INVALID(1016, "Subcategory name is invalid"),
    SUBCATEGORY_EXISTED(1017, "Subcategory already existed"),
    SUBCATEGORY_NOT_EXIST(1018, "Subcategory does not exist"),
    PRODUCT_EXISTED_IN_SUBCATEGORY(1019, "Product already existed in subcategory"),
    USER_EMAIL_EXISTED(1020, "User email already existed"),
    EMAIL_INVALID(1021, "Email is invalid"),
    PASSWORD_INVALID(1022, "Password is invalid"),
    USER_NOT_EXIST(1023, "User does not exist"),
    UNAUTHENTICATED(1024, "Unauthenticated"),
    ;
    int code;
    String message;
}
