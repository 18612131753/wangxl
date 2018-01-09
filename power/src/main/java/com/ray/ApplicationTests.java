package com.ray;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTests {  
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());  
 
      
    @Test  
    public void contextLoads() {
    	// 最高纪录，997，40万条
    	Date dateS= new Date ();
    	
    	for(int i=0;i<100000;i++){
	        logger.trace("1111111111111I am trace log.");  
	        logger.debug("2222222222222I am debug log.");  
	        logger.warn("3333333333333I am warn log.");  
	        logger.error("4444444444I am error log.");  
    	}
    	
    	Date dateE= new Date ();
    	logger.error( "###  " +(dateE.getTime() - dateS.getTime()) );
    }  
  
}  
