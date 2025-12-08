//package com.house.controller;
//
//import com.house.common.Result;
//import com.house.common.StatusCode;
//import com.house.dto.ScheduleExecution;
//import com.house.pojo.Schedule;
//import com.house.service.ScheduleService;
//import com.house.utils.DateUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Date;
//
//@RestController
//@CrossOrigin
//@RequestMapping(value="/schedule")
//public class ScheduleController {
//
//    @Autowired
//    private ScheduleService scheduleService;
//
//    @RequestMapping(value = "/getallschedulelist",method = RequestMethod.GET)
//    public Result getAllScheduleList(){
//        return new Result(true, StatusCode.SUCCESS,"查找公告信息列表成功",scheduleService.findByCondition());
//    }
//
//    @RequestMapping(value = "/getscheduleinsevendays",method = RequestMethod.GET)
//    public Result getScheduleInsevenDays(){
//        return new Result(true, StatusCode.SUCCESS,"查找公告信息列表成功",scheduleService.findScheduleInSevenDays(DateUtil.dateFormat1(new Date())));
//    }
//
//    @RequestMapping(value = "/getschedulelistbycondition",method = RequestMethod.POST)
//    public Result getScheduleListByCondition(@RequestBody Schedule schedule){
//        return new Result(true, StatusCode.SUCCESS,"按条件查找公告信息列表成功",scheduleService.findByCondition());
//    }
//
//    @RequestMapping(value="/addschedule",method = RequestMethod.POST)
//    public Result addSchedule(@RequestBody Schedule schedule){
//        ScheduleExecution se;
//        try{
//            se = scheduleService.addSchedule(schedule);
//            if(se.isFlag()){
//                return new Result(true,StatusCode.SUCCESS,"添加公告信息成功");
//            }else {
//                return new Result(false,StatusCode.ERROR,"添加公告信息失败：" + se.getReason());
//            }
//        }catch (Exception e){
//            return new Result(false,StatusCode.ERROR,"添加公告信息失败：" + e.toString());
//        }
//    }
//
//    @RequestMapping(value="/updateschedule",method = RequestMethod.POST)
//    public Result updateSchedule(@RequestBody Schedule schedule){
//        ScheduleExecution se;
//        try{
//            se = scheduleService.updateSchedule(schedule);
//            if(se.isFlag()){
//                return new Result(true,StatusCode.SUCCESS,"修改公告信息成功");
//            }else {
//                return new Result(false,StatusCode.ERROR,"修改公告信息失败：" + se.getReason());
//            }
//        }catch (Exception e){
//            return new Result(false,StatusCode.ERROR,"修改公告信息失败：" + e.toString());
//        }
//    }
//
//    @RequestMapping(value="/deleteschedule",method = RequestMethod.DELETE)
//    public Result deleteSchedule(@RequestParam("scheduleId")Integer scheduleId){
//        ScheduleExecution se;
//        try{
//            se = scheduleService.deleteSchedule(scheduleId);
//            if(se.isFlag()){
//                return new Result(true,StatusCode.SUCCESS,"删除公告信息成功");
//            }else {
//                return new Result(false,StatusCode.ERROR,"删除公告信息失败：" + se.getReason());
//            }
//        }catch (Exception e){
//            return new Result(false,StatusCode.ERROR,"删除公告信息失败：" + e.toString());
//        }
//    }
//}
package com.house.controller;

import com.house.common.Result;
import com.house.common.StatusCode;
import com.house.dto.ScheduleExecution;
import com.house.pojo.Schedule;
import com.house.service.ScheduleService;
import com.house.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;

@RestController
@CrossOrigin
@RequestMapping(value="/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @RequestMapping(value = "/getallschedulelist", method = RequestMethod.GET)
    public Result getAllScheduleList() {
        System.out.println("*获取所有公告信息列表");
        ArrayList<Schedule> scheduleList = new ArrayList<>(scheduleService.findByCondition());
        System.out.println("**已获取公告信息列表: " + scheduleList);
        return new Result(true, StatusCode.SUCCESS, "查找公告信息列表成功", scheduleList);
    }

    @RequestMapping(value = "/getscheduleinsevendays", method = RequestMethod.GET)
    public Result getScheduleInsevenDays() {
        System.out.println("*获取未来七天的公告信息列表，当前日期：" + DateUtil.dateFormat1(new Date()));
        ArrayList<Schedule> scheduleList = new ArrayList<>(scheduleService.findScheduleInSevenDays(DateUtil.dateFormat1(new Date())));
        System.out.println("**已获取未来七天的公告信息列表: " + scheduleList);
        return new Result(true, StatusCode.SUCCESS, "查找公告信息列表成功", scheduleList);
    }

    @RequestMapping(value = "/getschedulelistbycondition", method = RequestMethod.POST)
    public Result getScheduleListByCondition(@RequestBody Schedule schedule) {
        System.out.println("*按条件获取公告信息列表: " + schedule);
        ArrayList<Schedule> result = new ArrayList<>(scheduleService.findByCondition());
        System.out.println("**已获取按条件查询的公告信息列表: " + result);
        return new Result(true, StatusCode.SUCCESS, "按条件查找公告信息列表成功", result);
    }

    @RequestMapping(value = "/addschedule", method = RequestMethod.POST)
    public Result addSchedule(@RequestBody Schedule schedule) {
        System.out.println("*添加公告信息: " + schedule);
        ScheduleExecution se;
        try {
            se = scheduleService.addSchedule(schedule);
            System.out.println("**公告信息添加结果: " + se);
            if (se.isFlag()) {
                return new Result(true, StatusCode.SUCCESS, "添加公告信息成功");
            } else {
                return new Result(false, StatusCode.ERROR, "添加公告信息失败：" + se.getReason());
            }
        } catch (Exception e) {
            System.out.println("*添加公告信息时发生异常: " + e.getMessage());
            e.printStackTrace();
            return new Result(false, StatusCode.ERROR, "添加公告信息失败：" + e.toString());
        }
    }

    @RequestMapping(value = "/updateschedule", method = RequestMethod.POST)
    public Result updateSchedule(@RequestBody Schedule schedule) {
        System.out.println("*更新公告信息: " + schedule);
        ScheduleExecution se;
        try {
            se = scheduleService.updateSchedule(schedule);
            System.out.println("**公告信息更新结果: " + se);
            if (se.isFlag()) {
                return new Result(true, StatusCode.SUCCESS, "修改公告信息成功");
            } else {
                return new Result(false, StatusCode.ERROR, "修改公告信息失败：" + se.getReason());
            }
        } catch (Exception e) {
            System.out.println("*更新公告信息时发生异常: " + e.getMessage());
            e.printStackTrace();
            return new Result(false, StatusCode.ERROR, "修改公告信息失败：" + e.toString());
        }
    }

    @RequestMapping(value = "/deleteschedule", method = RequestMethod.DELETE)
    public Result deleteSchedule(@RequestParam("scheduleId") Integer scheduleId) {
        System.out.println("*删除公告信息，ID: " + scheduleId);
        ScheduleExecution se;
        try {
            se = scheduleService.deleteSchedule(scheduleId);
            System.out.println("**公告信息删除结果: " + se);
            if (se.isFlag()) {
                return new Result(true, StatusCode.SUCCESS, "删除公告信息成功");
            } else {
                return new Result(false, StatusCode.ERROR, "删除公告信息失败：" + se.getReason());
            }
        } catch (Exception e) {
            System.out.println("*删除公告信息时发生异常: " + e.getMessage());
            e.printStackTrace();
            return new Result(false, StatusCode.ERROR, "删除公告信息失败：" + e.toString());
        }
    }
}
