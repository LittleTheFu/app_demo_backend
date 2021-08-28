package com.fu.demo.mbg.model;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class History {
	@ApiModelProperty(value = "id")
	private long id;
	
	@ApiModelProperty(value = "historyUserId")
	private long historyUserId;
	
	@ApiModelProperty(value = "historyArticleId")
	private long historyArticleId;
	
	@ApiModelProperty(value = "historyDate")
	private Date historyDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getHistoryUserId() {
		return historyUserId;
	}

	public void setHistoryUserId(long historyUserId) {
		this.historyUserId = historyUserId;
	}

	public long getHistoryArticleId() {
		return historyArticleId;
	}

	public void setHistoryArticleId(long historyArticleId) {
		this.historyArticleId = historyArticleId;
	}

	public Date getHistoryDate() {
		return historyDate;
	}

	public void setHistoryDate(Date historyDate) {
		this.historyDate = historyDate;
	}
}
