package com.kakao.dy.api.coupon.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.kakao.dy.api.coupon.vo.CouponVO;

@Repository("couponDao")
public class CouponDao {
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	public void addCoupon(List<String> couponList) {
		sqlSession.insert("addCoupon", couponList); 
	}

	public HashMap updateCoupon(HashMap paramMap) {
		sqlSession.update("updateCoupon", paramMap);
		return paramMap;
	}

	public List<CouponVO> getCoupon(String user) {		
		return sqlSession.selectList("getCoupon", user);
	}

	public void useCoupon(HashMap param) {
		sqlSession.update("useCoupon", param);
	}

	public void reuseCoupon(HashMap param) {
		sqlSession.update("reuseCoupon", param);
	}

	public List<CouponVO> getExpiredCouponList() {
		return sqlSession.selectList("expiredCoupon");
	}

	public List<CouponVO> checkExpireCoupon() {
		return sqlSession.selectList("checkExpireCoupon");
	}
}
