package org.arip.controller;

import org.arip.domain.User;
import org.arip.service.UserService;
import org.arip.util.ContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Arip Hidayat on 18/04/2016.
 */
@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @RequestMapping(value = "/{schema}", method = RequestMethod.GET)
    public String getUsers(Model model, @PathVariable("schema") String schema) {
        ContextHolder.setContext(schema);

        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "user/list";
    }

    @RequestMapping(value = "/{schema}/new", method = RequestMethod.GET)
    public String create(Model model, @PathVariable("schema") String schema) {
        User user = new User();

        model.addAttribute("user", user);
        return "user/form";
    }

    @RequestMapping(value = "/{schema}/save", method = RequestMethod.POST)
    public String save(Model model, @PathVariable("schema") String schema, @ModelAttribute User user) {
        ContextHolder.setContext(schema);

        userService.save(user);
        return "redirect:/"+ schema;
    }
}
