package com.kakao.dy.api.coupon.vo;

import java.sql.Timestamp;

public class CouponVO {
	
	private String coupon_id;
	private boolean coupon_use;
	private Timestamp created_time;
	private Timestamp expire_time;
	private String owner;
	private int coupon_count;
	
	public int getCoupon_count() {
		return coupon_count;
	}
	public void setCoupon_count(int coupon_count) {
		this.coupon_count = coupon_count;
	}
	public String getCoupon_id() {
		return coupon_id;
	}
	public void setCoupon_id(String coupon_id) {
		this.coupon_id = coupon_id;
	}
	public boolean isCoupon_use() {
		return coupon_use;
	}
	public void setCoupon_use(boolean coupon_use) {
		this.coupon_use = coupon_use;
	}
	public Timestamp getCreated_time() {
		return created_time;
	}
	public void setCreated_time(Timestamp created_time) {
		this.created_time = created_time;
	}
	public Timestamp getExpire_time() {
		return expire_time;
	}
	public void setExpire_time(Timestamp expire_time) {
		this.expire_time = expire_time;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
}
