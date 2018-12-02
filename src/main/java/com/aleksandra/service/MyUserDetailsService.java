/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.service;

import com.aleksandra.dao.implementation.ZaposleniDAO;
import com.aleksandra.domen.Zaposleni;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("LOAD BY USERNAME");
        System.out.println("username: " + username);
        ZaposleniService zaposleniS = new ZaposleniService();
        ZaposleniDAO zaposleniDAO = new ZaposleniDAO();
        Zaposleni zaposleni = zaposleniDAO.pronadjiZaposlenog(username);
        List<GrantedAuthority> auth = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");
        UserDetails userDetails = new User(zaposleni.getKorisnickoIme(), zaposleni.getLozinka(), auth);
        return userDetails;
    }

}
