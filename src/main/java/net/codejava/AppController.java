package net.codejava;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import net.codejava.admin.User;
import net.codejava.admin.UserRepository;
import net.codejava.document.Document;
import net.codejava.document.DocumentRepository;

import net.codejava.employee.Employee;
import net.codejava.employee.EmployeeDetails;
import net.codejava.employee.EmployeeRepository;
import net.codejava.leave.LeaveApplied;
import net.codejava.leave.LeaveAppliedRepository;
import net.codejava.post.Post;
import net.codejava.post.PostRepository;
import net.codejava.project.Project;
import net.codejava.project.ProjectRepository;

@Controller
public class AppController {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private EmployeeRepository empRepo;
	@Autowired
	private LeaveAppliedRepository leaveRepo;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	  private DocumentRepository documentRepository;
	@Autowired
	private ProjectRepository projectRepository;
	
	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		
		return "signup_form";
	}
	
	@GetMapping("/admin/registernewemp")
	public String showEmpRegistrationForm(Model model) {
		model.addAttribute("employee", new Employee());
		
		return "admin/emp_signup_form";
	}
	
	@PostMapping("/process_register")
	public String processRegister(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		userRepo.save(user);
		
		return "register_success";
	}
	
	@PostMapping("/admin/new_emp_register")
	public String newempRegister(Employee employee) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(employee.getPassword());
		employee.setPassword(encodedPassword);
		Long num = (long) 5;
		Project project = projectRepository.findAllByProjectid(num);
	employee.setProject(project);
		empRepo.save(employee);
		
		return "admin/register_success";}
	
	@GetMapping("/admin/users")
	public String listUsers(Model model) {
		List<Employee> listUsers = empRepo.findAll();
		model.addAttribute("listUsers", listUsers);
	
		
		return "admin/users";
	}
	
	@GetMapping("/admin/login")
    public String viewAdminLoginPage() {
        return "admin/admin_login";
    }
     
