package com.case_product_management.controller;

import com.case_product_management.dto.ListTodoDTO;
import com.case_product_management.model.User;
import com.case_product_management.service.ICategoryService;
import com.case_product_management.service.IOrderService;
import com.case_product_management.service.IRoleService;
import com.case_product_management.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
@SessionAttributes("loggedInUser")
public class AdminController {
    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IOrderService orderService;

    @ModelAttribute("loggedInUser")
    public User loggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.findByEmail(auth.getName());
    }

    @GetMapping
    public String adminPage(Model model) {
        ListTodoDTO listTodo = new ListTodoDTO();
        listTodo.setNewOrder(orderService.countByOrderStatus("Waiting Delivery"));
        listTodo.setWaitingOrder(orderService.countByOrderStatus("Waiting Approval"));
        listTodo.setNewContact(orderService.countByOrderStatus("Waiting response"));
        model.addAttribute("listTodo", listTodo);
        return "admin/AdminDashboard";
    }

    @GetMapping("/category")
    public String manageCategoryPage() {
        return "admin/manageCategory";
    }

    @GetMapping("/contact")
    public String manageContactPage() {
        return "admin/manageContact";
    }

    @GetMapping("/product")
    public String manageProductPage(Model model) {
//        model.addAttribute("listNhanHieu", hangSXService.getALlHangSX());
        model.addAttribute("listCategory", categoryService.getAllCategory());
        return "admin/manageProduct";
    }

    @GetMapping("/profile")
    public String profilePage(Model model, HttpServletRequest request) {
        model.addAttribute("user", getSessionUser(request));
        return "admin/profile";
    }

    public User getSessionUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("loggedInUser");
    }

    @PostMapping("/profile/update")
    public String updateUser(@ModelAttribute User user, HttpServletRequest request) {
        User currentUser = getSessionUser(request);
        currentUser.setAddress(user.getAddress());
        currentUser.setFullName(user.getFullName());
        currentUser.setNumber(user.getNumber());
        userService.updateUser(currentUser);
        return "redirect:/admin/profile";
    }

    @GetMapping("/account")
    public String manageAccountPage(Model model) {
        model.addAttribute("listVaiTro", roleService.findAllRole());
        return "admin/manageAccount";
    }

    @GetMapping("/statistic")
    public String statisticPage(Model model) {
        return "admin/statistic";
    }
}
