package com.koitt.book.model;

import java.io.Serializable;

// Java Bean
public class Book implements Serializable {
    private Integer isbn;		// 책 번호 (자동증가)
    private String title;		// 책 제목
    private String author;		// 저자
    private String publisher;	// 출판사
    private Integer price;		// 가격
    private String description;	// 책 설명
    private String attachment;
    private Users users;		// 유저
    
    // 1. 기본 생성자
    public Book() {}

    // 2. 생성자
	public Book(Integer isbn, Integer userNo, String title, String author, String publisher, Integer price, String description, String attachment) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.price = price;
		this.description = description;
		this.attachment = attachment;
		this.users = users;
	}

	// 3. getter, setter
	public Integer getIsbn() {
		return isbn;
	}

	public void setIsbn(Integer isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	


	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	// 4. equals, hashCode 작성
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((users == null) ? 0 : users.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((publisher == null) ? 0 : publisher.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof Book)) {
			return false;
		}
		
		Book other = (Book) obj;
		if (this.isbn.equals(other.isbn)) {
			return true;
		}
		
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Book [isbn=");
		builder.append(isbn);
		builder.append(", users=");
		builder.append(users);
		builder.append(", title=");
		builder.append(title);
		builder.append(", author=");
		builder.append(author);
		builder.append(", publisher=");
		builder.append(publisher);
		builder.append(", price=");
		builder.append(price);
		builder.append(", description=");
		builder.append(description);
		builder.append(", attachment=");
		builder.append(attachment);
		builder.append("]");
		return builder.toString();
	}

	// 5. toString 구현
	
}