package org.example.projectgt.dto.response;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiReponse<T>{
    int code = 1000;
    String message;
    T data;
}