package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;


@Controller
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String printUser(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @PostMapping("/addUser")
    public String addUser(@RequestParam("age") Integer age,
                          @RequestParam("name") String name,
                          @RequestParam("surname") String surname) {
        User user = new User();
        user.setAge(age);
        user.setName(name);
        user.setSurname(surname);
        userService.save(user);
        return "redirect:/";
    }

    @PostMapping("/deleteUser")
    public String delete(@RequestParam("id") int id) {
        User user = userService.getByIdUser(id);
        if (user != null) {
            userService.delete(user);
        }
        return "redirect:/";
    }

    @PostMapping("/updateUser")
    public String update(@ModelAttribute User user) {
        userService.update(user);
        return "redirect:/";
    }
}
