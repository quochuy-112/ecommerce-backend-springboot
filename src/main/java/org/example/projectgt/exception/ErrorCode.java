package org.example.projectgt.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
    UNCATEGORIED_ERROR(1001, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1002, "Invalid key", HttpStatus.BAD_REQUEST),
    PRODUCT_EXISTED(1003, "Product already existed", HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_EXIST(1004, "Product does not exist", HttpStatus.NOT_FOUND),
    HARDWARE_NOT_EXIST(105, "Hardware does not exist", HttpStatus.BAD_REQUEST),
    PRODUCT_NAME_INVALID(1006, "Product name is invalid", HttpStatus.BAD_REQUEST),
    PRODUCT_DESCRIPTION_INVALID(1007, "Product description is invalid", HttpStatus.BAD_REQUEST),
    PRODUCT_PRICE_INVALID(1008, "Product price is invalid", HttpStatus.BAD_REQUEST),
    CATEGORY_NAME_INVALID(1009, "Category name is invalid", HttpStatus.BAD_REQUEST),
    CATEGORY_EXISTED(1010, "Category already existed", HttpStatus.BAD_REQUEST),
    CATEGORY_NOT_EXIST(1011, "Category does not exist", HttpStatus.NOT_FOUND),
    CATEGORYGROUP_NAME_INVALID(1012, "Category group name is invalid", HttpStatus.BAD_REQUEST),
    CATEGORY_GROUP_NOT_EXIST(1013, "Category group does not exist", HttpStatus.NOT_FOUND),
    CATEGORY_ID_INVALID(1014, "Category id is invalid", HttpStatus.BAD_REQUEST),
    CATEGORY_GROUP_EXISTED(1015, "Category group already existed", HttpStatus.BAD_REQUEST),
    SUBCATEGORY_NAME_INVALID(1016, "Subcategory name is invalid", HttpStatus.BAD_REQUEST),
    SUBCATEGORY_EXISTED(1017, "Subcategory already existed", HttpStatus.BAD_REQUEST),
    SUBCATEGORY_NOT_EXIST(1018, "Subcategory does not exist", HttpStatus.NOT_FOUND),
    PRODUCT_EXISTED_IN_SUBCATEGORY(1019, "Product already existed in subcategory", HttpStatus.BAD_REQUEST),
    USER_EMAIL_EXISTED(1020, "User email already existed", HttpStatus.BAD_REQUEST),
    EMAIL_INVALID(1021, "Email is invalid", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1022, "Password must be between 8 and 32 character", HttpStatus.BAD_REQUEST),
    USER_NOT_EXIST(1023, "User does not exist", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1024, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1025, "You dot not have permission", HttpStatus.FORBIDDEN),
    INVALID_BIRTHDATE(1026, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),
    ;

    int code;
    String message;
    HttpStatusCode httpStatusCode;
}
