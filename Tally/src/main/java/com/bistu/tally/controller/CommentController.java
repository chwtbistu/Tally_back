package com.bistu.tally.controller;

import java.util.Objects;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bistu.tally.bean.ResultInfo;
import com.bistu.tally.helper.CommentBean;
import com.bistu.tally.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@PostMapping({"/comments"})
	public ResultInfo createComment(@RequestBody CommentBean bean) {
		log.info("coming bean is: {}", bean);
		if(Objects.isNull(bean) || Objects.isNull(bean.getCommentContent()) ||
				Objects.isNull(bean.getTime()) || Objects.isNull(bean.getUserId())||
				Objects.isNull(bean.getSocialId())) {
			ResultInfo resultInfo = ResultInfo.failure();
            resultInfo.setMesg(String.format("Attribute missing,Request Score:%s",
                    ReflectionToStringBuilder.toString(bean, ToStringStyle.MULTI_LINE_STYLE)));
            return ResultInfo.failure();
		}
		CommentBean bean2 = commentService.create(bean);
		log.info("bean is: {}", bean2);
		ResultInfo resultInfo = ResultInfo.success();
		resultInfo.setData(bean2);
		return resultInfo;
	}
}
