package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.UserSearchRequest;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@Controller
public class UserController {
	
	@Autowired // インスタンスの注入
	UserService userService; 
	
	// トップページ表示
	@GetMapping("/")
	public String displaySearchAll(Model model) {
		List<User> list = userService.searchAll();
		model.addAttribute("usersinfo", list);
		return "index";
	}
	
	// ユーザー情報検索(1件取得)画面を表示
	@GetMapping("/user/search")
	public String displaySearch() {
		return "user/search";
	}
	
	// ユーザー情報検索画面に検索結果を表示
	@PostMapping("/user/id_search")
	public String search(@ModelAttribute UserSearchRequest userSearchRequest, Model model) { // @ModelAttributeにより、自動インスタンス化
		model.addAttribute("userinfo", userService.search(userSearchRequest));
		return "user/search";
	}
	
	// ユーザー登録画面を表示
	@GetMapping("/user/create")
	public String create() {
		return "user/create";
	}
	
	// ユーザー登録を行い、ホームにリダイレクト
	@PostMapping("/user/create")
	public String createOne(@Validated @ModelAttribute UserSearchRequest userSearchRequest, BindingResult result) {
		if(result.hasErrors()) {
			return "user/create";
		}
		userService.create(userSearchRequest);
		return "redirect:/"; //GetMappingを探している
	}
	
	// Update処理画面遷移
	@GetMapping("/user/update/id={id}")
	public String update(@PathVariable int id, Model model) {
		model.addAttribute("userinfo", userService.search(id));
		return "user/update";
	}
	
	// Update処理
	@PostMapping("/user/update/id={id}")
	public String updateProcess(@ModelAttribute UserSearchRequest user) {
		userService.update(user.getId(), user.getName(), user.getAge(), user.getBirthday());
		return "redirect:/";
	}
	
	//Delete処理
	@PostMapping("/user/delete/id={id}")
	public String delete(@PathVariable int id) {
		userService.delete(id);
		return "redirect:/";
	}
}
