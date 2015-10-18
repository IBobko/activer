package ru.todo100.activer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;



/**
 * @author Igor Bobko
 */

public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler
{

	@Override
	public void onAuthenticationSuccess(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse,
	                                    final Authentication authentication) throws IOException, ServletException
	{
		super.onAuthenticationSuccess(httpServletRequest,httpServletResponse,authentication);
	}
}
