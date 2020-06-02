package com.kakao.dy.api.coupon.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CouponMapper {
	
	public void addCoupon(List<String> couponList) throws Exception;
	
	public void sendCoupon(@Param("couponId")String couponId) throws Exception;
	
	public List<HashMap<String, String>> paidCouponList() throws Exception;
	
	public void useCoupon(@Param("couponId")String couponId) throws Exception;
	
	public void cancleCoupon(@Param("couponId")String couponId) throws Exception;
	
	public int validCoupon(@Param("couponId")String couponId) throws Exception;
	
	public String unusedCouponId() throws Exception;
	
	public List<HashMap<String, String>> expireCoupon() throws Exception;

	public List<HashMap<String, String>> checkExpireCoupon() throws Exception;
}
