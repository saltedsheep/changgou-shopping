package com.changgou.dao;

import com.changgou.goods.pojo.Brand;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

/**
 * 使用通用Mapper，可以省去数据库语句，需继承tk.mybatis.mapper.common.Mapper包
 *      增加数据，调用Mapper.insert()
 *      增加数据，调用Mapper.insertselective()
 *
 *      修改数据，调用Mapper.update(T)
 *      修改数据，调用Mapper.updateByPrimaryKey(T)
 *
 *      查询数据，根据主键查询Mapper.selectByPrimaryKey(T)
 *      查询数据，调用Mapper.select(T)
 *
 */

@Component
public interface BrandMapper extends Mapper<Brand> {

}
