package org.example.projectgt.repository;

import org.example.projectgt.entity.Hardware;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HardwareRepository extends JpaRepository<Hardware, String> {
    boolean existsByName(String name);
}
