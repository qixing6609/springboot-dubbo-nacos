package com.qx.consumer.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.qx.consumer.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 *
 */
@RestController
@RequestMapping("/demo")
public class DemoController {
	private static Logger logger = LoggerFactory.getLogger(DemoController.class);
	@Autowired 
	private DemoService demoService;

	@NacosValue(value = "${dubbo.application.name}", autoRefreshed = true)
	private String dubboApplicationName;


	/**
	 *
	 * @param postData
	 * @return
	 */
	@RequestMapping(value = "/demo", method = RequestMethod.POST)
	@ResponseBody
	public String demo(@RequestBody String postData){
			return demoService.demo().toString();
	}
	/**
	 *
	 * @param postData
	 * @return
	 */
	@RequestMapping(value = "/nacosConfig", method = RequestMethod.POST)
	@ResponseBody
	public String nacosConfig(@RequestBody String postData){
			return dubboApplicationName;
	}
}
