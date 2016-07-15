package com.ufgov.zc.server.zc.publish.impl;

import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.zc.publish.IMessageDelegate;
import com.ufgov.zc.server.zc.service.IMessageService;

public class MessageDelegate implements IMessageDelegate {
  private IMessageService messageService;

  public IMessageService getMessageService() {
    return messageService;
  }

  public void setMessageService(IMessageService messageService) {
    this.messageService = messageService;
  }

  
  public boolean isCgzxGroup(String userId, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    return messageService.isCgzxGroup(userId);
  }

  
  public List getMessage(String userId, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    return messageService.getMessage(userId);
  }

  
  public void logout(String userId, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    messageService.logout(userId);
  }

}
