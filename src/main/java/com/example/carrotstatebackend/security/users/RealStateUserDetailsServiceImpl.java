package com.example.carrotstatebackend.security.users;

import com.example.carrotstatebackend.entities.RealState;
import com.example.carrotstatebackend.services.interfaces.persons.IRealStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("RealStateDetails")
public class RealStateUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IRealStateService realStateService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        RealState realState = realStateService.getRealState(email);
        return new RealStateUserDetailsImpl(realState);
    }
}
