package com.kakao.dy.api.coupon;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.dy.api.coupon.dao.CouponDao;
import com.kakao.dy.api.coupon.vo.CouponVO;

import net.minidev.json.JSONObject;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CouponControllerTests {  
	@Autowired
    private MockMvc mvc;
	
    @MockBean
    private CouponDao CouponMapper;

    //쿠폰 등록 
    @Test
    public void addCoupon_test() throws Exception {
    	String uri = "/coupon";

    	CouponVO cv = new CouponVO();
    	cv.setCoupon_count(5);
    	
    	ObjectMapper mapper = new ObjectMapper();
    	String param = mapper.writeValueAsString(cv);
    	
    	mvc.perform(MockMvcRequestBuilders.post(uri)
    	.contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(param) ).andExpect(status().isOk());

    }
    
    //쿠폰 등록 
    @Test
    public void addCoupon_test2() throws Exception {
    	String uri = "/coupon";

    	MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
    	.contentType(MediaType.APPLICATION_JSON_VALUE)
        .param("coupon_count", "")).andReturn();

    	int status = mvcResult.getResponse().getStatus();
    	assertEquals(200, status);
    	String content = mvcResult.getResponse().getContentAsString();

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("resultMsg", "failed! coupon_count in null");
    	assertEquals(content, jsonObject.toString());
    }
    
    @Test
    public void getCoupon_test() throws Exception {
    	String uri = "/coupon";

    	MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
    	.contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

    	int status = mvcResult.getResponse().getStatus();
    	assertEquals(200, status);
    	String content = mvcResult.getResponse().getContentAsString();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("resultMsg", "failed! check coupon");
    	assertEquals(content, jsonObject.toString());
    }
  
     @Test
     public void getCouponList_test() throws Exception {
    	String uri = "/coupon/dypark";

    	MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
    	.contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

    	int status = mvcResult.getResponse().getStatus();
    	assertEquals(200, status);
    	String content = mvcResult.getResponse().getContentAsString();
		JSONObject jsonObject = new JSONObject();
		List<HashMap<String, String>> paidCouponList = new ArrayList<HashMap<String,String>>();
		jsonObject.put("resultData",paidCouponList );
    	assertEquals(content, jsonObject.toString());
    }

    @Test
    public void useCoupon_test() throws Exception {
    	String uri = "/coupon";

    	MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
    	.contentType(MediaType.APPLICATION_JSON_VALUE)
    	.param("couponId", "test")).andReturn();

    	int status = mvcResult.getResponse().getStatus();
    	assertEquals(200, status);
    	String content = mvcResult.getResponse().getContentAsString();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("resultMsg","failed! check couponId" );
    	assertEquals(content, jsonObject.toString());
    }
    
    
    @Test
    public void cancleCoupon_test() throws Exception {
    	String uri = "/reuse_coupon";

    	MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
    	.contentType(MediaType.APPLICATION_JSON_VALUE)
    	.param("couponId", "test")).andReturn();

    	int status = mvcResult.getResponse().getStatus();
    	assertEquals(200, status);
    	String content = mvcResult.getResponse().getContentAsString();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("resultMsg","failed! check couponId" );
    	assertEquals(content, jsonObject.toString());
    }    

    @Test
    public void expiredCoupon_test() throws Exception {
    	String uri = "/expired_coupon";

    	MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
    	.contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

    	int status = mvcResult.getResponse().getStatus();
    	assertEquals(200, status);
    	String content = mvcResult.getResponse().getContentAsString();
		JSONObject jsonObject = new JSONObject();
		List<HashMap<String, String>> expireCouponList = new ArrayList<HashMap<String,String>>();
		jsonObject.put("resultData",expireCouponList);
    	assertEquals(content, jsonObject.toString());
    }
}
