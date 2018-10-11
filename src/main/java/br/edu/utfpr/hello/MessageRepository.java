/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.hello;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ronifabio
 */
public interface MessageRepository
        extends JpaRepository<Message, Long> {

}
