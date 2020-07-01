package com.springboot.v1.configure.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: OpLog
 * @Description: 标注是否需要织入外部业务请求
 * @author dyl
 * @date 2019年12月14日 上午10:48:51
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface OpLog {

  /**
   * @Title: system     
   * @Description: 系统    
   * @return       
   */
  String system() default "";

  /**
   * @Title: module     
   * @Description: 系统对应模块    
   * @return       
   */
  String module() default "";

  /**
   * @Title: menu     
   * @Description: 系统对应菜单    
   * @return       
   */
  String menu() default "";

  /**
   * @Title: function     
   * @Description: 功能方法
   * @return       
   */
  String function() default "";

  /**
   * @Title: content     
   * @Description: 内容信息    
   * @return       
   */
  String content() default "";
}
