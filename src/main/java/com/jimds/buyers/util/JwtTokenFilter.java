package com.jimds.buyers.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jimds.buyers.exceptions.InvalidJwtAuthenticationException;
import com.jimds.buyers.exceptions.StandardError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JwtTokenFilter extends GenericFilterBean {
    private JWTHandler jwtHandler;

    public JwtTokenFilter(JWTHandler jwtTokenProvider) {
        this.jwtHandler = jwtTokenProvider;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        try{
            String token = jwtHandler.resolveToken(req);
            if(token !=null && jwtHandler.validateToke(token)){
                Authentication auth = jwtHandler.getAuthentication(token);

                String refreshedToken = jwtHandler.createRefreshToken(token);
                res.setHeader("Access-Control-Expose-Headers", "Authorization");
                res.setHeader("Authorization","Bearer "+refreshedToken);

                if(auth != null){
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }



        }
        catch(InvalidJwtAuthenticationException ex){
            StandardError error = new StandardError(HttpStatus.UNAUTHORIZED.value(), "Token inválido ou expirado!", ex.getMessage(), req.getHeader("Referer"));
            res.setStatus(error.getStatus());
            res.setContentType("application/json");

            ObjectMapper mapper = new ObjectMapper();
            PrintWriter out = res.getWriter();
            String json = mapper.writeValueAsString(error);
            out.print(json);
            out.flush();
            return;
        }
        chain.doFilter(req,res);
    }
}
