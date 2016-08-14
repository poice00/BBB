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
@Table(name = "repostrelation", catalog = "mb")
public class Repostrelation implements java.io.Serializable {

	private RepostrelationId id;
	private String content;
	private String state;

	public Repostrelation() {
	}

	public Repostrelation(RepostrelationId id) {
		this.id = id;
	}

	public Repostrelation(RepostrelationId id, String content, String state) {
		this.id = id;
		this.content = content;
		this.state = state;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "mid", column = @Column(name = "mid", nullable = false) ),
			@AttributeOverride(name = "uid", column = @Column(name = "uid", nullable = false) ) })
	public RepostrelationId getId() {
		return this.id;
	}

	public void setId(RepostrelationId id) {
		this.id = id;
	}

	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "state")
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
