package com.cl.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cl.utils.PageUtils;
import com.cl.utils.Query;


import com.cl.dao.TongzhisongjiluDao;
import com.cl.entity.TongzhisongjiluEntity;
import com.cl.service.TongzhisongjiluService;
import com.cl.entity.view.TongzhisongjiluView;

@Service("tongzhisongjiluService")
public class TongzhisongjiluServiceImpl extends ServiceImpl<TongzhisongjiluDao, TongzhisongjiluEntity> implements TongzhisongjiluService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<TongzhisongjiluEntity> page = this.selectPage(
                new Query<TongzhisongjiluEntity>(params).getPage(),
                new EntityWrapper<TongzhisongjiluEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<TongzhisongjiluEntity> wrapper) {
		  Page<TongzhisongjiluView> page =new Query<TongzhisongjiluView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<TongzhisongjiluView> selectListView(Wrapper<TongzhisongjiluEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public TongzhisongjiluView selectView(Wrapper<TongzhisongjiluEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
