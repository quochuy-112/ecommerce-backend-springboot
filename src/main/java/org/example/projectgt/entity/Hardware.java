package org.example.projectgt.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Hardware {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String name;
    String imgUrl;

    @OneToMany(mappedBy = "hardware", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<ProductHardware> productHardwares = new HashSet<>();
}
