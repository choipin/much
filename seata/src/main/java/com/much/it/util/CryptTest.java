package com.much.it.util;

import com.alibaba.fastjson.JSON;
import com.much.it.entity.PageParam;

/**
 * @description:
 * @project: much
 * @date: 2020/5/6
 * @author: Wenxin
 * @version: 1.0
 */
public class CryptTest {
    public static void main(String[] args) {
        //test1();
        test2();
    }

    private static void test2() {
        String key = "1234567890123456";
        String ivKey = "6543210987654321";
        //String context = "0Ml76z+Fj18t8Ixp+kk0yRrlnywqE9W+0tdEueyqHYYyfZBgPwINaLqPCsxY6d8QaECefMS5OlXTtrkUJrop3mgXInWu/1kf+qhrRo4UXbEcjxY+w9BHTikKbpze198kCU+TQX4uNH8fjDyOdgiiw7DXOBzMsJmDIBmTZkTPZctRkacmXXjX12Zo2i0z6ulScOcAWuPV68+wP8DUHuMbV1psKdqbdhlAdRFfVYe796QJebRGk19lNwpXZdxvsc6X0qw/5JSvWb11AKxrg+N2E8oVgWabs3xtSGsbp51Pw24l/lhbt5N6bpt/GfQLIr7H6+p+yu/rvGnAD2dXR9Tr022W2A0+XURHx1dezPNgrqQZJsok39dHvLJucj9WOXVvXy+KZMjBle9aKN21EGBU/cPyLp/0ODMXw6yu9QB57GtJFpCMaVQVEA4zUSD03qHS";
        String context = "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";
        System.out.println(AesUtil.decrypt(context, key, ivKey));
    }

    private static void test1() {
        String key = "1234567890123456";
        String ivKey = "6543210987654321";
        PageParam pageParam = PageParam.builder().pageNum(1).pageSize(5).build();
        String context = JSON.toJSONString(pageParam);
        System.out.println(AesUtil.encrypt(context, key, ivKey));
    }
}
