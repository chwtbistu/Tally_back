package com.bistu.tally.dao.repository.custom;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.bistu.tally.dao.entity.Social;
import com.bistu.tally.helper.Location;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SocialCustomRepositoryImpl implements SocialCustomRepository{

	@Autowired
    private EntityManager entityManager;
	
	public List<Social> findByDistance(Location location) {
		String jpql = "SELECT entity from Social entity where " + 
				"round(6378.138*2*asin(sqrt(pow(sin( ("+ location.getLatitude() + 
				"*pi()/180-entity.latitude*pi()/180)/2),2)+cos(" + location.getLatitude()
				+ "*pi()/180)*cos(entity.latitude*pi()/180)* pow(sin( (" +
				location.getLongitude() + "*pi()/180-entity.longitude*pi()/180)/2),2)))*1000) < 1000 " + 
				"ORDER BY time";
		log.info("select query is: {}", jpql);
        List<Social> entities = this.entityManager.createQuery(String.format(jpql, location))
                .getResultList();

        log.info("entities:{}", entities);
        return entities;
	}

	
}