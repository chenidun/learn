package com.demo.learn.controller;

import com.demo.learn.model.Good;
import com.demo.learn.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "good")
@ResponseStatus(HttpStatus.OK)
public class GoodController {

    @Autowired
    GoodService goodService;

    @GetMapping("get")
    public Good get(@RequestParam("id") Integer id){
        return goodService.getGood(id);
    }

    @PostMapping("add")
    public String add(@RequestBody Good good){
        goodService.addGood(good);
        return "ok";
    }

    @PostMapping("delete")
    public String delete(@RequestParam("id") Integer id){
        goodService.deleteGood(id);
        return "ok";
    }

    @PostMapping("edit")
    public String edit(@RequestBody Good good){
        goodService.editGood(good);
        return "ok";
    }

    @GetMapping("list")
    public List<Good> getList(){
        return goodService.getGoodList();
    }

    @GetMapping("buy")
    public String buy(@RequestParam("goodName") String goodName) {
        boolean result = goodService.buy(goodName);
        return result ? "抢购" + goodName + "成功" : "抢购" + goodName + "失败";
    }

    @GetMapping("resetStore")
    public String resetStore(@RequestParam("store") Integer store) {
        goodService.resetStore(store);
        return "重置成功";
    }
}