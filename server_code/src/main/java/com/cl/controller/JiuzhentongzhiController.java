package com.cl.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

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

import com.cl.entity.JiuzhentongzhiEntity;
import com.cl.entity.view.JiuzhentongzhiView;

import com.cl.service.JiuzhentongzhiService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 就诊通知
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-27 15:44:15
 */
@RestController
@RequestMapping("/jiuzhentongzhi")
public class JiuzhentongzhiController {
    @Autowired
    private JiuzhentongzhiService jiuzhentongzhiService;
    
    /**
     * 定时任务：每隔5分钟重试发送失败的通知
     */
    @Scheduled(fixedRate = 5 * 60 * 1000)
    public void retryFailedNotifications() {
        try {
            // 查询所有发送失败的通知
            EntityWrapper&lt;JiuzhentongzhiEntity&gt; ew = new EntityWrapper&lt;&gt;();
            ew.eq("tongzhizhuangtai", 2); // 状态2表示发送失败
            
            List&lt;JiuzhentongzhiEntity&gt; failedList = jiuzhentongzhiService.selectList(ew);
            
            for (JiuzhentongzhiEntity tongzhi : failedList) {
                retrySendNotification(tongzhi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 重试发送通知
     */
    private void retrySendNotification(JiuzhentongzhiEntity tongzhi) {
        try {
            // 这里模拟通知重试发送逻辑
            // 实际项目中可以调用短信服务、邮件服务等
            
            // 模拟发送成功（实际项目中根据发送结果设置）
            boolean sendSuccess = true;
            
            if (sendSuccess) {
                tongzhi.setTongzhizhuangtai(1); // 更新为发送成功
                tongzhi.setTongzhishijian(new Date());
            }
            
            jiuzhentongzhiService.updateById(tongzhi);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JiuzhentongzhiEntity jiuzhentongzhi,
                                                                                                                                                    HttpServletRequest request){
                    String tableName = request.getSession().getAttribute("tableName").toString();
                                                                        if(tableName.equals("yisheng")) {
                    jiuzhentongzhi.setYishengzhanghao((String)request.getSession().getAttribute("username"));
                                    }
                                                                                                                                                                    if(tableName.equals("yonghu")) {
                    jiuzhentongzhi.setZhanghao((String)request.getSession().getAttribute("username"));
                                    }
                                                                                                                        EntityWrapper<JiuzhentongzhiEntity> ew = new EntityWrapper<JiuzhentongzhiEntity>();
                                                                                                                                                                                                                                
        
        
        PageUtils page = jiuzhentongzhiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiuzhentongzhi), params), params));
        return R.ok().put("data", page);
    }







    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,JiuzhentongzhiEntity jiuzhentongzhi,
		HttpServletRequest request){
        EntityWrapper<JiuzhentongzhiEntity> ew = new EntityWrapper<JiuzhentongzhiEntity>();

		PageUtils page = jiuzhentongzhiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiuzhentongzhi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( JiuzhentongzhiEntity jiuzhentongzhi){
       	EntityWrapper<JiuzhentongzhiEntity> ew = new EntityWrapper<JiuzhentongzhiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( jiuzhentongzhi, "jiuzhentongzhi")); 
        return R.ok().put("data", jiuzhentongzhiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JiuzhentongzhiEntity jiuzhentongzhi){
        EntityWrapper< JiuzhentongzhiEntity> ew = new EntityWrapper< JiuzhentongzhiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( jiuzhentongzhi, "jiuzhentongzhi")); 
		JiuzhentongzhiView jiuzhentongzhiView =  jiuzhentongzhiService.selectView(ew);
		return R.ok("查询就诊通知成功").put("data", jiuzhentongzhiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JiuzhentongzhiEntity jiuzhentongzhi = jiuzhentongzhiService.selectById(id);
		jiuzhentongzhi = jiuzhentongzhiService.selectView(new EntityWrapper<JiuzhentongzhiEntity>().eq("id", id));
        return R.ok().put("data", jiuzhentongzhi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JiuzhentongzhiEntity jiuzhentongzhi = jiuzhentongzhiService.selectById(id);
		jiuzhentongzhi = jiuzhentongzhiService.selectView(new EntityWrapper<JiuzhentongzhiEntity>().eq("id", id));
        return R.ok().put("data", jiuzhentongzhi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增就诊通知")
    public R save(@RequestBody JiuzhentongzhiEntity jiuzhentongzhi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(jiuzhentongzhi);
        jiuzhentongzhiService.insert(jiuzhentongzhi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增就诊通知")
    @RequestMapping("/add")
    public R add(@RequestBody JiuzhentongzhiEntity jiuzhentongzhi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(jiuzhentongzhi);
        jiuzhentongzhiService.insert(jiuzhentongzhi);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改就诊通知")
    public R update(@RequestBody JiuzhentongzhiEntity jiuzhentongzhi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jiuzhentongzhi);
        jiuzhentongzhiService.updateById(jiuzhentongzhi);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除就诊通知")
    public R delete(@RequestBody Long[] ids){
        jiuzhentongzhiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
    /**
     * 手动重试发送通知
     */
    @RequestMapping("/retry/{id}")
    @SysLog("重试发送通知")
    public R retry(@PathVariable("id") Long id){
        JiuzhentongzhiEntity tongzhi = jiuzhentongzhiService.selectById(id);
        if(tongzhi == null){
            return R.error("通知不存在");
        }
        
        try {
            // 这里模拟通知发送逻辑
            boolean sendSuccess = true; // 实际项目中根据发送结果设置
            
            if (sendSuccess) {
                tongzhi.setTongzhizhuangtai(1); // 发送成功
                tongzhi.setTongzhishijian(new Date());
                jiuzhentongzhiService.updateById(tongzhi);
                return R.ok("通知重发成功");
            } else {
                tongzhi.setTongzhizhuangtai(2); // 发送失败
                jiuzhentongzhiService.updateById(tongzhi);
                return R.error("通知重发失败");
            }
        } catch (Exception e) {
            tongzhi.setTongzhizhuangtai(2); // 发送失败
            jiuzhentongzhiService.updateById(tongzhi);
            e.printStackTrace();
            return R.error("通知重发异常：" + e.getMessage());
        }
    }
    
    /**
     * 批量重试发送失败的通知
     */
    @RequestMapping("/retryBatch")
    @SysLog("批量重试发送通知")
    public R retryBatch(@RequestBody Long[] ids){
        int successCount = 0;
        int failCount = 0;
        
        for(Long id : ids) {
            JiuzhentongzhiEntity tongzhi = jiuzhentongzhiService.selectById(id);
            if(tongzhi != null) {
                try {
                    boolean sendSuccess = true; // 实际项目中根据发送结果设置
                    if (sendSuccess) {
                        tongzhi.setTongzhizhuangtai(1);
                        tongzhi.setTongzhishijian(new Date());
                        successCount++;
                    } else {
                        failCount++;
                    }
                    jiuzhentongzhiService.updateById(tongzhi);
                } catch (Exception e) {
                    failCount++;
                    e.printStackTrace();
                }
            }
        }
        
        return R.ok("批量重试完成，成功：" + successCount + "，失败：" + failCount);
    }
    
	









}
