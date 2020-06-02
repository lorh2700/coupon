package com.kakao.dy.api.coupon.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kakao.dy.api.coupon.dao.CouponMapper;
import com.kakao.dy.util.CouponUtil;

@Service("couponService")
public class CouponService {
	
	@Autowired
	private CouponMapper mapper;
	
	@Autowired
	private CouponUtil cUtil;
	
	@Transactional(value="transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseEntity<Object> couponPost(int count) throws Exception {
		
		
		ArrayList<String> couponList = new ArrayList<String>();
		
		for(int i = 0; i<count; i++) {
			couponList.add(cUtil.generateCoupon());
		}
		
		mapper.addCoupon(couponList);
		
		return new ResponseEntity<Object>(null, HttpStatus.OK);
	}

}
