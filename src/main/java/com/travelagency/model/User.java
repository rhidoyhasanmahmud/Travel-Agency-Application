package com.travelagency.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * The persistent class for the pmt_user database table.
 *
 * @author Hasan Mahmud
 * @since 2021-07-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "user_details")
@ToString(callSuper = true)
public class User extends AbstractPersistableEntity {
    private static final long serialVersionUID = 1L;

    @Convert(converter = StringTrimConverter.class)
    @Column(nullable = false, updatable = false, unique = true, length = 100)
    @NotNull
    @Size(max = 100)
    private String userId;

    @Convert(converter = StringTrimConverter.class)
    @Column(nullable = false)
    @NotNull
    private String name;

    private String password;

    @Convert(converter = StringTrimConverter.class)
    @Column(nullable = false)
    @NotNull
    @Email
    private String email;
}
