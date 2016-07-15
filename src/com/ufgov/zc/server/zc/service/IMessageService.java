package com.ufgov.zc.server.zc.service;

import java.util.List;

public interface IMessageService {

  public boolean isCgzxGroup(String userId);

  public List getMessage(String userId);

  public void logout(String userId);
}
