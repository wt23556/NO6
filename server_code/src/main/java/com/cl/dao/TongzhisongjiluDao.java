package com.cl.dao;

import com.cl.entity.TongzhisongjiluEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.TongzhisongjiluView;


/**
 * 通知发送记录
 * 
 * @author 
 * @email 
 * @date 2025-03-27 15:44:15
 */
public interface TongzhisongjiluDao extends BaseMapper<TongzhisongjiluEntity> {
	
	List<TongzhisongjiluView> selectListView(@Param("ew") Wrapper<TongzhisongjiluEntity> wrapper);

	List<TongzhisongjiluView> selectListView(Pagination page,@Param("ew") Wrapper<TongzhisongjiluEntity> wrapper);
	
	TongzhisongjiluView selectView(@Param("ew") Wrapper<TongzhisongjiluEntity> wrapper);


}
