package com.gyeongju.dogshow.service;

import com.gyeongju.dogshow.dao.DaoDog;
import com.gyeongju.dogshow.entities.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  DaoDog daoDog;

  @Override
  public UserDetails loadUserByUsername(String handlerName) throws UsernameNotFoundException {
    Handler handler = daoDog.findUserAccount(handlerName);
    if(handler == null) {
      System.out.println("User not found " );
      throw new UsernameNotFoundException("User: " + handlerName + "was not found in the dataabse");
    }

    System.out.println("Found User: " + handlerName);

    List<GrantedAuthority> grantList = new ArrayList<>();
    GrantedAuthority authority = new SimpleGrantedAuthority(handler.getRoleName());
    grantList.add(authority);

    UserDetails userDetails = (UserDetails) new User(handler.getHandlerName(), handler.getEncryptedPassword(),
            grantList);

    return userDetails;
  }
}
