/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.hello;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ronifabio
 */
@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    public void save(Message message) {
        messageRepository.save(message);
    }

    public List<Message> findAll() {
        return messageRepository.findAll();
    }

}
