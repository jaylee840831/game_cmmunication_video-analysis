package com.example.hibernateTest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.hibernateTest.StudentRequest;
import com.example.hibernateTest.dto.GameInfo;
import com.example.hibernateTest.dto.Laptop;
import com.example.hibernateTest.dto.Student;
import com.example.hibernateTest.dto.UserInfo;
import com.example.hibernateTest.dto.Users;
import com.example.hibernateTest.service.BallGameService;
import com.example.hibernateTest.service.LaptopService;
import com.example.hibernateTest.service.NewUserDetailService;
import com.example.hibernateTest.service.StudentService;


@RestController
@RequestMapping("/api/v1")
public class APIController {
	
	@Autowired
	LaptopService laptopService;
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	NewUserDetailService newUserDetailService;
	
	@Autowired
	BallGameService ballGameService;
	
	@Autowired
	private ResourceBundleMessageSource resourceBundleMessageSource;
	
	@Value("${placeholder.hello.visiter}")
	private String visiter;
	
	@Value("${placeholder.hello.username}")
	private String name;
	
	//取得球局資料
	@GetMapping("/GET/gamedata/{code}")
	public ResponseEntity<List<GameInfo>> getGame(@PathVariable(value="code")String code){
		List<GameInfo> gameInfos= new ArrayList<GameInfo>();
		//code為0代表取全部球局資料
		if(code.equals("0")) {
			gameInfos=ballGameService.findAllGameInfo();
		}
		else {
			gameInfos.add(ballGameService.findGameInfo(code));
		}

		return new ResponseEntity<List<GameInfo>>(gameInfos,HttpStatus.OK);
	}
	
	//取得使用者資料
	@GetMapping("/GET/person")
	public ResponseEntity<UserInfo> getPerson(Authentication authentication){
		
		String username="";
		UserInfo userInfo=null;
		
		if(authentication!=null) {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			//取得user的權限種類
//			System.out.println("User has authorities: " + userDetails.getAuthorities());
			//取得user的名稱
			username=userDetails.getUsername();
		}
		
		if(!username.equals("") || username!=null) {
			userInfo=newUserDetailService.findUserInfo(username);
		}
		
		return new ResponseEntity<UserInfo>(userInfo,HttpStatus.OK);
	}
	
	// i18n 多國語系
	@GetMapping("/GET/hello")
	public ResponseEntity<String> getHelloMessage(
			@RequestHeader(value = "Accept-Language",defaultValue = "us", required = false)
			final String localeString,
			@RequestParam(value = "username",required = false,defaultValue = "paul")
			final String username) {
			System.out.println(localeString);
			Locale locale = new Locale(localeString);
			
			String str=resourceBundleMessageSource.getMessage(name, new Object[] {username},locale);
			
			return new ResponseEntity<String>(str,HttpStatus.OK);
	}
	
	// i18n 多國語系
	@GetMapping("/GET/hello/{language}")
	public String getHelloMessage2(
			@PathVariable(value = "language")
			final String localeString,
			Authentication authentication) {
		
			System.out.println("language: "+localeString);
			Locale locale = new Locale(localeString);
			
			String username="";
			
			if(authentication!=null) {
				UserDetails userDetails = (UserDetails) authentication.getPrincipal();
				//取得user的權限種類
	//			System.out.println("User has authorities: " + userDetails.getAuthorities());
				//取得user的名稱
				username=userDetails.getUsername();
				System.out.println("user name: " + username);
			}
			
			String welcomMessage="";
			if(username.equals("") || username==null) {
				welcomMessage=resourceBundleMessageSource.getMessage(visiter, new Object[] {""},locale);
			}
			else {
				welcomMessage=resourceBundleMessageSource.getMessage(name, new Object[] {username},locale);
			}
			
			return welcomMessage;
	}
	
	//取得筆電資料
	@GetMapping("/GET/laptop/{lid}")
	public ResponseEntity<Laptop>getLaptop(@PathVariable("lid") int lid){
		
		return new ResponseEntity<>(laptopService.getLaptop(lid), HttpStatus.OK);
	}
	
	//新增學生資料
	@PostMapping("/POST/student")
	public ResponseEntity<Student>setStudent(@RequestBody StudentRequest studentRequest){
	
		return new ResponseEntity<>(studentService.setStudent(studentRequest),HttpStatus.OK);
	}
	
	//隨機新增學生資料(多筆)
	@PostMapping("/POST/students")
	public ResponseEntity<Set<Student>>setStudents(){
	
		return new ResponseEntity<>(studentService.setRandomStudents(),HttpStatus.OK);
	}
	
	/*姓名取得學生資料
	 * name1=name
	 * name2=姓名*/
	@GetMapping("/GET/student_by_name/{name1}/{name2}")
	public ResponseEntity<List<Student>>getStudent(@PathVariable("name1")String name1,@PathVariable("name2")String name2){
	
		return new ResponseEntity<>(studentService.nameGetStudent(name1,name2),HttpStatus.OK);
	}
	
	/*rollno取得學生資料
		rollno1=rollno
		rollno2=代號
	**/
	@GetMapping("/GET/student_by_rollno/{rollno1}/{rollno2}")
	public ResponseEntity<List<Student>>getStudent(@PathVariable("rollno1")String rollno1,@PathVariable("rollno2")int rollno2){
	
		return new ResponseEntity<>(studentService.rollnoGetStudent(rollno1,rollno2),HttpStatus.OK);
	}
	
	//二級快取測試
	@GetMapping("/GET/cache")
	public ResponseEntity<Laptop>cacheTest(){
		return new ResponseEntity<>(laptopService.getLaptop(),HttpStatus.OK);
	}
}
