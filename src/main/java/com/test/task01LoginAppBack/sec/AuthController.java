package com.test.task01LoginAppBack.sec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/auth")
//@CrossOrigin("http://localhost:4201")
public class AuthController {
    @Autowired
    private TokenUtile tokenUtils;
    @Autowired
    private SecUserService secUserService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = {"", "/singIn"})
    public JwtResponse singIn(@RequestParam String username, @RequestParam String password) throws Exception {
        System.out.println("===========> start authentication controller");
        Authentication authentication = null;
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));

        } catch (DisabledException dis) {
            return new JwtResponse(null, null, null, "USER_DISABLED");
        } catch (BadCredentialsException e) {
            return new JwtResponse(null, null, null, "INVALID_CREDENTIALS");
        }

        System.out.println("===========> check authentication controller: " + authentication.isAuthenticated());
        if (authentication.isAuthenticated()) {
            System.out.println("===========> check authentication inside controller: " + authentication.isAuthenticated());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = secUserService.loadUserByUsername(username);
            Optional<SecUser> user = secUserService.findByUsername(username);
            String token = tokenUtils.generateToken(userDetails);
            return new JwtResponse(token, username, user.get().getRoles(), "SUCCESS");
        }

        return new JwtResponse(null, null, null, "INVALID");
    }

    @GetMapping("/users")
    public List<SecUser> getAllUsers() {
        return secUserService.getAllUsers();
    }

}
