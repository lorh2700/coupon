package com.kakao.dy.util;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Random;

import org.slf4j.Logger;

@Component
public class CouponUtil {
	
		private static final Logger logger = LoggerFactory.getLogger(CouponUtil.class);
		
		private final char COUPON_SPACER_CHAR = '-';
		private final int COUPON_PREFIX = 5;
		private final int COUPON_MIDDLE = 11;
		
	    private static int certCharLength = 19;
	    private final static char[] characterTable = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 
			                                            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 
			                                            'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };
	    
	    public String generateCoupon() {
	        Random random = new Random(System.currentTimeMillis());
	        int tablelength = characterTable.length;
	        StringBuffer buf = new StringBuffer();
	        
	        for(int i = 0; i < certCharLength; i++) {
	        	if( i == COUPON_PREFIX || i == COUPON_MIDDLE)
	        		buf.append(COUPON_SPACER_CHAR);
	        			
	            buf.append(characterTable[random.nextInt(tablelength)]);
	        }
	        
	        return buf.toString();
	    }

}
