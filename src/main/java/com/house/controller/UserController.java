//package com.house.controller;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import com.house.dto.LoginUser;
//import com.house.common.Result;
//import com.house.common.StatusCode;
//import com.house.dto.UserExecution;
//import com.house.pojo.User;
//import com.house.pojo.UserList;
//import com.house.service.UserService;
//import com.house.utils.JwtUtil;
//import com.house.vo.PasswordVO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@CrossOrigin
//@RequestMapping(value="/user")
//public class UserController {
//
//
//	@Autowired
//	private UserService userService;
//
//
//	@RequestMapping(value = "/login",method = RequestMethod.POST)
//	public Map<String,Object> login(@RequestBody LoginUser loginUser) {
//		Map<String,Object> map = new HashMap<>();
//		User user = userService.login(loginUser.getAccount(),loginUser.getPassword());
//		if(user == null){
//			map.put("flag",false);
//			return map;
//		}
//		UserList userList = userService.findUserInfoByCondition(null,user.getId(),null);
//		//生成令牌
//		JwtUtil jwtUtil = new JwtUtil();
//		String token = null;
//		if(userList.getType() == 1){
//			map.put("systemRole","admin");
//			token = jwtUtil.createJWT(String.valueOf(userList.getId()),user.getUsername(),"admin");
//
//		}else {
//			map.put("systemRole","user");
//			token = jwtUtil.createJWT(String.valueOf(userList.getId()),user.getUsername(),"user");
//		}
//
//		map.put("userInfo",userList);
//		map.put("token",token);
//		map.put("flag",true);
//		return map;
//
//	}
//
//	@RequestMapping(value = "/getalluserlist",method = RequestMethod.GET)
//	public Result getAllUserList(){
//		return new Result(true, StatusCode.SUCCESS,"查找用户列表成功",userService.findUserListByCondition(null,null));
//	}
//
//	@RequestMapping(value = "/getuserlistbycondition",method = RequestMethod.POST)
//	public Result getuUerListByCondition(@RequestBody UserList userList){
//		return new Result(true, StatusCode.SUCCESS,"按条件查找用户列表成功",userService.findUserListByCondition(userList.getName(),userList.getId()));
//	}
//
//	@RequestMapping(value = "/getuserinfobycondition",method = RequestMethod.POST)
//	public Result getUserInfoByCondition(@RequestBody UserList userList){
//		return new Result(true, StatusCode.SUCCESS,"按条件查找用户列表成功",userService.findUserInfoByCondition(userList.getName(),userList.getUserId(),userList.getId()));
//
//	}
//
//	@RequestMapping(value="/adduser",method = RequestMethod.POST)
//	public Result addUser(@RequestBody UserList userList){
//		UserExecution ue;
//		try{
//			ue = userService.addUserListAndUserAccount(userList);
//			if(ue.isFlag()){
//				return new Result(true,StatusCode.SUCCESS,"添加用户成功");
//			}else {
//				return new Result(false,StatusCode.ERROR,"添加用户失败：" + ue.getReason());
//			}
//		}catch (Exception e){
//			return new Result(false,StatusCode.ERROR,"添加用户失败：" + e.toString());
//		}
//	}
//
//	@RequestMapping(value="/updateuser",method = RequestMethod.POST)
//	public Result updateUser(@RequestBody UserList userList){
//		UserExecution ue;
//		try{
//			ue = userService.updateUserList(userList);
//			if(ue.isFlag()){
//				return new Result(true,StatusCode.SUCCESS,"更新用户成功");
//			}else {
//				return new Result(false,StatusCode.ERROR,"更新用户失败：" + ue.getReason());
//			}
//		}catch (Exception e){
//			return new Result(false,StatusCode.ERROR,"更新用户失败：" + e.toString());
//		}
//	}
//
//	@RequestMapping(value="/deleteuser",method = RequestMethod.DELETE)
//	public Result deleteUser(@RequestParam("userListid")Integer userListid){
//		UserExecution ue;
//		try{
//			ue = userService.deleteUser(userListid);
//			if(ue.isFlag()){
//				return new Result(true,StatusCode.SUCCESS,"删除用户成功");
//			}else {
//				return new Result(false,StatusCode.ERROR,"删除用户失败：" + ue.getReason());
//			}
//		}catch (Exception e){
//			return new Result(false,StatusCode.ERROR,"删除用户失败：" + e.toString());
//		}
//	}
//
//	@RequestMapping(value="/editpassword",method = RequestMethod.POST)
//	public Result updateUser(@RequestBody PasswordVO passwordVO){
//		UserExecution ue;
//		try{
//			ue = userService.updatePassword(passwordVO);
//			if(ue.isFlag()){
//				return new Result(true,StatusCode.SUCCESS,"修改密码成功");
//			}else {
//				return new Result(false,StatusCode.ERROR,ue.getReason());
//			}
//		}catch (Exception e){
//			return new Result(false,StatusCode.ERROR,"修改密码失败：" + e.toString());
//		}
//	}
//
//
//}
package com.house.controller;

import java.util.HashMap;
import java.util.Map;

