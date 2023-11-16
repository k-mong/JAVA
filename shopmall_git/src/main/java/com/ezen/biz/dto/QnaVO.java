package com.ezen.biz.dto;

import java.util.Date;

import lombok.Data;

@Data
public class QnaVO {
	private int qseq;
	private String subject;
	private String content;
	private String reply;
	private String id;
	private String rep;
	private Date indate;
}
