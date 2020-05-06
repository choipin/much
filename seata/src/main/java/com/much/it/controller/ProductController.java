package com.much.it.controller;

import com.much.it.entity.PageParam;
import com.much.it.entity.PageVO;
import com.much.it.entity.Product;
import com.much.it.service.ProductService;
import com.ofwiki.encrypt.annotations.EncryptDecrypt;
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

    @EncryptDecrypt
    @GetMapping("/{productId}")
    public Product selectByProductId(@PathVariable("productId") Long productId) {
        return productService.selectByProductId(productId);
    }

    @EncryptDecrypt
    @PutMapping("/insertProduct")
    public Integer insertProduct(@RequestBody @Validated Product product) {
        return productService.insertProduct(product);
    }

    @EncryptDecrypt
    @PutMapping("/{num}")
    public Integer insertBatch(@PathVariable("num") Integer num) {
       return productService.insertBatch(num);
    }

    @EncryptDecrypt
    @DeleteMapping("/{deleteAll}")
    public Integer deleteAll() {
        return productService.deleteAll();
    }

    @EncryptDecrypt
    @DeleteMapping("/{productId}")
    public Integer deleteById(@PathVariable("productId") Long productId) {
        return productService.deleteById(productId);
    }

    @EncryptDecrypt
    @PostMapping("/findPage")
    public PageVO<Product> findPage(@RequestBody @Validated PageParam pageParam) {
      return productService.findPage(pageParam);
    }
}
