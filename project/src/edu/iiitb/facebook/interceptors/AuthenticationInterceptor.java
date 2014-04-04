package edu.iiitb.facebook.interceptors;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.Interceptor;

import edu.iiitb.facebook.util.Constants;

public class AuthenticationInterceptor implements Interceptor
{
	private static final long serialVersionUID = -2799348281841811478L;

	@Override
	public void destroy()
	{
	}

	@Override
	public void init()
	{
	}

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception
	{

		Map<String, Object> session = actionInvocation.getInvocationContext()
				.getSession();

		if (session.get(Constants.USER) == null)
		{
			return ActionSupport.LOGIN;
		}
		return actionInvocation.invoke();
	}

}
