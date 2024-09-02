package org.api.entity.security;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "user_rol", schema = "security")
public class UserRol {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_rol_id_gen")
    @SequenceGenerator(name = "user_rol_id_gen", sequenceName = "user_rol_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rolId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

}