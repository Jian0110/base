package com.lijian.controller;

import com.lijian.entity.User;
import com.lijian.service.impl.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author: Jian
 * @desrciption:
 * @date: Created in 0:31 2018/11/4
 * @Modified By:
 */
@Controller
@RequestMapping("user")
public class UserController {
    private static final Logger log = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/users-list")
    public ModelAndView listUsers(){
        System.out.println("============");
        log.info("listUsers()");
        List<User> users = userService.listUsers();
        ModelAndView mv = new ModelAndView();
        mv.addObject(users);
        mv.setViewName("user-list");
        return mv;
    }
}
