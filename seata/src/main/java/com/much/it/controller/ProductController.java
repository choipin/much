package com.much.it.controller;

import com.much.it.entity.PageParam;
import com.much.it.entity.PageVO;
import com.much.it.entity.Product;
import com.much.it.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @project: much
 * @date: 2020/5/4
 * @author: Wenxin
 * @version: 1.0
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{productId}")
    public Product selectByProductId(@PathVariable("productId") Long productId) {
        return productService.selectByProductId(productId);
    }

    @PutMapping("/insertProduct")
    public Integer insertProduct(@RequestBody @Validated Product product) {
        return productService.insertProduct(product);
    }

    @PutMapping("/{num}")
    public Integer insertBatch(@PathVariable("productId") Integer num) {
       return productService.insertBatch(num);
    }

    @DeleteMapping("/{deleteAll}")
    public Integer deleteAll() {
        return productService.deleteAll();
    }

    @DeleteMapping("/{productId}")
    public Integer deleteById(@PathVariable("productId") Long productId) {
        return productService.deleteById(productId);
    }
    @PostMapping("/{findPage}")
    public PageVO<Product> findPage(@RequestBody @Validated PageParam pageParam) {
      return productService.findPage(pageParam);
    }
}
