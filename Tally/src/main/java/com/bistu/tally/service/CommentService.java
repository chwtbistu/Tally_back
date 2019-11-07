package com.bistu.tally.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.bistu.tally.dao.entity.Comment;
import com.bistu.tally.dao.entity.Social;
import com.bistu.tally.dao.repository.CommentRepository;
import com.bistu.tally.helper.CommentBean;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommentService {
	@Autowired
	private CommentRepository commentRepository;
	
	public CommentBean create(CommentBean bean) {
		Comment entity = CommentBean.of(bean);
		log.info("entity before save: {}", entity);
		entity = this.commentRepository.save(entity);
		log.info("entity saved: {}", entity);
		CommentBean bean2 = CommentBean.of(entity);
		return bean2;
	}
	
	public boolean delete(Long id) {
		this.commentRepository.deleteById(id);
		boolean result = this.commentRepository.existsById(id);
        log.info("comment id:{}, exist:{}", id, result);
        if(result)
            return false;
        else
            return true;
	}
}
