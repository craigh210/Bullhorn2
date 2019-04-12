package com.cwhcode.bullhorn2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    MessageRepository messageRepository;

    @RequestMapping("/")
    public String listPosts(Model model) {
        model.addAttribute("messages", messageRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String newPost(Model model) {
        model.addAttribute("message", new Message());
        return "newpost";
    }

    @PostMapping("/process")
    public String processPost(@Valid Message message, BindingResult result)
    {
        if (result.hasErrors()) {
            return "newpost";
        }
        messageRepository.save(message);
        return "redirect:/";
    }

    /*@RequestMapping("/delete/{id}")
    public String delPost(@PathVariable("id") long id)
    {
        messageRepository.deleteById(id);
        return "redirect:/";
    }*/

}
