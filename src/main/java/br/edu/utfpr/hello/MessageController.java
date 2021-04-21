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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author ronifabio
 */
@Controller
@RequestMapping("/mensagens")
public class MessageController {

    @Autowired
    MessageService messageService;

    //chamada: /mensagens/falar?m=oi
    @RequestMapping(value = {"/falar", "/f"}, method = RequestMethod.GET)
    public ModelAndView messageParam(@RequestParam("m") String msg) {
        ModelAndView mv = new ModelAndView("message.jsp");
        mv.addObject("msg", msg);
        return mv;
    }

    //chamada: /mensagens/falar/oi
    @RequestMapping(path = "/falar/{m}", method = RequestMethod.GET)
    public ModelAndView messageVariable(@PathVariable("m") String msg) {
        ModelAndView mv = new ModelAndView("message.jsp");
        mv.addObject("msg", msg);
        return mv;
    }

    //chamada: /mensagens/gritar?m=oi
    @RequestMapping(value = "/gritar", method = RequestMethod.GET)
            public String messageModel(@RequestParam("m") String msg, Model model) {
        model.addAttribute("msg", msg.toUpperCase());
        return "message.jsp";
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showMessageList() {

        List<Message> messages = messageService.findAll();

        ModelAndView mv = new ModelAndView("list-messages.jsp", "messages", messages);
        return mv;
    }

    @RequestMapping(path = "/form", method = RequestMethod.GET)
    public ModelAndView showForm() {
        ModelAndView mv = new ModelAndView("form.jsp", "m", new Message("Roni", "oi"));
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

    @RequestMapping(path = "/form-redirect", method = RequestMethod.POST)
    public RedirectView persist(@ModelAttribute("m") Message message, RedirectAttributes redirectAttributes) {
        messageService.save(message);
        //atributo é guardado na sessão temporariamente
        redirectAttributes.addFlashAttribute("msg", "Cadastro feito com sucesso!");
        //atributo é enviado como query parameter
        redirectAttributes.addAttribute("status", "success");
        return new RedirectView("/mensagens", true);
    }

    @RequestMapping(path = "/form-redirect-string", method = RequestMethod.POST)
    public String persistRedirect(@ModelAttribute("m") Message message, RedirectAttributes redirectAttributes) {
        messageService.save(message);
        redirectAttributes.addFlashAttribute("msg", "Cadastro feito com sucesso!");
        return "redirect:/mensagens";
    }


}
