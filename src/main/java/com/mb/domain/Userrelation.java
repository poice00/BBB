package com.mb.domain;
// Generated 2016-8-14 9:29:03 by Hibernate Tools 4.0.0

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Repostrelation generated by hbm2java
 */
@Entity
public class Userrelation implements java.io.Serializable {

	private UserrelationId id;


	@EmbeddedId
	public UserrelationId getId() {
		return this.id;
	}

	public void setId(UserrelationId id) {
		this.id = id;
	}


}
