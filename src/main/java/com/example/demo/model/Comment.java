package com.example.demo.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="COMMENTS")
public class Comment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="POST_ID", foreignKey=@ForeignKey(name = "FK_COMMENTS_POST_ID"))
	private Post post;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="CONTENT", nullable=false)
	private String content;
	
	@Column(name="CREATED_ON")
	private Timestamp createdOn;

	public Comment(Integer id, Integer postId, String name, String email, String content, Timestamp createdOn) {
		this.id = id;
		this.post = new Post(postId);
		this.name = name;
		this.email = email;
		this.content = content;
		this.createdOn = createdOn;
	}

}
