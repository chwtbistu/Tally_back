package com.bistu.tally.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bistu.tally.dao.entity.Comment;
import com.bistu.tally.dao.entity.Social;
import com.bistu.tally.dao.repository.CommentRepository;
import com.bistu.tally.dao.repository.SocialRepository;
import com.bistu.tally.helper.CommentBean;
import com.bistu.tally.helper.Location;
import com.bistu.tally.helper.SocialBean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SocialService {
	@Autowired
	private SocialRepository socialRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	public List<SocialBean> getByUserId(Long userId) {
		List<Social> socials =  socialRepository.findSocialByUserId(userId);
		List<SocialBean> entities = new ArrayList<SocialBean>();
		for(int i = 0; i < socials.size(); i++) {
			SocialBean bean = SocialBean.of(socials.get(i));
			if(bean.getCommentsNum() > 0)
				bean.setComments(getBySocialId(bean.getSocialId()));
		}
		log.info("entities is: {}", entities);
		return entities;
	}
	
	private List<CommentBean> getBySocialId(Long id) {
		List<Comment> comments = commentRepository.findAllBySocialId(id);
		List<CommentBean> entities = new ArrayList<CommentBean>();
		for(int i = 0; i < comments.size(); i++) {
			CommentBean bean = CommentBean.of(comments.get(i));
			entities.add(bean);
		}
		return entities;
	}
	
	public List<SocialBean> getByDistance(Location location) {
		List<Social> socials =  socialRepository.findByDistance(location);
		List<SocialBean> entities = new ArrayList<SocialBean>();
		for(int i = 0; i < socials.size(); i++) {
			SocialBean bean = SocialBean.of(socials.get(i));
			bean.setComments(getBySocialId(bean.getSocialId()));
		}
		log.info("entities is: {}", entities);
		return entities;
	}
	
	public SocialBean create(SocialBean bean) {
		Social entity = SocialBean.of(bean);
		log.info("entity before save: {}", entity);
		entity = this.socialRepository.save(entity);
		log.info("entity saved: {}", entity);
		SocialBean bean2 = SocialBean.of(entity);
		return bean2;
	}
	
	public boolean delete(Long id) {
		this.socialRepository.deleteById(id);
		this.commentRepository.deleteBySocialId(id);
		boolean result = this.socialRepository.existsById(id) && this.commentRepository.existsBySocialId(id);
        log.info("Social id:{}, exist:{}", id, result);
        if(result)
            return false;
        else
            return true;
	}
	
}
