package com.much.it.controller;

import com.much.it.entity.PageParam;
import com.much.it.entity.PageVO;
import com.much.it.entity.Title;
import com.much.it.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @project: much
 * @date: 2020/5/2
 * @author: Wenxin
 * @version: 1.0
 */
@RestController
@RequestMapping("title")
public class TitleController {

    @Autowired
    private TitleService titleService;

    @GetMapping("/{empNo}")
    public Title selectById(@PathVariable("empNo") Long empNo) {
        return titleService.selectById(empNo);
    }
    @PutMapping("/insertTitle")
    public Integer insertTitle(@RequestBody @Validated Title title) {
        return titleService.insertTitle(title);
    }

    @PutMapping("/{num}")
    public Integer insertBatch(@PathVariable("num") Integer num) {
        return titleService.insertBatch(num);
    }

    @PostMapping("/findByPage")
    public PageVO<Title> findByPage(@RequestBody @Validated PageParam pageParam) {
        return titleService.findByPage(pageParam);
    }
    @DeleteMapping("/deleteAll")
    public Integer deleteAll() {
        return titleService.deleteAll();
    }

    @DeleteMapping("/{empNo}")
    public Integer deleteByEmpNo(@PathVariable("empNo") Long empNo) {
        return titleService.deleteByEmpNo(empNo);
    }
}
