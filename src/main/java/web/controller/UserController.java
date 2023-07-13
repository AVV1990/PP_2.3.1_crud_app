package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/show_formToAdd")
    public String showPageToAddUser() {
        return "formToAdd";
    }

    @GetMapping(value = "/show_formToFindUserById")
    public String showPageToFindUserById() {
        return "formToFindUserById";
    }

    @GetMapping(value = "/user_add")
    public String addUser(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int age) {
        User user = new User(firstName, lastName, age);
        userService.addUser(user);
        return "users";
    }

    @GetMapping(value = "/getUsers")
    public String getUsers(ModelMap model) {
        model.addAttribute("users", userService.getUsers());
        return "users";
    }

    @GetMapping(value = "/user_getUserById")
    public String getUserById(ModelMap model, @RequestParam Long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "findUserById";
    }


    @GetMapping(value = "/user_deleteUserById")
    public String deleteUserById(ModelMap model, @RequestParam Long id) {
        userService.deleteUserById(id);
        return "users";
    }

    @GetMapping(value = "/user_updateUser")
    public String updateUserById(ModelMap model, @RequestParam Long id, User user) {
        userService.updateUser(id, user);
        return "users";
    }
}
