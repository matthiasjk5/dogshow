package com.gyeongju.dogshow.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Handler {
  private String handlerName;
  private String encryptedPassword;
  private String roleName;
}
