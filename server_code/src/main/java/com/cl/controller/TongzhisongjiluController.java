package com.cl.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import com.cl.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cl.annotation.IgnoreAuth;
import com.cl.annotation.SysLog;

import com.cl.entity.TongzhisongjiluEntity;
import com.cl.entity.view.TongzhisongjiluView;

import com.cl.service.TongzhisongjiluService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;

/**
 * 通知发送记录
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-27 15:44:15
 */
@RestController
@RequestMapping("/tongzhisongjilu")
public class TongzhisongjiluController {
    @Autowired
    private TongzhisongjiluService tongzhisongjiluService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,TongzhisongjiluEntity tongzhisongjilu,
                                                                                                                                                    HttpServletRequest request){
                    EntityWrapper<TongzhisongjiluEntity> ew = new EntityWrapper<TongzhisongjiluEntity>();
                                                                                                                                                                                                        
        
        
        PageUtils page = tongzhisongjiluService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, tongzhisongjilu), params), params));
        return R.ok().put("data", page);
    }










    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,TongzhisongjiluEntity tongzhisongjilu,
		HttpServletRequest request){
        EntityWrapper<TongzhisongjiluEntity> ew = new EntityWrapper<TongzhisongjiluEntity>();

		PageUtils page = tongzhisongjiluService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, tongzhisongjilu), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( TongzhisongjiluEntity tongzhisongjilu){
       	EntityWrapper<TongzhisongjiluEntity> ew = new EntityWrapper<TongzhisongjiluEntity>();
      	ew.allEq(MPUtil.allEQMapPre( tongzhisongjilu, "tongzhisongjilu")); 
        return R.ok().put("data", tongzhisongjiluService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(TongzhisongjiluEntity tongzhisongjilu){
        EntityWrapper< TongzhisongjiluEntity> ew = new EntityWrapper< TongzhisongjiluEntity>();
 		ew.allEq(MPUtil.allEQMapPre( tongzhisongjilu, "tongzhisongjilu")); 
		TongzhisongjiluView tongzhisongjiluView =  tongzhisongjiluService.selectView(ew);
		return R.ok("查询通知发送记录成功").put("data", tongzhisongjiluView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        TongzhisongjiluEntity tongzhisongjilu = tongzhisongjiluService.selectById(id);
		tongzhisongjilu = tongzhisongjiluService.selectView(new EntityWrapper<TongzhisongjiluEntity>().eq("id", id));
        return R.ok().put("data", tongzhisongjilu);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        TongzhisongjiluEntity tongzhisongjilu = tongzhisongjiluService.selectById(id);
		tongzhisongjilu = tongzhisongjiluService.selectView(new EntityWrapper<TongzhisongjiluEntity>().eq("id", id));
        return R.ok().put("data", tongzhisongjilu);
    }
    




    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增通知发送记录")
    public R save(@RequestBody TongzhisongjiluEntity tongzhisongjilu, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(tongzhisongjilu);
        tongzhisongjiluService.insert(tongzhisongjilu);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增通知发送记录")
    @RequestMapping("/add")
    public R add(@RequestBody TongzhisongjiluEntity tongzhisongjilu, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(tongzhisongjilu);
        tongzhisongjiluService.insert(tongzhisongjilu);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改通知发送记录")
    public R update(@RequestBody TongzhisongjiluEntity tongzhisongjilu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(tongzhisongjilu);
        tongzhisongjiluService.updateById(tongzhisongjilu);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除通知发送记录")
    public R delete(@RequestBody Long[] ids){
        tongzhisongjiluService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
