package org.example.projectgt.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.projectgt.dto.request.HardwareCreationRequest;
import org.example.projectgt.dto.request.HardwareUpdateRequest;
import org.example.projectgt.dto.response.HardwareResponse;
import org.example.projectgt.service.HardwareService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/hardwares")
@RequiredArgsConstructor
public class HardwareController {
    HardwareService hardwareService;

    @PostMapping("")
    HardwareResponse createHardware(@RequestBody HardwareCreationRequest request) {
        return hardwareService.createRequest(request);
    }

    @GetMapping("")
    List<HardwareResponse> getAllHardwares() {
        return hardwareService.getAllHardwares();
    }

    @GetMapping("/{id}")
    HardwareResponse getHardware(@PathVariable String id) {
        return hardwareService.getHardwareById(id);
    }

    @PutMapping("/{id}")
    HardwareResponse updateHardware(@PathVariable String id,@RequestBody HardwareUpdateRequest request){
        return hardwareService.updateHardware(id,request);
    }

    @DeleteMapping("/{id}")
    void deleteHardware(@PathVariable String id) {
        hardwareService.deleteHardware(id);
    }
}
