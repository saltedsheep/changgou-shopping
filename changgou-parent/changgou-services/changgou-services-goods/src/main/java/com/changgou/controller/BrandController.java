package com.changgou.controller;

import com.changgou.goods.pojo.Brand;
import com.changgou.service.BrandService;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/brand")
@CrossOrigin   //跨域
/**
 * 跨域：A域名访问B域名的数据
 *       域名或者请求端口或者协议不一致的时候，就是跨域
 */
public class BrandController {

    @Autowired
    private BrandService brandService;


    /**
     * 分页+条件搜索
     *
     * @param brand
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}")        //因为要接收前端的数据，所以用PostMapping
    public Result<PageInfo<Brand>> findPage(@RequestBody Brand brand,
                                            @PathVariable(value = "page") Integer page,
                                            @PathVariable(value = "size") Integer size) {
        PageInfo<Brand> pageInfo = brandService.findPage(brand, page, size);
        return new Result<PageInfo<Brand>>(true, StatusCode.OK, "分页查询！", pageInfo);
    }

    /**
     * 分页查询
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Brand>> findPage(@PathVariable(value = "page") Integer page,
                                            @PathVariable(value = "size") Integer size) {
        PageInfo<Brand> pageInfo = brandService.findPage(page, size);
        return new Result<PageInfo<Brand>>(true, StatusCode.OK, "分页查询！", pageInfo);
    }

    /**
     * 条件查询
     *
     * @return
     */
    @PostMapping(value = "/search")
    public Result<List<Brand>> findList(@RequestBody Brand brand) {
        List<Brand> brands = brandService.findList(brand);
        return new Result<List<Brand>>(true, StatusCode.OK, "条件搜索查询！", brands);
    }

    /**
     * 根据ID删除成功
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable(value = "id") Integer id) {
        brandService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功！");
    }

    /**
     * 修改品牌数据
     */
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable(value = "id") Integer id, @RequestBody Brand brand) {
        //将ID给brand
        brand.setId(id);
        //调用service，对已经修改好的brand进行操作，实现修改
        brandService.update(brand);
        return new Result(true, StatusCode.OK, "修改成功！");
    }


    /**
     * 增加品牌
     *
     * @param brand
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Brand brand) {
        brandService.add(brand);
        return new Result(true, StatusCode.OK, "增加品牌成功！");
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public Result<Brand> findById(@PathVariable(value = "id") Integer id) {
        Brand byId = brandService.findById(id);
        return new Result<Brand>(true, StatusCode.OK, "根据ID查询Brandr成功！", byId);
    }

    /**
     * 查询所有品牌
     *
     * @return
     */
    @GetMapping
    public Result<List<Brand>> findAll() {
        List<Brand> brands = brandService.findAll();
        //相应结果封装
        return new Result<List<Brand>>(true, StatusCode.OK, "查询品牌集合成功！", brands);
    }


}
