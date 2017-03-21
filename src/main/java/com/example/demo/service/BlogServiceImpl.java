package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Comment;
import com.example.demo.model.Post;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.PostRepository;

@Service
@Transactional
public class BlogServiceImpl implements BlogService {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
//	@Autowired
//	private DSLContext dsl;

	@Override
	public Post createPost(Post post) {
		Post postSaved = postRepository.save(post);
		return postSaved;
		
//		PostsRecord postsRecord = dsl.insertInto(POSTS)
//				.set(POSTS.TITLE, post.getTitle())
//				.set(POSTS.CONTENT, post.getContent())
//				.set(POSTS.CREATED_ON, post.getCreatedOn())
//				.returning(POSTS.ID)
//				.fetchOne();
//			
//		post.setId(postsRecord.getId());
//		return post;
	}

	@Override
	public List<Post> getPosts() {
		List<Post> posts = postRepository.findAll();
		return posts ;
		
//		List<Post> posts = new ArrayList<>();		
//		Result<Record> result = dsl.select().from(POSTS).fetch();
//		for (Record r : result) {
//		    posts.add(getPostEntity(r));
//		}
//		return posts ;
	}

	@Override
	public Post getPost(Integer postId) {
		Post post = postRepository.findOne(postId);
		return post;
		
//		Record record = dsl.select().from(POSTS).where(POSTS.ID.eq(postId)).fetchOne();
//		if (record != null) {
//			Result<Record> commentRecords = dsl.select().from(COMMENTS).where(COMMENTS.POST_ID.eq(postId)).fetch();
//			
//			Post post = getPostEntity(record);
//			for (Record r : commentRecords) {
//				post.addComment(getCommentEntity(r, post));
//			}
//			return post;
//		}
//		return null;
	}

	@Override
	public Comment createComment(Comment comment) {
		
		Post post = postRepository.findOne(comment.getPost().getId());
		comment.setPost(post);
		
		Comment commentSaved = commentRepository.save(comment);
		return commentSaved;
		
//		---------------------------------
		
//		CommentsRecord commentsRecord = dsl.insertInto(COMMENTS)
//				.set(COMMENTS.POST_ID, comment.getPost().getId())
//				.set(COMMENTS.NAME, comment.getName())
//				.set(COMMENTS.EMAIL, comment.getEmail())
//				.set(COMMENTS.CONTENT, comment.getContent())
//				.set(COMMENTS.CREATED_ON, comment.getCreatedOn())
//				.returning(COMMENTS.ID)
//				.fetchOne();
//			
//		comment.setId(commentsRecord.getId());
//		return comment;
	}

	@Override
	public int deleteComment(Integer commentId) {
		commentRepository.delete(commentId);
		return 1;
		
//		return dsl.deleteFrom(COMMENTS).where(COMMENTS.ID.equal(commentId)).execute();
	}

//	private Post getPostEntity(Record r){
//		Integer id = r.getValue(POSTS.ID, Integer.class);
//	    String title = r.getValue(POSTS.TITLE, String.class);
//	    String content = r.getValue(POSTS.CONTENT, String.class);
//	    Timestamp createdOn = r.getValue(POSTS.CREATED_ON, Timestamp.class);
//	    return new Post(id, title, content, createdOn);
//	}
//	
//	private Comment getCommentEntity(Record r, Post post) {
//		Integer id = r.getValue(COMMENTS.ID, Integer.class);
//		//Integer postId = r.getValue(COMMENTS.POST_ID, Integer.class);
//		String name = r.getValue(COMMENTS.NAME, String.class);
//		String email = r.getValue(COMMENTS.EMAIL, String.class);
//		String content = r.getValue(COMMENTS.CONTENT, String.class);
//		Timestamp createdOn = r.getValue(COMMENTS.CREATED_ON, Timestamp.class);
//		return new Comment(id, post, name, email, content, createdOn);
//	}

}
