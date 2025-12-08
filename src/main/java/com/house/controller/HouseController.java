package com.house.controller;

import com.house.common.Result;
import com.house.common.StatusCode;
import com.house.dto.HouseExecution;
import com.house.pojo.HouseList;
import com.house.service.HouseListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value="/house")
public class HouseController {

    @Autowired
    private HouseListService houseListService;

    @RequestMapping(value = "/getallhouselist",method = RequestMethod.GET)
    public Result getAllHouseList(){
        System.out.println("*获取所有房屋列表:");
        List<HouseList> houseList = houseListService.findHouseListByCondition(null,null,null);
        System.out.println("**已获取房屋列表: " + houseList);
        return new Result(true, StatusCode.SUCCESS,"查找房屋信息列表成功",houseList);
    }

    @RequestMapping(value = "/gethouselistbycondition",method = RequestMethod.POST)
    public Result getHouseListByCondition(@RequestBody HouseList houseList){
        System.out.println("*按条件获取房屋列表: " + houseList);
        List<HouseList> result = houseListService.findHouseListByCondition(houseList.getStatus(),houseList.getAddress(),houseList.getUserlist_Id());
        System.out.println("**已获取房屋列表: " + result);
        return new Result(true, StatusCode.SUCCESS,"按条件查找房屋信息列表成功", result);
    }

    @RequestMapping(value="/addhouse",method = RequestMethod.POST)
    public Result addHouse(@RequestBody HouseList houseList){
        System.out.println("*添加房屋信息: " + houseList);
        HouseExecution he;
        try{
            he = houseListService.addHouse(houseList);
            System.out.println("**房屋添加结果: " + he);
            if(he.isFlag()){
                return new Result(true,StatusCode.SUCCESS,"添加房屋信息成功");
            }else {
                return new Result(false,StatusCode.ERROR,"添加房屋信息失败：" + he.getReason());
            }
        }catch (Exception e){
            System.out.println("*添加房屋信息时发生异常: " + e.getMessage());
            e.printStackTrace();
            return new Result(false,StatusCode.ERROR,"添加房屋信息失败：" + e.toString());
        }
    }

    @RequestMapping(value="/updatehouse",method = RequestMethod.POST)
    public Result updateHouse(@RequestBody HouseList houseList){
        System.out.println("*更新房屋信息: " + houseList);
        HouseExecution he;
        try{
            he = houseListService.updateHouse(houseList);
            System.out.println("**房屋更新结果: " + he);
            if(he.isFlag()){
                return new Result(true,StatusCode.SUCCESS,"修改房屋信息成功");
            }else {
                return new Result(false,StatusCode.ERROR,"修改房屋信息失败：" + he.getReason());
            }
        }catch (Exception e){
            System.out.println("*更新房屋信息时发生异常: " + e.getMessage());
            e.printStackTrace();
            return new Result(false,StatusCode.ERROR,"修改房屋信息失败：" + e.toString());
        }
    }

    @RequestMapping(value="/deletehouse",method = RequestMethod.DELETE)
    public Result deleteHouse(@RequestParam("houseId") Integer houseId){
        System.out.println("*删除房屋信息，ID: " + houseId);
        HouseExecution he;
        try{
            he = houseListService.deleteHouse(houseId);
            System.out.println("**房屋删除结果: " + he);
            if(he.isFlag()){
                return new Result(true,StatusCode.SUCCESS,"删除房屋信息成功");
            }else {
                return new Result(false,StatusCode.ERROR,"删除房屋信息失败：" + he.getReason());
            }
        }catch (Exception e){
            System.out.println("*删除房屋信息时发生异常: " + e.getMessage());
            e.printStackTrace();
            return new Result(false,StatusCode.ERROR,"删除房屋信息失败：" + e.toString());
        }
    }
}
