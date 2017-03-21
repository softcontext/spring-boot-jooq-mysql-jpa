package com.example.demo.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = { "comments" })
@Entity
@Table(name="POSTS")
public class Post {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="TITLE", nullable=false)
	private String title;
	
	@Column(name="CONTENT")
	private String content;
	
	@Column(name="CREATED_ON")
	private Timestamp createdOn;
	
	@OneToMany(mappedBy="post", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	private List<Comment> comments = new ArrayList<>();

	public Post(Integer id) {
		this.id = id;
	}

	public Post(Integer id, String title, String content, Timestamp createdOn) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.createdOn = createdOn;
	}
	
	public void addComment(Comment comment) {
		if (comment.getPost() == null) {
			comment.setPost(this);
		}
		this.comments.add(comment);
	}

}
