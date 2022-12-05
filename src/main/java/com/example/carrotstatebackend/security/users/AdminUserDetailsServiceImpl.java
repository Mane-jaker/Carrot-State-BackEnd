package com.example.carrotstatebackend.security.users;

import com.example.carrotstatebackend.entities.Admin;
import com.example.carrotstatebackend.exceptions.LoginInvalidException;
import com.example.carrotstatebackend.services.interfaces.persons.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service("AdminDetails")
public class AdminUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IAdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String email) throws LoginInvalidException {
        Admin admin = adminService.getAdmin(email);
        return new AdminUserDetailsImpl(admin);
    }
}
