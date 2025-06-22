package org.example.projectgt.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductHardwareResponse {
    String id;
    String value;
    String hardwareImgUrl;
}
