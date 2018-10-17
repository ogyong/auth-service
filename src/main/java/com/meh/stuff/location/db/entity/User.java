package com.meh.stuff.location.db.entity;

import java.time.OffsetDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	private int id;
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
}
