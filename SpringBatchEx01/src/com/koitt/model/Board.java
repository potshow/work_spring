package com.koitt.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="record")
public class Board {
	
	private Integer id;
	private String title;
	private String content;
	private String writer;
	private Date date;
	
	
	@XmlAttribute(name="id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@XmlElement(name="title")
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@XmlElement(name="content")
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	@XmlElement(name="writer")
	public String getWriter() {
		return writer;
	}
	
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date regDate) {
		this.date = regDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Board [id=");
		builder.append(id);
		builder.append(", title=");
		builder.append(title);
		builder.append(", content=");
		builder.append(content);
		builder.append(", writer=");
		builder.append(writer);
		builder.append(", date=");
		builder.append(date);
		builder.append("]");
		return builder.toString();
	}


	
}
