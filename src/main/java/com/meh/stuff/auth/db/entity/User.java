package com.meh.stuff.auth.db.entity;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="the_user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
    @Type(type = "uuid-char")
	private UUID uuid;
	private String internalId;
	private String username;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "creator")
	private User creator;
	private OffsetDateTime dateCreated;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "modified_by")
	private User modifiedBy;
	private OffsetDateTime dateModified;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "retired_by")
	private User retiredBy;
	private boolean retired;
	private OffsetDateTime dateRetired;
	private String retiredReason;
	
	@ManyToMany
    @JoinTable(name = "user_role",
               joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") },
               inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") })
	private Set<Role> roles;
}
