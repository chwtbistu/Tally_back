package com.bistu.tally.helper;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.bistu.tally.dao.entity.Comment;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Data
@Component
public class CommentBean {
	private Long socialId;
	private Long userId;
	private String commentContent;
	private Date time;
	private Long commentId;
	
	public static CommentBean of(Comment entity) {
		log.info("entity is: {}", entity);
		CommentBean bean = new CommentBean();
		bean.setUserId(entity.getUserId());
		bean.setSocialId(entity.getSocialId());
		bean.setCommentId(entity.getId());
		bean.setCommentContent(entity.getCommentContent());
		bean.setTime(entity.getTime());
		log.info("bean is: {}", bean);
		return bean;
	}
	
	public static Comment of(CommentBean bean) {
		log.info("bean is: {}", bean);
		Comment entity = new Comment();
		entity.setId(bean.getSocialId());
		entity.setSocialId(bean.getSocialId());
		entity.setUserId(bean.getUserId());
		entity.setTime(bean.getTime());
		entity.setCommentContent(bean.getCommentContent());
		return entity;
	}
}
