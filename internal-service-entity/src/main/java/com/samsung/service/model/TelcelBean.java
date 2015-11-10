package com.samsung.service.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_TC_EVENT", schema = "dbo", catalog = "BI")
public class TelcelBean implements java.io.Serializable {

	/**
	 * @serialField
	 */
	private static final long serialVersionUID = -447479175985587461L;
	@Id
	@Column(name="FIRE_INSTANCE_ID")
	private String fireInstanceId;
	@Column(name="FIRE_TIME", nullable=false)
	private Date fireTime;
	@Column(name="FINISH_TIME")
	private Date finishTime;
	@Column(name="ERROR_DESC")
	private String errorDesc;

	public String getFireInstanceId() {
		return fireInstanceId;
	}

	public Date getFireTime() {
		return fireTime;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setFireInstanceId(String fireInstanceId) {
		this.fireInstanceId = fireInstanceId;
	}

	public void setFireTime(Date fireTime) {
		this.fireTime = fireTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fireInstanceId == null) ? 0 : fireInstanceId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof TelcelBean)) {
			return false;
		}
		TelcelBean other = (TelcelBean) obj;
		if (fireInstanceId == null) {
			if (other.fireInstanceId != null) {
				return false;
			}
		} else if (!fireInstanceId.equals(other.fireInstanceId)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Event [fireInstanceId=");
		builder.append(fireInstanceId);
		builder.append(", fireTime=");
		builder.append(fireTime);
		builder.append(", finishTime=");
		builder.append(finishTime);
		builder.append(", errorDesc=");
		builder.append(errorDesc);
		builder.append("]");
		return builder.toString();
	}
}
