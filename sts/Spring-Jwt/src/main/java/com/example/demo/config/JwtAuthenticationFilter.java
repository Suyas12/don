package com.example.demo.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.helper.JwtHelper;
import com.example.demo.services.CustomUserDetailsService;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtHelper jwtHelper;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		//get Jwt
		//Starting with Bearer
		//Validate
		String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String JwtToken = null;
		
		//null and format
		if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")) 
		{
			JwtToken=requestTokenHeader.substring(7);
			try {
				
				username=this.jwtHelper.extractUsername(JwtToken);
				
			}catch(Exception e) 
			{
				e.printStackTrace();
			}
			UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);
			//**Security**
			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) 
			{
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticatonToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				usernamePasswordAuthenticatonToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticatonToken);
			}
			else {
				System.out.println("Invalid Token");
			}
			}
		filterChain.doFilter(request, response);
	}

}
