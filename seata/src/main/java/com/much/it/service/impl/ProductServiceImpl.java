package com.much.it.service.impl;

import com.much.it.entity.PageParam;
import com.much.it.entity.PageVO;
import com.much.it.entity.Product;
import com.much.it.mapper.ProductMapper;
import com.much.it.service.ProductService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @description:
 * @project: much
 * @date: 2020/5/4
 * @author: Wenxin
 * @version: 1.0
 */
@Service
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product selectByProductId(Long productId) {
        return productMapper.selectByProductId(productId);
    }

    @Override
    @GlobalTransactional
    public Integer insertProduct(Product product) {
        return productMapper.insertProduct(product);
    }

    @Override
    @GlobalTransactional
    public Integer insertBatch(Integer num) {
        List<Product> products = IntStream.range(0, num).boxed().map(integer -> Product.builder().createTime(new Date())
                .productDescription(UUID.randomUUID().toString()).productIcon(UUID.randomUUID().toString())
                .productPrice(new BigDecimal(new Random().nextDouble())).productStatus(new Random().nextInt(1000))
                .productStock(new Random().nextInt(3000)).productType(UUID.randomUUID().toString())
                .updateTime(new Date()).build()).collect(Collectors.toList());
        return productMapper.insertBatch(products);
    }

    @Override
    @GlobalTransactional
    public Integer deleteAll() {
        return productMapper.deleteAll();
    }

    @Override
    @GlobalTransactional
    public Integer deleteById(Long productId) {
        return productMapper.deleteById(productId);
    }

    @Override
    public PageVO<Product> findPage(PageParam pageParam) {
        List<Product> all = productMapper.findAll();
        List<Product> list = Optional.ofNullable(all).orElse(Collections.singletonList(new Product())).stream()
                .skip(pageParam.getPageSize() * (pageParam.getPageNum() - 1)).limit(pageParam.getPageSize()).collect(Collectors.toList());
        Integer total = all==null?0:all.size();
        return new PageVO<>(pageParam.getPageNum(),total,list);
    }
}
