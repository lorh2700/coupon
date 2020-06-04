package com.kakao.dy.api.coupon;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.kakao.dy.api.coupon.dao.CouponDao;
import com.kakao.dy.api.coupon.service.CouponService;
import com.kakao.dy.api.coupon.vo.CouponVO;

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
	public @ResponseBody ResponseEntity<Object> addCoupon1(@RequestBody CouponVO cvo) throws Exception {
		return couponService.couponPost(cvo);		
		
	}
	
	/**
	 * 생성된 쿠폰중 하나를 사용자에게 지급하는 API를 구현하세요
	 */
	@RequestMapping(value="/coupon",method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<Object> sendCoupon(HttpServletRequest request) throws Exception {		
		String user = "dypark";
		return couponService.couponGet(user);
	}
	
	
	/**
	 * 사용자에게 지급된 쿠폰을 조회하는 API를 구현하세요.
	 */
	@RequestMapping(value="/coupon/{userId}",method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<Object> paidCuponList(@PathVariable String userId) throws Exception {
		return couponService.couponListWithUser(userId);
	}
	
	
	/**
	 * 지급된 쿠폰중 하나를 사용하는 API를 구현하세요. (쿠폰 재사용은 불가)
	 */
	@RequestMapping(value="/coupon",method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<Object> useCoupon(@RequestBody CouponVO coupon) throws Exception {
		String user = "dypark";
		return couponService.couponUse(user, coupon);
	}
	
	
	/**
	 *  지급된 쿠폰중 하나를 사용 취소하는 API를 구현하세요. (취소된 쿠폰 재사용 가능)
	 */
	@RequestMapping(value="/reuse_coupon",method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<Object> reuseCoupon(@RequestBody CouponVO coupon) throws Exception {
		String user = "dypark";
		return couponService.reuseCoupon(user, coupon);
	}	
	
	/** expire 
	 * 발급된 쿠폰중 당일 만료된 전체 쿠폰 목록을 조회하는 API를 구현하세요.
	 * **/
	@RequestMapping(value="/expired_coupon",method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<Object> expiredCoupon() throws Exception {
		return couponService.expiredCoupon();
	}
	

	/**
	 * // 매시간 만료일 3일 남은 쿠폰에 대해 사용자에게 메시지 출력
	 * @throws Exception
	 */
	@Scheduled(cron="1 * * * * *")  
	public void checkExpireCoupon() throws Exception {
		List<CouponVO> checkExpireCoupon = mapper.checkExpireCoupon();
		for (CouponVO coupon : checkExpireCoupon) {
			System.out.println(coupon.getOwner() + " 님의 쿠폰 " + coupon.getCoupon_id() +" 이 만료전까지 3일 남았습니다. ");
		}
	}
	
}
