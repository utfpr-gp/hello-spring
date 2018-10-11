/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.hello;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ronifabio
 */
@Controller
@RequestMapping("/mensagens")
public class MessageController {

    @Autowired
    MessageService messageService;

    @RequestMapping(value = {"/falar", "/f"}, method = RequestMethod.GET)
    public ModelAndView messageParam(@RequestParam("m") String msg) {
        ModelAndView mv = new ModelAndView("message.jsp");
        mv.addObject("msg", msg);
        return mv;
    }

    @RequestMapping(path = "/falar/{m}", method = RequestMethod.GET)
    public ModelAndView messageVariable(@PathVariable("m") String msg) {
        ModelAndView mv = new ModelAndView("message.jsp");
        mv.addObject("msg", msg);
        return mv;
    }

    @RequestMapping(value = "/gritar", method = RequestMethod.GET)
    public String messageModel(@RequestParam("m") String msg, Model model) {
        model.addAttribute("msg", msg.toUpperCase());
        return "message.jsp";
    }

    @RequestMapping(path = "/form", method = RequestMethod.GET)
    public ModelAndView showForm() {
        ModelAndView mv = new ModelAndView("form.jsp", "m", new Message());
        return mv;
    }

    @RequestMapping(path = "/form", method = RequestMethod.POST)
    public ModelAndView receiveForm(@ModelAttribute("m") Message message) {
        //persiste no H2
        messageService.save(message);
        //encaminha para mostrar a mensagem
        ModelAndView mv = new ModelAndView("message.jsp");
        mv.addObject("msg", "A pessoa " + message.getName()
                + " mandou a mensagem " + message.getMessage());
        return mv;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showMessageList() {

        List<Message> messages = messageService.findAll();

        ModelAndView mv = new ModelAndView("list-messages.jsp", "messages", messages);
        return mv;
    }

}
