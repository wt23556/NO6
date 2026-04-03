package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.TongzhisongjiluEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.TongzhisongjiluView;


/**
 * 通知发送记录
 *
 * @author 
 * @email 
 * @date 2025-03-27 15:44:15
 */
public interface TongzhisongjiluService extends IService<TongzhisongjiluEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<TongzhisongjiluView> selectListView(Wrapper<TongzhisongjiluEntity> wrapper);
   	
   	TongzhisongjiluView selectView(@Param("ew") Wrapper<TongzhisongjiluEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<TongzhisongjiluEntity> wrapper);
   	
   
}
