package com.kakao.dy.api.coupon.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kakao.dy.api.coupon.dao.CouponDao;
import com.kakao.dy.api.coupon.vo.CouponVO;
import com.kakao.dy.util.CouponUtil;

@Service("couponService")
public class CouponService {
	
	@Autowired
	private CouponDao couponDao;
	
	@Autowired
	private CouponUtil cUtil;
	
	@Transactional(value="transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseEntity<Object> couponPost(CouponVO cvo) throws Exception {
		
		
		ArrayList<String> couponList = new ArrayList<String>();
		
		for(int i = 0; i<cvo.getCoupon_count(); i++) {
			couponList.add(cUtil.generateCoupon(i));
			System.out.println(cUtil.generateCoupon(i));
		}
		
		couponDao.addCoupon(couponList);
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@Transactional(value="transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseEntity<Object> couponGet(String user) {
		
		HashMap paramMap = new HashMap<String, String>();
		paramMap.put("user_name", user);
		
		HashMap resultMap = couponDao.updateCoupon(paramMap);
		
		return new ResponseEntity<Object>(resultMap, HttpStatus.OK);
	}

	public ResponseEntity<Object> couponListWithUser(String user) {
		
		List<CouponVO> couponList = couponDao.getCoupon(user);
		
		return new ResponseEntity<Object>(couponList, HttpStatus.OK);
	}

	public ResponseEntity<Object> couponUse(String user, CouponVO coupon) {
		
		HashMap param = new HashMap<String, String>();
		param.put("user", user);
		param.put("coupon_id", coupon.getCoupon_id());
		
		couponDao.useCoupon(param);
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	public ResponseEntity<Object> reuseCoupon(String user, CouponVO coupon) {
		
		HashMap param = new HashMap<String, String>();
		param.put("user", user);
		param.put("coupon_id", coupon.getCoupon_id());
		
		couponDao.reuseCoupon(param);
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	public ResponseEntity<Object> expiredCoupon() {
		
		List<CouponVO> couponList = couponDao.getExpiredCouponList();
		
		return new ResponseEntity<Object>(couponList, HttpStatus.OK);
	}

}
