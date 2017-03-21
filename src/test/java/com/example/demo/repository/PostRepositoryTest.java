package com.example.demo.repository;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@Sql("classpath:data-mariadb.sql")
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PostRepositoryTest {

	@Autowired
	private PostRepository postRepository;
	
	@Test
	public void test() {
		assertNotNull(postRepository);
		
		long count = postRepository.count();
		System.out.println("count = "+count);
	}

}
