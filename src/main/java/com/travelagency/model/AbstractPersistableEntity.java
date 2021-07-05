package com.travelagency.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * The Abstract Persistable entity for all other db schema entity
 *
 * @author Hasan Mahmud
 * @since 2021-07-05
 */
@Data
@MappedSuperclass
public class AbstractPersistableEntity implements Serializable {

    private static final long serialVersionUID = 4240005902936474749L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Version
    private Long version;

    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Setter(value = AccessLevel.PRIVATE)
    private LocalDateTime updatedAt;

    @PrePersist
    void onInsert() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