//    @GetMapping("/admin/home")
//    public String viewAdminHomePage(Model model) {
//    	List<Employee> listUsers = empRepo.findAll();
//		model.addAttribute("listUsers", listUsers);
//		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
//		System.out.println(listUsers);
//		
//		return "admin/users";
////        return "admin/admin_home";
//    }
     
    @GetMapping("/employee/login")
    public String viewUserLoginPage() {
        return "employee/employee_login";
    }
     
    @GetMapping("/employee/home")
    public String viewUserHomePage() {
        return "employee/employee_home";
    }
    
    @GetMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam Long employeeId) {
		empRepo.deleteById(employeeId);
		return "redirect:/admin/users";
	}
    
    @GetMapping("/employee/changepassword")
    public String viewChangePasswordForm() {
        return "employee/change_password";
    }
    
    @PostMapping("/employee/change_password")
    @PreAuthorize("hasRole('READ_PRIVILEGE')")
    public String changeUserPassword(Locale locale, 
      @RequestParam("password") String password, 
      @RequestParam("oldpassword") String oldPassword) throws Exception {
        Employee employee = empRepo.findByEmail(
        SecurityContextHolder.getContext().getAuthentication().getName());
        
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String encodedPassword = passwordEncoder.encode(oldPassword);
//		System.out.println(passwordEncoder.encode("password1"));
//		System.out.println(oldPassword);
//		System.out.println(passwordEncoder.encode("password1"));
//		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		if(passwordEncoder.matches(oldPassword, employee.getPassword())) {
			String encodedPassword2 = passwordEncoder.encode(password);	
		employee.setPassword(encodedPassword2);
		empRepo.save(employee);}
		else {
			return "employee/password_change_failed";
		}
        
        
        return "employee/password_change_success";
    }
    
    @GetMapping("/employee/personaldetails")
    public String viewPersonalDetails() {
        return "employee/personal_details";
    }
    
    @GetMapping("/employee/editPersonalDetails")
    public String editPersonalDetails() {
        return "employee/edit_personal_details";
    }
    
    @PostMapping("/employee/update_personal_details")
    @PreAuthorize("hasRole('READ_PRIVILEGE')")
	public String personalDetailsUpdate(@RequestParam("address") String address, @RequestParam("phone") String phone, @RequestParam("dob") Date dob, Model model) {
    	Employee employee = empRepo.findByEmail(
    	        SecurityContextHolder.getContext().getAuthentication().getName());
		employee.setAddress(address);
		employee.setPhone(phone);
		employee.setDOB(dob);
		empRepo.save(employee);
//		model.addAttribute("employeeDetails",employee);
		
		return "redirect:/";}
    
    @GetMapping("/employee/applyLeave")
    public String applyLeave(Model model) {
    	model.addAttribute("leave", new LeaveApplied());
        return "employee/leave_application";
    }

    @PostMapping("/employee/apply_for_leave")
	public String applyForLeave(LeaveApplied leave) {
    	 EmployeeDetails emp = (EmployeeDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
         String email = emp.getEmail();
         Long empid = emp.getId();
         leave.setEmail(email);
         leave.setEmpid(empid);
         leave.setStatus("Pending");
         leaveRepo.save(leave);
         
		return "redirect:/employee/applied_leaves";}
	@GetMapping("/employee/applied_leaves")
	public String viewAppliedLeaves(Model model) {
   	 EmployeeDetails emp = (EmployeeDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
   	Long empid = emp.getId();
		List<LeaveApplied> listOfLeave = leaveRepo.findAllByEmpid(empid);
		model.addAttribute("listOfLeave", listOfLeave);
	
		
		return "employee/applied_leaves";
	}
    
	@GetMapping("/admin/viewLeaveRequests")
	public String viewLeaveRequests(Model model) {
		List<LeaveApplied> listOfLeaves = leaveRepo.findAll();
		model.addAttribute("listOfLeaves", listOfLeaves);
	
		
		return "admin/leave_requests";
	}
	
	@GetMapping("/admin/approveLeave/{id}")
	public String approveLeave(@PathVariable Long id) {
	    LeaveApplied leave = leaveRepo.findAllById(id);
	    leave.setStatus("Approved");
	    leaveRepo.save(leave);
	    return "redirect:/admin/viewLeaveRequests";
	}
	
	@GetMapping("/admin/rejectLeave/{id}")
	public String rejectLeave(@PathVariable Long id) {
	    LeaveApplied leave = leaveRepo.findAllById(id);
	    leave.setStatus("Rejected");
	    leaveRepo.save(leave);
	    return "redirect:/admin/viewLeaveRequests";
	}
	
	@GetMapping("/admin/HRNoticeBoard")
	public String showHRNoticeBoard(Model model) {
		model.addAttribute("post",new Post());
		return "admin/HRNoticeBoard";
	}
	
	@PostMapping("/admin/newPost")
	public String newPost(Post post) {
		LocalDate today = LocalDate.now();
		post.setDate(today);
		postRepository.save(post);
		
		return "redirect:/admin/HRNoticeBoard";}
	
	@GetMapping("/employee/viewHRNotice")
	public String viewHRNotice(Model model) {
		List<Post> listPosts = postRepository.findAll();
		model.addAttribute("listPosts", listPosts);
	
		
		return "employee/HRPosts";	}
	
	@GetMapping("/admin/projectAllocation")
	public String projectAllocation(Model model) {
		List<Employee> listEmployee = empRepo.findAll();
		model.addAttribute("listEmployee", listEmployee);
		
		return "admin/project_allocation";
	}
	
	@GetMapping("/admin/docUpload")
	public String docUpload() {
		
		return "admin/document_upload";
	}
	
	 @PostMapping("admin/upload")
	  public String uploadFile(@RequestParam("document") MultipartFile multipartFile,@RequestParam("empid") Long empid, RedirectAttributes ra) throws IOException {
	    String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
	    Document document = new Document();
	    document.setName(fileName);
	    document.setEmpid(empid);
	    document.setData(multipartFile.getBytes());
	    
	    documentRepository.save(document);
	    ra.addFlashAttribute("message","Uploaded successfully");
	    return "admin/document_upload";
	  }

	 @GetMapping("/employee/viewDocuments")
		public String viewDocuments(Model model) {
		 Employee employee = empRepo.findByEmail(
			        SecurityContextHolder.getContext().getAuthentication().getName());
		 Long empid = employee.getId();
			List<Document> listDocs = documentRepository.findAllByEmpid(empid);
			model.addAttribute("listDocs",listDocs);
			return "employee/viewDocuments";
		}
	 
	 @GetMapping("/employee/download")
	 public void downloadFile(@Param("id") Long id,HttpServletResponse response) throws Exception {
		 Optional<Document> result = documentRepository.findById(id);
		 if(!result.isPresent()) {
			 throw new Exception("Could not find document");
		 }
		 Document document = result.get();
		 response.setContentType("application/octet-stream");
		 String headerKey = "Content-Disposition";
		 String headerValue = "attachment; filename="+document.getName();
		 response.setHeader(headerKey, headerValue);
		 ServletOutputStream outputStream = response.getOutputStream();
		 outputStream.write(document.getData());
		 outputStream.close();
		 
	 }
	 
	 @PostMapping("/admin/allocate")
		public String allocateProject(@RequestParam("empid") Long empid, @RequestParam("project_id") Long project_id) {
			Employee employee = empRepo.findAllById(empid);
			Project project = projectRepository.findAllByProjectid(project_id);
			employee.setProject(project);
			empRepo.save(employee);
			return "redirect:/admin/users";}
		
	 @GetMapping("/employee/projectDetails")
		public String projectDetails(Model model) {
		 Employee employee = empRepo.findByEmail(
			        SecurityContextHolder.getContext().getAuthentication().getName());
		 Long projectid = employee.getProject().getProjectid();
		 Project project = projectRepository.findAllByProjectid(projectid);
		 model.addAttribute("projectDetails",project);
		
			
			return "employee/project_allocation";
		}
	 
	 @GetMapping("/admin/viewEmployees")
	    public String viewEmployees(Model model) {
		     	model.addAttribute("employeeList", empRepo.findAll());
	        return "admin/view_all_employees";
	    }
	 @GetMapping("/admin/viewProjects")
	    public String viewProjects(Model model) {
		     	model.addAttribute("projectList", projectRepository.findAll());
	        return "admin/view_all_projects";
	    }
}
