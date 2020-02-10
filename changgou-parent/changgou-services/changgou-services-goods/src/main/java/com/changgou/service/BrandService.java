package com.changgou.service;

import com.changgou.goods.pojo.Brand;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface BrandService {
    /**
     * 查询所有
     *
     * @return
     */
    List<Brand> findAll();

    /**
     * 根据ID查询，ID是主键
     *
     * @param id
     * @return
     */
    Brand findById(Integer id);

    /**
     * 增加品牌
     *
     * @param brand
     */
    void add(Brand brand);

    /**
     * 修改品牌信息
     *
     * @param brand
     */
    void update(Brand brand);

    /**
     * 根据ID删除
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * 根据品牌信息多条件搜索
     *
     * @param brand
     * @return
     */
    List<Brand> findList(Brand brand);

    /**
     * 分页
     *
     * @param page:当前页
     * @param size:页面显示的最大记录数
     * @return
     */
    PageInfo<Brand> findPage(Integer page, Integer size);

    /**
     * 分页+条件搜索
     *
     * @param brand:搜索条件
     * @param page:当前页
     * @param size:页面显示的最大记录数
     * @return
     */
    PageInfo<Brand> findPage(Brand brand, Integer page, Integer size);
}
