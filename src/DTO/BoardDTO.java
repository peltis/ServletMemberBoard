package DTO;

import java.sql.Date;

public class BoardDTO {
	private int boardNumber, boardHit;
	private Date boardDate;
	private String boardWrite, boardPassword;
	private String boardSubject, boardContent, boardFile;

	public int getBoardNumber() {
		return boardNumber;
	}
	public void setBoardNumber(int boardNumber) {
		this.boardNumber = boardNumber;
	}
	public int getBoardHit() {
		return boardHit;
	}
	public void setBoardHit(int boardHit) {
		this.boardHit = boardHit;
	}
	public Date getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}
	public String getBoardWrite() {
		return boardWrite;
	}
	public void setBoardWrite(String boardWrite) {
		this.boardWrite = boardWrite;
	}
	public String getBoardPassword() {
		return boardPassword;
	}
	public void setBoardPassword(String boardPassword) {
		this.boardPassword = boardPassword;
	}
	public String getBoardSubject() {
		return boardSubject;
	}
	public void setBoardSubject(String boardSubject) {
		this.boardSubject = boardSubject;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getBoardFile() {
		return boardFile;
	}
	public void setBoardFile(String boardFile) {
		this.boardFile = boardFile;
	}
}
