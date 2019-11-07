package com.bistu.tally.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bistu.tally.dao.entity.Comment;


public interface CommentRepository extends JpaRepository<Comment, Long>{
	
	public void deleteBySocialId(Long id); 
	
	public boolean existsBySocialId(Long id);
	
	public List<Comment> findAllBySocialId(Long id);
	
}
