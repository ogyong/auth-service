package com.meh.stuff.auth.db.entity;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "role")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Type(type = "uuid-char")
    private UUID uuid;
    private String roleName;
    private String roleDescription;

    @ManyToMany
    @JoinTable(name = "role_permission",
               joinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") },
               inverseJoinColumns = { @JoinColumn(name = "permission_id", referencedColumnName = "id") })
    private Set<Permission> permissions;

    @ManyToMany
    @JoinTable(name = "role_role",
               joinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") },
               inverseJoinColumns = { @JoinColumn(name = "parent_role_id", referencedColumnName = "id") })
    private Set<Role> childRoles;

}
