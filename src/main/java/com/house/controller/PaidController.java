package com.house.controller;

import com.house.common.Result;
import com.house.common.StatusCode;
import com.house.dto.PaidExecution;
import com.house.pojo.Paid;
import com.house.service.PaidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin
@RequestMapping(value="/paid")
public class PaidController {

    @Autowired
    private PaidService paidService;

    @RequestMapping(value = "/getallpaidlist", method = RequestMethod.GET)
    public Result getAllPaidList() {
        System.out.println("*获取所有租金列表");
        ArrayList<Paid> paidList = new ArrayList<>(paidService.findPaidListByCondition(null, null, null, null));
        System.out.println("**已获取租金列表: " + paidList);
        return new Result(true, StatusCode.SUCCESS, "查找租金信息列表成功", paidList);
    }

    @RequestMapping(value = "/getpaidlistbycondition", method = RequestMethod.POST)
    public Result getPaidListByCondition(@RequestBody Paid paid) {
        System.out.println("*按条件获取租金列表: " + paid);
        ArrayList<Paid> result = new ArrayList<>(paidService.findPaidListByCondition(paid.getStatus(), paid.getName(), paid.getAddress(), paid.getUserlist_id()));
        System.out.println("**已获取租金列表: " + result);
        return new Result(true, StatusCode.SUCCESS, "按条件查找租金信息列表成功", result);
    }

    @RequestMapping(value = "/addpaid", method = RequestMethod.POST)
    public Result addPaid(@RequestBody Paid paid) {
        System.out.println("*添加租金信息: " + paid);
        PaidExecution pe;
        try {
            pe = paidService.addPaid(paid);
            System.out.println("**租金信息添加结果: " + pe);
            if (pe.isFlag()) {
                return new Result(true, StatusCode.SUCCESS, "添加租金信息成功");
            } else {
                return new Result(false, StatusCode.ERROR, "添加租金信息失败：" + pe.getReason());
            }
        } catch (Exception e) {
            System.out.println("*添加租金信息时发生异常: " + e.getMessage());
            e.printStackTrace();
            return new Result(false, StatusCode.ERROR, "添加租金信息失败：" + e.toString());
        }
    }

    @RequestMapping(value = "/updatepaid", method = RequestMethod.POST)
    public Result updatePaid(@RequestBody Paid paid) {
        System.out.println("*更新租金信息: " + paid);
        PaidExecution pe;
        try {
            pe = paidService.updatePaid(paid);
            System.out.println("**租金信息更新结果: " + pe);
            if (pe.isFlag()) {
                return new Result(true, StatusCode.SUCCESS, "修改租金信息成功");
            } else {
                return new Result(false, StatusCode.ERROR, "修改租金信息失败：" + pe.getReason());
            }
        } catch (Exception e) {
            System.out.println("*更新租金信息时发生异常: " + e.getMessage());
            e.printStackTrace();
            return new Result(false, StatusCode.ERROR, "修改租金信息失败：" + e.toString());
        }
    }

    @RequestMapping(value = "/deletepaid", method = RequestMethod.DELETE)
    public Result deletePaid(@RequestParam("paidId") Integer paidId) {
        System.out.println("*删除租金信息，ID: " + paidId);
        PaidExecution pe;
        try {
            pe = paidService.deletePaid(paidId);
            System.out.println("**租金信息删除结果: " + pe);
            if (pe.isFlag()) {
                return new Result(true, StatusCode.SUCCESS, "删除租金信息成功");
            } else {
                return new Result(false, StatusCode.ERROR, "删除租金信息失败：" + pe.getReason());
            }
        } catch (Exception e) {
            System.out.println("*删除租金信息时发生异常: " + e.getMessage());
            e.printStackTrace();
            return new Result(false, StatusCode.ERROR, "删除租金信息失败：" + e.toString());
        }
    }
}
