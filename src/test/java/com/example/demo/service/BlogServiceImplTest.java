package com.example.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Comment;
import com.example.demo.model.Post;

@Sql("classpath:data-mariadb.sql")
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BlogServiceImplTest {

	@Autowired
	private BlogServiceImpl blogService;
	
	@Test
	public void testCreatePost() {
		Post post = new Post(0, "My new Post", "This is my new test post", new Timestamp(System.currentTimeMillis()));
		Post savedPost = blogService.createPost(post);
		
		Post newPost = blogService.getPost(savedPost.getId());
		assertEquals("My new Post", newPost.getTitle());
		assertEquals("This is my new test post", newPost.getContent());
	}

	@Test
	public void testGetPosts() {
		List<Post> posts = blogService.getPosts();
		assertNotNull(posts);
		assertTrue(!posts.isEmpty());
		
		for (Post post : posts) {
			System.err.println(post);
		}
	}

	@Test
	public void testGetPost() {
		Integer postId = 1;
		Post post = blogService.getPost(postId);
		assertNotNull(post);
		System.err.println(post);
		
		List<Comment> comments = post.getComments();
		System.err.println(comments);
	}

	@Test
	public void testCreateComment() {
		Integer postId = 1;
		
//		Post post = blogService.getPost(postId);
//		assertNotNull(post);
//		int oldSize = post.getComments().size();
//		System.err.println("oldSize = "+oldSize);
		
		Comment comment = new Comment(0, postId, "User4", "user4@gmail.com", "This is my new comment on post1", new Timestamp(System.currentTimeMillis()));
		Comment savedComment = blogService.createComment(comment);
		System.err.println(">>> "+savedComment);
		
		Post post = blogService.getPost(postId);
		List<Comment> comments = post.getComments();
		assertNotNull(comments);
//		int newSize = post.getComments().size();
//		System.err.println("newSize = "+newSize);
//		
//		assertEquals(oldSize+1, newSize);
//		
		for (Comment comm : comments) {
			if (savedComment.getId() == comm.getId()) {
				assertEquals("User4", comm.getName());
				assertEquals("user4@gmail.com", comm.getEmail());
				assertEquals("This is my new comment on post1", comm.getContent());
			}
		}
	}

	@Test
	public void testDeleteComment() {
		Integer postId = 1;
		
		Comment comment = new Comment(0, postId, "User4", "user4@gmail.com", "This is my new comment on post1", new Timestamp(System.currentTimeMillis()));
		Comment savedComment = blogService.createComment(comment);
		
		int affectedRows = blogService.deleteComment(savedComment.getId());
		assertEquals(affectedRows, 1);
	}

}
