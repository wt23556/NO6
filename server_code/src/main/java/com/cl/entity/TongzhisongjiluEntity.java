package com.cl.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * 通知发送记录
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2025-03-27 15:44:15
 */
@TableName("tongzhisongjilu")
public class TongzhisongjiluEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public TongzhisongjiluEntity() {
		
	}
	
	public TongzhisongjiluEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 主键id
	 */
	@TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 关联通知id
	 */
					
	private Long tongzhiid;
	
	/**
	 * 通知编号
	 */
					
	private String tongzhibianhao;
	
	/**
	 * 接收人账号
	 */
					
	private String zhanghao;
	
	/**
	 * 手机号
	 */
					
	private String shouji;
	
	/**
	 * 发送时间
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 		
	private Date fasongshijian;
	
	/**
	 * 发送状态：0-待发送，1-发送成功，2-发送失败
	 */
					
	private Integer fasongzhuangtai;
	
	/**
	 * 失败原因
	 */
					
	private String shibaiyuanyin;
	
	/**
	 * 重试次数
	 */
					
	private Integer chongshicishu;
	
	/**
	 * 最后重试时间
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 		
	private Date zuihouchongshishijian;
	
	/**
	 * 通知内容
	 */
					
	private String tongzhineirong;
	

	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 设置：关联通知id
	 */
	public void setTongzhiid(Long tongzhiid) {
		this.tongzhiid = tongzhiid;
	}
	/**
	 * 获取：关联通知id
	 */
	public Long getTongzhiid() {
		return tongzhiid;
	}
	/**
	 * 设置：通知编号
	 */
	public void setTongzhibianhao(String tongzhibianhao) {
		this.tongzhibianhao = tongzhibianhao;
	}
	/**
	 * 获取：通知编号
	 */
	public String getTongzhibianhao() {
		return tongzhibianhao;
	}
	/**
	 * 设置：接收人账号
	 */
	public void setZhanghao(String zhanghao) {
		this.zhanghao = zhanghao;
	}
	/**
	 * 获取：接收人账号
	 */
	public String getZhanghao() {
		return zhanghao;
	}
	/**
	 * 设置：手机号
	 */
	public void setShouji(String shouji) {
		this.shouji = shouji;
	}
	/**
	 * 获取：手机号
	 */
	public String getShouji() {
		return shouji;
	}
	/**
	 * 设置：发送时间
	 */
	public void setFasongshijian(Date fasongshijian) {
		this.fasongshijian = fasongshijian;
	}
	/**
	 * 获取：发送时间
	 */
	public Date getFasongshijian() {
		return fasongshijian;
	}
	/**
	 * 设置：发送状态：0-待发送，1-发送成功，2-发送失败
	 */
	public void setFasongzhuangtai(Integer fasongzhuangtai) {
		this.fasongzhuangtai = fasongzhuangtai;
	}
	/**
	 * 获取：发送状态：0-待发送，1-发送成功，2-发送失败
	 */
	public Integer getFasongzhuangtai() {
		return fasongzhuangtai;
	}
	/**
	 * 设置：失败原因
	 */
	public void setShibaiyuanyin(String shibaiyuanyin) {
		this.shibaiyuanyin = shibaiyuanyin;
	}
	/**
	 * 获取：失败原因
	 */
	public String getShibaiyuanyin() {
		return shibaiyuanyin;
	}
	/**
	 * 设置：重试次数
	 */
	public void setChongshicishu(Integer chongshicishu) {
		this.chongshicishu = chongshicishu;
	}
	/**
	 * 获取：重试次数
	 */
	public Integer getChongshicishu() {
		return chongshicishu;
	}
	/**
	 * 设置：最后重试时间
	 */
	public void setZuihouchongshishijian(Date zuihouchongshishijian) {
		this.zuihouchongshishijian = zuihouchongshishijian;
	}
	/**
	 * 获取：最后重试时间
	 */
	public Date getZuihouchongshishijian() {
		return zuihouchongshishijian;
	}
	/**
	 * 设置：通知内容
	 */
	public void setTongzhineirong(String tongzhineirong) {
		this.tongzhineirong = tongzhineirong;
	}
	/**
	 * 获取：通知内容
	 */
	public String getTongzhineirong() {
		return tongzhineirong;
	}

}
