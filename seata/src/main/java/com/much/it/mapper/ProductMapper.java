package com.much.it.mapper;

import com.much.it.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 * @project: much
 * @date: 2020/5/4
 * @author: Wenxin
 * @version: 1.0
 */
@Mapper
@Component
public interface ProductMapper {

    Product selectByProductId(Long productId);

    Integer insertProduct(Product product);

    Integer insertBatch(List<Product> products);

    Integer deleteAll();

    Integer deleteById(Long productId);

    List<Product> findAll();
}
