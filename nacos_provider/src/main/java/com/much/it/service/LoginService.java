package com.much.it.service;

import java.util.Map;

/**
 * @description:
 * @project: much
 * @date: 2020/5/2
 * @author: Wenxin
 * @version: 1.0
 */
public interface LoginService {

    Map<String, String> verification(String phone);

    String login(Map<String,String> map);
}
