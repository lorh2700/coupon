package com.kakao.dy.api.coupon;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.kakao.dy.api.coupon.dao.CouponDao;
import com.kakao.dy.api.coupon.service.CouponService;

@RestController
public class CouponController {
	
	@Autowired
	CouponService couponService;
	
	@Autowired
	CouponDao mapper;
	
	/**
	 *  쿠폰 등록 
	 * 랜덤한 코드의 쿠폰을 N개 생성하여 데이터베이스에 보관하는 API를 구현하세요.*/	
	@RequestMapping(value="/coupon", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<Object> addCoupon(@RequestBody int count) throws Exception {
		
		return couponService.couponPost(count);		
		
	}
	
	/**
	 * send coupon 생성된 쿠폰중 하나를 사용자에게 지급하는 API를 구현하세요
	 */
	@RequestMapping(value="/coupon",method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<Object> sendCoupon(HttpServletRequest request) throws Exception {
		
		return null;
	}
	
	
	/**
	 * paid coupon 사용자에게 지급된 쿠폰을 조회하는 API를 구현하세요.
	 */
	@RequestMapping(value="/coupon/{userId}",method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<Object> paidCuponList(@PathVariable String userId) throws Exception {

		return null;
	}
	
	
	/**
	 * use coupon 지급된 쿠폰중 하나를 사용하는 API를 구현하세요. (쿠폰 재사용은 불가)
	 */
	@RequestMapping(value="/coupon",method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<Object> useCoupon(@RequestBody String coupon) throws Exception {
		return null;
	}
	
	
	/**
	 * cancleCoupon
	 *  지급된 쿠폰중 하나를 사용 취소하는 API를 구현하세요. (취소된 쿠폰 재사용 가능)
	 */
	@RequestMapping(value="/coupon",method=RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<Object> cancleCoupon(HttpServletRequest request) throws Exception {
		return null;
	}	
	
	/** expire 
	 * 발급된 쿠폰중 당일 만료된 전체 쿠폰 목록을 조회하는 API를 구현하세요.
	 * **/
	@RequestMapping(value="/expired_coupon",method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<Object> expiredCoupon(@RequestBody String coupon) throws Exception {
		return null;
	}
	
}
