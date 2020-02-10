package com.changgou.service.impl;

import com.changgou.dao.BrandMapper;
import com.changgou.goods.pojo.Brand;
import com.changgou.service.BrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;


    /*
     * 查询所有
     */
    @Override
    public List<Brand> findAll() {
        /**
         * 查询所有，用Mapper.selectAll(),这里brandMapper继承自Mapper
         */
        return brandMapper.selectAll();
    }

    @Override
    public Brand findById(Integer id) {
        /**
         * 根据ID查询--》通用Mapper.selectByPrimaryKey(Object)   根据主键查询
         */
        return brandMapper.selectByPrimaryKey(id);
    }

    /**
     * 增加品牌
     *
     * @param brand
     */
    @Override
    public void add(Brand brand) {
        /**
         * 使用通用Mapper.insertSelective(T)实现增加
         */
        brandMapper.insertSelective(brand);
    }

    /**
     * 更新修改
     *
     * @param brand
     */
    @Override
    public void update(Brand brand) {
        /**
         * 使用通用Mapper.updateByPrimaryKeySelective(T)实现修改
         */
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    /**
     * 根据ID删除
     *
     * @param id
     */
    @Override
    public void delete(Integer id) {
        /**
         * 使用通用Mapper.deleteByPrimaryKey(T)实现删除
         */
        brandMapper.deleteByPrimaryKey(id);
    }

    /**
     * 条件查询
     *
     * @param brand
     * @return
     */
    @Override
    public List<Brand> findList(Brand brand) {
        //调用构建的条件
        Example example = createExample(brand);

        return brandMapper.selectByExample(example);
    }

    /**
     * 分页
     *
     * @param page:当前页
     * @param size:页面显示的最大记录数
     * @return
     */
    @Override
    public PageInfo<Brand> findPage(Integer page, Integer size) {
        /**
         * 分页实现，调用PageHelper.startPage(page,size);后面的查询需为查询集合
         * page:当前页
         * size:每页显示多少条
         */
        PageHelper.startPage(page, size);

        //查询集合
        List<Brand> brands = brandMapper.selectAll();

        //封装一个PageInfo--》list集合
        return new PageInfo<Brand>(brands);
    }

    /**
     * 分页+条件搜索：先实现分页，在按条件查询
     *
     * @param brand:搜索条件
     * @param page:当前页
     * @param size:页面显示的最大记录数
     * @return
     */
    @Override
    public PageInfo<Brand> findPage(Brand brand, Integer page, Integer size) {
        //先分页
        PageHelper.startPage(page, size);

        //搜索数据  name不为空，根据name模糊查询，letter不为空，则根据letter查询
        Example example = createExample(brand);
        List<Brand> brands = brandMapper.selectByExample(example);
        return new PageInfo<Brand>(brands);
    }

    /**
     * 条件构建
     *
     * @param brand
     * @return
     */
    public Example createExample(Brand brand) {
        //自定义条件搜索对象
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();      //条件构造器

        if (brand != null) {
            //brand.name!=null根据名字模糊搜索where name like '%华%'
            if (!StringUtils.isEmpty(brand.getName())) {
                /**
                 * 1、brand的属性名（Ctrl进Brand里查看）
                 * 2、占位符参数，搜索条件
                 */
                criteria.andLike("name", "%" + brand.getName() + "%");
            }
            //brand.letter!=null根据首字母搜索 and letter='H'
            if (!StringUtils.isEmpty(brand.getLetter())) {
                criteria.andEqualTo("letter", brand.getLetter());
            }
        }
        return example;
    }

}
