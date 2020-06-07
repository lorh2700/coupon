package com.kakao.dy.Scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kakao.dy.api.coupon.dao.CouponDao;
import com.kakao.dy.api.coupon.vo.CouponVO;

@Component
public class CouponScheduler {
	
	
	@Autowired
	CouponDao mapper;
	
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
