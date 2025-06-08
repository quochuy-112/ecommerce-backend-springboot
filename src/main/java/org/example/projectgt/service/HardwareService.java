package org.example.projectgt.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.projectgt.dto.request.HardwareCreationRequest;
import org.example.projectgt.dto.request.HardwareUpdateRequest;
import org.example.projectgt.dto.request.ProductCreationRequest;
import org.example.projectgt.dto.request.ProductUpdateRequest;
import org.example.projectgt.dto.response.HardwareResponse;
import org.example.projectgt.dto.response.ProductResponse;
import org.example.projectgt.entity.Hardware;
import org.example.projectgt.entity.Product;
import org.example.projectgt.mapper.HardwareMapper;
import org.example.projectgt.repository.HardwareRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class HardwareService {
    HardwareRepository hardwareRepository;
    HardwareMapper hardwareMapper;

    public HardwareResponse createRequest(HardwareCreationRequest request){
        if(hardwareRepository.existsByName(request.getName()))
            throw new RuntimeException("Product name already exists");

        Hardware hardware = hardwareMapper.toHardware(request);

        return hardwareMapper.toHardwaResponse(hardwareRepository.save(hardware));
    }

    public List<HardwareResponse> getAllHardwares(){
        return hardwareRepository.findAll()
                .stream()
                .map(hardwareMapper::toHardwaResponse)
                .toList();
    }

    public HardwareResponse getHardwareById(String id){
        return hardwareMapper.toHardwaResponse(hardwareRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Id not found")));
    }

    public HardwareResponse updateHardware(String id, HardwareUpdateRequest request){
        Hardware hardware = hardwareRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Id not found"));

        hardwareMapper.updateHardware(hardware, request);

        return hardwareMapper.toHardwaResponse(hardwareRepository.save(hardware));
    }

    @Transactional
    public void deleteHardware(String id){
        hardwareRepository.deleteById(id);
    }
}
