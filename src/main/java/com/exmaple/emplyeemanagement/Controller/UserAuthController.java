package com.exmaple.emplyeemanagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exmaple.emplyeemanagement.Entity.UserAuthEntity;
import com.exmaple.emplyeemanagement.Service.UserAuthEntityService;

@RestController
@RequestMapping("/auth")
 public class UserAuthController {

 @Autowired
 private UserAuthEntityService userAuthEntityService;

 @Autowired
 private PasswordEncoder passwordEncoder;

 @PostMapping("/register")
 public ResponseEntity<String> register(
 @RequestBody UserAuthEntity userAuthDetails) {

 // Hash the password before saving
 userAuthDetails.setPassword(
 /*
 Encode the password before storing it in DB.

Now, by-default in spring boot security, all the endpoints are AUTHENTICATED, means we have to authenticate
ourself by either username/password or JWT etc.. To access any API, so how we will access "/auth/register" API,
which is just a first step to create user.
Yes, we have to relax the authentication for this API and its industry standard.

SecurityConfig.java

19 */
 passwordEncoder.encode(userAuthDetails.getPassword())
 );

 // Save user
 userAuthEntityService.save(userAuthDetails);

 return ResponseEntity.ok("User registered successfully!");
 }
 }
