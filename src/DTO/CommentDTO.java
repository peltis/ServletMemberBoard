package DTO;

import java.sql.Date;

public class CommentDTO {
	private int commentNum, commentHit;
	public int getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
	public int getCommentHit() {
		return commentHit;
	}
	public void setCommentHit(int commentHit) {
		this.commentHit = commentHit;
	}
	public String getCommentWriter() {
		return commentWriter;
	}
	public void setCommentWriter(String commentWriter) {
		this.commentWriter = commentWriter;
	}
	public String getCommentSubject() {
		return commentSubject;
	}
	public void setCommentSubject(String commentSubject) {
		this.commentSubject = commentSubject;
	}
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	private String commentWriter, commentSubject;
	private Date commentDate;
}
