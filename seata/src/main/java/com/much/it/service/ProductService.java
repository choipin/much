package com.much.it.service;

import com.much.it.entity.PageParam;
import com.much.it.entity.PageVO;
import com.much.it.entity.Product;

/**
 * @description:
 * @project: much
 * @date: 2020/5/4
 * @author: Wenxin
 * @version: 1.0
 */
public interface ProductService {

    Product selectByProductId(Long productId);

    Integer insertProduct(Product product);

    Integer insertBatch(Integer num);

    Integer deleteAll();

    Integer deleteById(Long productId);

    PageVO<Product> findPage(PageParam pageParam);
}
