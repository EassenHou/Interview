/**
 * ‎Hangzhou Lejian Technology Co., Ltd.
 * Copyright (c) 2019 All Rights Reserved.
 */
package com.example.test2;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用户注册入口
 * 
 * @author Your Name
 *
 */
public class SigninController {


		/**
		 * TODO 请从这里开始补充代码
		 * <p>
		 * 测试1：138 1234 1234
		 * 结果：通过此手机号注册成功
		 * <p>
		 * 测试2：13812345abc
		 * 结果：通知本手机号无法注册，提示为非法手机号
		 * <p>
		 * 测试3：13812345678
		 * 结果：通知此手机号注册成功
		 * <p>
		 * 测试4：138 1234 5678
		 * 结果：提示此手机号已经被其他用户注册
		 * <p>
		 * 测试5：98765432101
		 * 结果：此手机号码为中国大陆非法手机号码
		 */


		public static String cellPhoneNo(String telephone) {
			LocalCache cache = LocalCache.getInstance();

			Pattern pattern = Pattern.compile("^((13[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}");
			Matcher matcher = pattern.matcher(telephone);
			boolean mark = matcher.matches();

			Pattern pattern2 = Pattern.compile("^((13[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\s\\d{4}\\s\\d{4}");
			Matcher matcher2 = pattern2.matcher(telephone);
			boolean mark2 = matcher2.matches();

			Pattern pattern3 = Pattern.compile("^\\d{11}$");
			Matcher matcher3 = pattern3.matcher(telephone);
			boolean mark3 = matcher3.matches();

			//符合334格式且合法
			if (mark2) {
				//转换格式
				String newTel = telephone.replaceAll(" ", ""); //去掉所有空格，包括首尾、中间
				//对比数据缓存
				System.out.println(newTel);
				if(cache.get(newTel)==null){
					cache.set(newTel,newTel,1000);
					return "此手机号注册成功";
				}else{
					return "提示此手机号已经被其他用户注册";
				}
				//符合普通格式
			} else if (mark) {
//			//为假
				if(cache.get(telephone)==null){
					cache.set(telephone,telephone,1000);
					System.out.println(cache.get(telephone));
					return "此手机号注册成功";

				}else{
					return "提示此手机号已经被其他用户注册";
				}
			} else if (mark3) {
				return "此手机号码为中国大陆非法手机号码";
			} else {
				return "通知本手机号无法注册，提示为非法手机号";
			}
		}
}

