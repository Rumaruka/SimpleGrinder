package com.rumaruka.simplegrinder.Proxy;

import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.common.FMLLog;

public class CommonProxy {
	
	private static final Logger logger = FMLLog.getLogger();
	
	public void Renders(){
		
	}
	
	public void info(String s){
		logger.info(s);
	}
	
	
	public void error(String s){
		logger.error(s);
	}
}
