package com.ufgov.zc.common.zc.publish;

import java.util.List;

import com.ufgov.zc.common.system.Publishable;
import com.ufgov.zc.common.system.RequestMeta;

public interface IMessageDelegate extends Publishable {

	public boolean isCgzxGroup(String userId, RequestMeta meta);

	public List getMessage(String userId, RequestMeta meta);

	public void logout(String userId, RequestMeta meta);
}
