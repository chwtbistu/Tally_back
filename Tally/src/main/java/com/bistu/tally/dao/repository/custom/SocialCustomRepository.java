package com.bistu.tally.dao.repository.custom;

import java.util.List;

import com.bistu.tally.dao.entity.Social;
import com.bistu.tally.helper.Location;

public interface SocialCustomRepository{
	
	public List<Social> findByDistance(Location location);

}
