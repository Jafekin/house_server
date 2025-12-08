//package com.house.controller;
//
//import com.house.common.Result;
//import com.house.common.StatusCode;
//import com.house.dto.SolveExecution;
//import com.house.pojo.Solve;
//import com.house.service.SolveService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//
//@RestController
//@CrossOrigin
//@RequestMapping(value="/solve")
//public class SolveController {
//
//    @Autowired
//    private SolveService solveService;
//
//    @RequestMapping(value = "/getallsolvelist",method = RequestMethod.GET)
//    public Result getAllSolveList(){
//
//        return new Result(true, StatusCode.SUCCESS,"查找故障信息列表成功",solveService.findSolveListByCondition(null,null,null,null));
//    }
//
//    @RequestMapping(value = "/getsolvelistbycondition",method = RequestMethod.POST)
//    public Result getSolveListByCondition(@RequestBody Solve solve){
//        return new Result(true, StatusCode.SUCCESS,"按条件查找故障信息列表成功",solveService.findSolveListByCondition(solve.getStatus(),solve.getName(),solve.getAddress(),solve.getUserlist_id()));
//    }
//
//    @RequestMapping(value="/addsolve",method = RequestMethod.POST)
//    public Result addSolve(@RequestBody Solve solve){
//        SolveExecution se;
//        try{
//            se = solveService.addSolve(solve);
//            if(se.isFlag()){
//                return new Result(true,StatusCode.SUCCESS,"添加故障信息成功");
//            }else {
//                return new Result(false,StatusCode.ERROR,"添加故障信息失败：" + se.getReason());
//            }
//        }catch (Exception e){
//            return new Result(false,StatusCode.ERROR,"添加故障信息失败：" + e.toString());
//        }
//    }
//
//    @RequestMapping(value="/updatesolve",method = RequestMethod.POST)
//    public Result updateSolve(@RequestBody Solve solve){
//        SolveExecution se;
//        try{
//            se = solveService.updateSolve(solve);
//            if(se.isFlag()){
//                return new Result(true,StatusCode.SUCCESS,"修改故障信息成功");
//            }else {
//                return new Result(false,StatusCode.ERROR,"修改故障信息失败：" + se.getReason());
//            }
//        }catch (Exception e){
//            return new Result(false,StatusCode.ERROR,"修改故障信息失败：" + e.toString());
//        }
//    }
//
//    @RequestMapping(value="/deletesolve",method = RequestMethod.DELETE)
//    public Result deleteSolve(@RequestParam("solveId")Integer solveId){
//        SolveExecution se;
//        try{
//            se = solveService.deleteSolve(solveId);
//            if(se.isFlag()){
//                return new Result(true,StatusCode.SUCCESS,"删除故障信息成功");
//            }else {
//                return new Result(false,StatusCode.ERROR,"删除故障信息失败：" + se.getReason());
//            }
//        }catch (Exception e){
//            return new Result(false,StatusCode.ERROR,"删除故障信息失败：" + e.toString());
//        }
//    }
//}
package com.house.controller;

import com.house.common.Result;
import com.house.common.StatusCode;
import com.house.dto.SolveExecution;
import com.house.pojo.Solve;
import com.house.service.SolveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin
@RequestMapping(value="/solve")
public class SolveController {

    @Autowired
    private SolveService solveService;

    @RequestMapping(value = "/getallsolvelist",method = RequestMethod.GET)
    public Result getAllSolveList(){
        System.out.println("*获取所有故障信息列表");
        ArrayList<Solve> solveList = new ArrayList<>(solveService.findSolveListByCondition(null,null,null,null));
        System.out.println("**已获取故障信息列表: " + solveList);
        return new Result(true, StatusCode.SUCCESS,"查找故障信息列表成功",solveList);
    }

    @RequestMapping(value = "/getsolvelistbycondition",method = RequestMethod.POST)
    public Result getSolveListByCondition(@RequestBody Solve solve){
        System.out.println("*按条件获取故障信息列表: " + solve);
        ArrayList<Solve> result = new ArrayList<>(solveService.findSolveListByCondition(solve.getStatus(),solve.getName(),solve.getAddress(),solve.getUserlist_id()));
        System.out.println("**已获取按条件查询的故障信息列表: " + result);
        return new Result(true, StatusCode.SUCCESS,"按条件查找故障信息列表成功",result);
    }

    @RequestMapping(value="/addsolve",method = RequestMethod.POST)
    public Result addSolve(@RequestBody Solve solve){
        System.out.println("*添加故障信息: " + solve);
        SolveExecution se;
        try{
            se = solveService.addSolve(solve);
            System.out.println("**故障信息添加结果: " + se);
            if(se.isFlag()){
                return new Result(true,StatusCode.SUCCESS,"添加故障信息成功");
            }else {
                return new Result(false,StatusCode.ERROR,"添加故障信息失败：" + se.getReason());
            }
        }catch (Exception e){
            System.out.println("*添加故障信息时发生异常: " + e.getMessage());
            e.printStackTrace();
            return new Result(false,StatusCode.ERROR,"添加故障信息失败：" + e.toString());
        }
    }

    @RequestMapping(value="/updatesolve",method = RequestMethod.POST)
    public Result updateSolve(@RequestBody Solve solve){
        System.out.println("*更新故障信息: " + solve);
        SolveExecution se;
        try{
            se = solveService.updateSolve(solve);
            System.out.println("**故障信息更新结果: " + se);
            if(se.isFlag()){
                return new Result(true,StatusCode.SUCCESS,"修改故障信息成功");
            }else {
                return new Result(false,StatusCode.ERROR,"修改故障信息失败：" + se.getReason());
            }
        }catch (Exception e){
            System.out.println("*更新故障信息时发生异常: " + e.getMessage());
            e.printStackTrace();
            return new Result(false,StatusCode.ERROR,"修改故障信息失败：" + e.toString());
        }
    }

    @RequestMapping(value="/deletesolve",method = RequestMethod.DELETE)
    public Result deleteSolve(@RequestParam("solveId")Integer solveId){
        System.out.println("*删除故障信息，ID: " + solveId);
        SolveExecution se;
        try{
            se = solveService.deleteSolve(solveId);
            System.out.println("**故障信息删除结果: " + se);
            if(se.isFlag()){
                return new Result(true,StatusCode.SUCCESS,"删除故障信息成功");
            }else {
                return new Result(false,StatusCode.ERROR,"删除故障信息失败：" + se.getReason());
            }
        }catch (Exception e){
            System.out.println("*删除故障信息时发生异常: " + e.getMessage());
            e.printStackTrace();
            return new Result(false,StatusCode.ERROR,"删除故障信息失败：" + e.toString());
        }
    }
}
