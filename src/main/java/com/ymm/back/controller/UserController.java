package com.ymm.back.controller;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.ymm.back.dto.JwtDTO;
import com.ymm.back.entity.UserEntity;
import com.ymm.back.service.CustomUserDetailsService;
import com.ymm.back.service.UserService;
import com.ymm.back.utils.JwtUtil;
import com.ymm.back.utils.StaticVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(allowCredentials = "true")
@RequestMapping(value = "/api")
public class UserController {
	@Autowired
    UserService userService;
	@Autowired
    private JwtUtil jwtTokenUtil;
	@Autowired
    private CustomUserDetailsService userDetailsService;
	@Autowired
    private AuthenticationManager authenticationManager;
	@PostMapping(value = "/user/register")
    public ResponseEntity<?> createUser(@RequestBody UserEntity user){
        
		System.out.println(user.toString());
        // Check if there are matched in DB
//        if ((userService.checkIfUserNameOrMailAlreadyUsed((String) json.get("firstname"), (String) json.get("email")))) {
//            return ResponseEntity.badRequest().body("Username or mail already used, please try again");
//        }
        
		
		
		
        user.setPassword(userService.passwordEncoder(user.getPassword()));
        user.setWstoken(UUID.randomUUID().toString());
        user.setRole(1);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setEnabled(true);
        user.setCredentialsNonExpired(true);
        System.out.println(user);
        userService.save(user);
        return ResponseEntity.status(200).build();
    }
	
	@PostMapping(value = "/auth")
    public UserEntity createAuthenticationToken(@RequestBody JwtDTO authenticationRequest, HttpServletResponse response) throws Exception {
		
		System.out.println(authenticationRequest.toString());
		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        System.out.println(userDetails);
        UserEntity user=userService.findByNameOrEmail(authenticationRequest.getEmail(), authenticationRequest.getEmail());
        String token = jwtTokenUtil.generateToken(userDetails);
        Cookie jwtAuthToken = new Cookie(StaticVariable.SECURE_COOKIE, token);
        jwtAuthToken.setHttpOnly(true);
        jwtAuthToken.setSecure(false);
        jwtAuthToken.setPath("/");
        jwtAuthToken.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(jwtAuthToken);
        return user;
    }
	private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            
        } catch (DisabledException e) {
        	System.out.println("disabled");
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
        	System.out.println("invalid");
            throw new Exception("INVALID_CREDENTIALS", e);
        }catch(Exception e) {
        	e.printStackTrace();
        }
    }
	
}
