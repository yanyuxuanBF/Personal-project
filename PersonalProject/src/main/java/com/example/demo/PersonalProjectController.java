package com.example.demo;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.JobOriginatingUserName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Blog;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.BlogService;
import com.example.demo.services.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
public class PersonalProjectController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BlogService blogService;
	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}

	@GetMapping("/register")
	public String getRegister() {
		return "register";
	}

	@GetMapping("/blog")
	public String blog(//
			@RequestParam("Username") String username, //
			Map<String, Object> map) {

		map.put("Username", username);
		List<Blog> blogs = blogService.findBlogsByUsername(username);
		map.put("blogs", blogs);
		return "blog";
	}

	@GetMapping("/unlogin")
	public String getUnLogin() {
		return "unlogin";
	}

	@GetMapping("/editBlog")
	public String getEditBlog(//
			@RequestParam("Username") String username, //
			Map<String, Object> map) {
		map.put("Username", username);
		return "editBlog";
	}

	@PostMapping("/login")
	public ModelAndView login(//
			@RequestParam("Username") String username, //
			@RequestParam("password") String password, //
			ModelAndView mv) {
		User user = userRepository//
				.findByUsername(username);
		mv.addObject("Username", username);
		if (username.equals(user.getUsername())//
				&& password.equals(user.getPassword())) {
			mv.setViewName("redirect:/blog");
		} else {
			mv.setViewName("unlogin");
		}
		return mv;
	}

	@PostMapping("/register")
	public ModelAndView register(//
			@RequestParam("Username") String username, //
			@RequestParam("password") String password, //
			@RequestParam("password_two") String retypePassword, //
			ModelAndView mv) {
		if (!password.equals(retypePassword)) {
			mv.setViewName("unlogin");
		} else {
			User user = User.builder()//
					.username(username)//
					.password(password)//
					.build();
			userRepository.save(user);
			mv.addObject("Username", username);
			mv.setViewName("blog");
		}
		return mv;
	}

	@PostMapping("/editBlog")
	public ModelAndView editBlog(//
			@RequestParam("Username") String username, //
			@RequestParam("title") String title, //
			@RequestParam("editContent") String editContent, //
			ModelAndView mv) {
		User user = userService.findByUsername(username);

		Blog blog = Blog.builder()//
				.title(title)//
				.editContent(editContent)//
				.userId(user.getId()).username(username).build();
		blogService.addBlog(blog);
		mv.addObject("user_id", user.getId());
		mv.addObject("Username", username);
		mv.setViewName("redirect:/blog");
		return mv;

	}
}
