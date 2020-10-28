package io.github.monthalcantara.estudojpa.advice;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class ApiError implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer status;
	
	private Long timeStamp;
	
	private List<String> errors;

	public ApiError(Integer status, String msg, Long timeStamp) {
		super();
		this.status = status;
		this.errors = Arrays.asList(msg);
		this.timeStamp = timeStamp;
	}
	
	public ApiError(Integer status, List<String> errors, Long timeStamp) {
		super();
		this.status = status;
		this.errors = errors;
		this.timeStamp = timeStamp;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	
}
