package com.mb.domain;
// Generated 2016-8-14 9:29:03 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * RepostrelationId generated by hbm2java
 */
@Embeddable
public class RepostrelationId implements java.io.Serializable {

	private String mid;
	private String uid;

	public RepostrelationId() {
	}

	public RepostrelationId(String mid, String uid) {
		this.mid = mid;
		this.uid = uid;
	}

	@Column(name = "mid", nullable = false)
	public String getMid() {
		return this.mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	@Column(name = "uid", nullable = false)
	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RepostrelationId))
			return false;
		RepostrelationId castOther = (RepostrelationId) other;

		return ((this.getMid() == castOther.getMid())
				|| (this.getMid() != null && castOther.getMid() != null && this.getMid().equals(castOther.getMid())))
				&& ((this.getUid() == castOther.getUid()) || (this.getUid() != null && castOther.getUid() != null
						&& this.getUid().equals(castOther.getUid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getMid() == null ? 0 : this.getMid().hashCode());
		result = 37 * result + (getUid() == null ? 0 : this.getUid().hashCode());
		return result;
	}

}
