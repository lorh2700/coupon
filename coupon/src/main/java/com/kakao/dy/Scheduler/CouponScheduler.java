package com.kakao.dy.Scheduler;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.kakao.dy.api.coupon.dao.CouponDao;

public class CouponScheduler {
	
	@Autowired
	CouponDao mapper;

	/**
	 * // 매시간 만료일 3일 남은 쿠폰에 대해 사용자에게 메시지 출력
	 * @throws Exception
	 */
	@Scheduled(cron="0 0 * * * *")  
	public void checkExpireCoupon() throws Exception {
		List<HashMap<String, String>> checkExpireCoupon = mapper.checkExpireCoupon();
		for (int i = 0; i < checkExpireCoupon.size(); i++) {
			String couponId = checkExpireCoupon.get(i).get("couponId");
			System.out.println("만료전까지 3일 남았습니다. couponId : " + couponId);
		}
	}
}
