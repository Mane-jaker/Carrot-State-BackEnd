package com.example.carrotstatebackend.security.users;

import com.example.carrotstatebackend.entities.Agent;
import com.example.carrotstatebackend.exceptions.LoginInvalidException;
import com.example.carrotstatebackend.services.interfaces.persons.IAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("AgentDetails")
public class AgentUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IAgentService agentService;

    @Override
    public UserDetails loadUserByUsername(String email) throws LoginInvalidException {
        Agent agent = agentService.getAgent(email).orElseThrow(LoginInvalidException::new);
        return new AgentUserDetailsImpl(agent);
    }
}