import com.house.dto.LoginUser;
import com.house.common.Result;
import com.house.common.StatusCode;
import com.house.dto.UserExecution;
import com.house.pojo.User;
import com.house.pojo.UserList;
import com.house.service.UserService;
import com.house.utils.JwtUtil;
import com.house.vo.PasswordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public Map<String,Object> login(@RequestBody LoginUser loginUser) {
		System.out.println("用户登录请求：" + loginUser);
		Map<String,Object> map = new HashMap<>();
		User user = userService.login(loginUser.getAccount(),loginUser.getPassword());
		if(user == null){
			System.out.println("登录失败，用户不存在或密码错误");
			map.put("flag",false);
			return map;
		}
		UserList userList = userService.findUserInfoByCondition(null,user.getId(),null);
		//生成令牌
		JwtUtil jwtUtil = new JwtUtil();
		String token = null;
		if(userList.getType() == 1){
			map.put("systemRole","admin");
			token = jwtUtil.createJWT(String.valueOf(userList.getId()),user.getUsername(),"admin");

		}else {
			map.put("systemRole","user");
			token = jwtUtil.createJWT(String.valueOf(userList.getId()),user.getUsername(),"user");
		}

		map.put("userInfo",userList);
		map.put("token",token);
		map.put("flag",true);
		System.out.println("登录成功，返回信息：" + map);
		return map;

	}

	@RequestMapping(value = "/getalluserlist",method = RequestMethod.GET)
	public Result getAllUserList(){
		System.out.println("*获取所有用户列表");
		return new Result(true, StatusCode.SUCCESS,"查找用户列表成功",userService.findUserListByCondition(null,null));
	}

	@RequestMapping(value = "/getuserlistbycondition",method = RequestMethod.POST)
	public Result getuUerListByCondition(@RequestBody UserList userList){
		System.out.println("*按条件查询用户列表：" + userList);
		return new Result(true, StatusCode.SUCCESS,"按条件查找用户列表成功",userService.findUserListByCondition(userList.getName(),userList.getId()));
	}

	@RequestMapping(value = "/getuserinfobycondition",method = RequestMethod.POST)
	public Result getUserInfoByCondition(@RequestBody UserList userList){
		System.out.println("*按条件查询用户信息：" + userList);
		return new Result(true, StatusCode.SUCCESS,"按条件查找用户列表成功",userService.findUserInfoByCondition(userList.getName(),userList.getUserId(),userList.getId()));

	}

	@RequestMapping(value="/adduser",method = RequestMethod.POST)
	public Result addUser(@RequestBody UserList userList){
		System.out.println("*添加用户：" + userList);
		UserExecution ue;
		try{
			ue = userService.addUserListAndUserAccount(userList);
			if(ue.isFlag()){
				return new Result(true,StatusCode.SUCCESS,"添加用户成功");
			}else {
				return new Result(false,StatusCode.ERROR,"添加用户失败：" + ue.getReason());
			}
		}catch (Exception e){
			System.out.println("**添加用户失败：" + e.toString());
			e.printStackTrace();
			return new Result(false,StatusCode.ERROR,"添加用户失败：" + e.toString());
		}
	}

	@RequestMapping(value="/updateuser",method = RequestMethod.POST)
	public Result updateUser(@RequestBody UserList userList){
		System.out.println("*更新用户信息：" + userList);
		UserExecution ue;
		try{
			ue = userService.updateUserList(userList);
			if(ue.isFlag()){
				return new Result(true,StatusCode.SUCCESS,"更新用户成功");
			}else {
				return new Result(false,StatusCode.ERROR,"更新用户失败：" + ue.getReason());
			}
		}catch (Exception e){
			System.out.println("**更新用户失败：" + e.toString());
			e.printStackTrace();
			return new Result(false,StatusCode.ERROR,"更新用户失败：" + e.toString());
		}
	}

	@RequestMapping(value="/deleteuser",method = RequestMethod.DELETE)
	public Result deleteUser(@RequestParam("userListid")Integer userListid){
		System.out.println("*删除用户，用户ID：" + userListid);
		UserExecution ue;
		try{
			ue = userService.deleteUser(userListid);
			if(ue.isFlag()){
				return new Result(true,StatusCode.SUCCESS,"删除用户成功");
			}else {
				return new Result(false,StatusCode.ERROR,"删除用户失败：" + ue.getReason());
			}
		}catch (Exception e){
			System.out.println("**删除用户失败：" + e.toString());
			e.printStackTrace();
			return new Result(false,StatusCode.ERROR,"删除用户失败：" + e.toString());
		}
	}

	@RequestMapping(value="/editpassword",method = RequestMethod.POST)
	public Result updateUser(@RequestBody PasswordVO passwordVO){
		System.out.println("*修改用户密码：" + passwordVO);
		UserExecution ue;
		try{
			ue = userService.updatePassword(passwordVO);
			if(ue.isFlag()){
				return new Result(true,StatusCode.SUCCESS,"修改密码成功");
			}else {
				return new Result(false,StatusCode.ERROR,ue.getReason());
			}
		}catch (Exception e){
			System.out.println("*修改密码失败：" + e.toString());
			e.printStackTrace();
			return new Result(false,StatusCode.ERROR,"修改密码失败：" + e.toString());
		}
	}
}
