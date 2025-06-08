package org.example.projectgt.mapper;

import org.example.projectgt.dto.request.HardwareCreationRequest;
import org.example.projectgt.dto.request.HardwareUpdateRequest;
import org.example.projectgt.dto.response.HardwareResponse;
import org.example.projectgt.entity.Hardware;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface HardwareMapper {
    Hardware toHardware(HardwareCreationRequest request);

    HardwareResponse toHardwaResponse(Hardware hardware);

    void updateHardware(@MappingTarget Hardware hardware, HardwareUpdateRequest request);
}
