package com.kakao.dy.api.coupon.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CouponDao {
	
	public void addCoupon(List<String> couponList) throws Exception;

	public List<HashMap<String, String>> checkExpireCoupon();
	
}
