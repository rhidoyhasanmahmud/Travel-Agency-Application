package com.travelagency.travelagency.model;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The persistent class for the location database table.
 *
 * @author Hasan Mahmud
 * @since 2021-07-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@ToString(callSuper = true)
@Table(name = "location")
public class Location extends AbstractPersistableEntity {
    private static final long serialVersionUID = 1L;

    @Convert(converter = StringTrimConverter.class)
    @Column(nullable = false, unique = true)
    @NotNull
    private String name;

    @Column(nullable = false)
    private boolean isActive = true;
}
