/*
*
* OpLog.java
* Copyright(C) 2019-2025 北京用友政务软件股份有限公司
* @date 2019-12-14
*/
package com.springboot.v1.configure.log;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * @ClassName: OperateLog
 * @Description: 日志实体
 * @author dyl
 * @date 2019年12月14日 下午1:52:15
 */
@ApiModel(value="com.ufgov.pvdf.common.log.OperateLog")
@Data
public class OperateLog implements Serializable {
    /**
     * 主键
     */
    @ApiModelProperty(value="logId")
    private String logId;

    /**
     * 系统编码
     */
    @ApiModelProperty(value="sysCode系统编码")
    private String sysCode;

    /**
     * 模块编码
     */
    @ApiModelProperty(value="moduleCode模块编码")
    private String moduleCode;

    /**
     * 菜单编码
     */
    @ApiModelProperty(value="menuCode菜单编码")
    private String menuCode;

    /**
     * 功能ID
     */
    @ApiModelProperty(value="funcId功能ID")
    private String funcId;

    /**
     * 单位ID
     */
    @ApiModelProperty(value="coCode单位ID")
    private String coCode;

    /**
     * 人员ID
     */
    @ApiModelProperty(value="userCode人员ID")
    private String userCode;

    /**
     * 姓名
     */
    @ApiModelProperty(value="userName 姓名")
    private String userName;

    /**
     * 操作状态1 成功/ 0失败
     */
    @ApiModelProperty(value="status操作状态1 成功/ 0失败")
    private String status;

    /**
     * Ip地址
     */
    @ApiModelProperty(value="ipAddress Ip地址")
    private String ipAddress;

    /**
     * Mac地址
     */
    @ApiModelProperty(value="macAddress Mac地址")
    private String macAddress;

    /**
     * 日志信息
     */
    @ApiModelProperty(value="message日志信息")
    private String message;

    /**
     * 记录时间yyyy-MM-dd HH:mm:ss
     */
    @ApiModelProperty(value="recordTime记录时间yyyy-MM-dd HH:mm:ss")
    private String recordTime;

    /**
     * url
     */
    @ApiModelProperty(value="url")
    private String url;

    /**
     * region
     */
    @ApiModelProperty(value="region")
    private String region;

    /**
     * 区划码
     */
    @ApiModelProperty(value="agencyCode")
    private String agencyCode;

    /**
     * 区划名称
     */
    @ApiModelProperty(value="agencyName")
    private String agencyName;

    /**
     * 用户角色
     */
    @ApiModelProperty(value="userRole")
    private String userRole;

    /**
     * transdate
     */
    @ApiModelProperty(value="transDate")
    private String transDate;

    private static final long serialVersionUID = 1L;
}