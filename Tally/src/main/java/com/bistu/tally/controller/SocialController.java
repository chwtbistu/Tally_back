package com.bistu.tally.controller;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bistu.tally.bean.ResultInfo;
import com.bistu.tally.helper.Location;
import com.bistu.tally.helper.SocialBean;
import com.bistu.tally.service.SocialService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SocialController {
	@Autowired
	private SocialService socialService;
	
	@GetMapping({"/socials/v1/{la}&{lo}", "/social/all/v1/{la}&{lo}"}) 
	public ResultInfo getAllSocial(@PathVariable("la") float la, @PathVariable("lo") float lo) {
		log.info("get requesting...");
		ResultInfo resultInfo = ResultInfo.success();
		List<SocialBean> socials = this.socialService.getByDistance(new Location(la, lo));
		log.info("socials is: {}", socials);
		resultInfo.setData(socials);
		return resultInfo;
	}
	
	@GetMapping({"/socials/v2/{userId}"})
	public ResultInfo getOneSocial(@PathVariable("userId") long userId) {
		log.info("get requesting...");
		ResultInfo resultInfo = ResultInfo.success();
		List<SocialBean> socials = this.socialService.getByUserId(userId);
		log.info("socials is: {}", socials);
		resultInfo.setData(socials);
		return resultInfo;
	}
	
	@PostMapping({"/socials"})
	public ResultInfo createSocial(@RequestBody SocialBean bean) {
		log.info("coming bean is: {}", bean);
		if(Objects.isNull(bean) || Objects.isNull(bean.getSocialContent()) ||
				Objects.isNull(bean.getLocation()) || Objects.isNull(bean.getTime())||
				Objects.isNull(bean.getUserId())) {
			ResultInfo resultInfo = ResultInfo.failure();
            resultInfo.setMesg(String.format("Attribute missing,Request Score:%s",
                    ReflectionToStringBuilder.toString(bean, ToStringStyle.MULTI_LINE_STYLE)));
            return ResultInfo.failure();
		}
		SocialBean bean2 = this.socialService.create(bean);
		ResultInfo resultInfo = ResultInfo.success();
		resultInfo.setData(bean2);
		return resultInfo;
	}
	
	@DeleteMapping({"/socials/{sid}"})
	public ResultInfo deleteSocial(@PathVariable("sid") Long id) {
		log.info("ready to delete social id:{}", id);
        if (Objects.isNull(id)) {
            ResultInfo resultInfo = ResultInfo.failure();
            resultInfo.setMesg("SID is missing");
            log.error("SID is required,....");
            return resultInfo;
        }

        boolean result = socialService.delete(id);
        if(!result) {
            ResultInfo resultInfo = ResultInfo.failure();
            resultInfo.setData("social deleted fail");
            log.error("social:{} deleted fail", id);
            return resultInfo;
        }
        else {
            ResultInfo resultInfo = ResultInfo.success();
            resultInfo.setMesg(id + " is deleted");
            log.info("social:{} is deleted....", id);
            return resultInfo;
        }
	}
	
//	@PutMapping({"/socials/{sid}"})
//	public ResultInfo praiseSocial(Long id) {
//		
//	}
}
