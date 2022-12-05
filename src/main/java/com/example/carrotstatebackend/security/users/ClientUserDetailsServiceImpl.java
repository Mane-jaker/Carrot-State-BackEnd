package com.example.carrotstatebackend.security.users;

import com.example.carrotstatebackend.entities.Client;
import com.example.carrotstatebackend.exceptions.LoginInvalidException;
import com.example.carrotstatebackend.services.interfaces.persons.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("ClientDetails")
public class ClientUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IClientService clientService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client client = clientService.getClient(email).orElseThrow(LoginInvalidException::new);
        return new ClientUserDetailsImpl(client);
    }
}
