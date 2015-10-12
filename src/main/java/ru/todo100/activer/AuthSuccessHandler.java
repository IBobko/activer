/*********************************************************************
 * The Initial Developer of the content of this file is NOVARDIS.
 * All portions of the code written by NOVARDIS are property of
 * NOVARDIS. All Rights Reserved.
 * <p>
 * NOVARDIS
 * Detskaya st. 5A, 199106
 * Saint Petersburg, Russian Federation
 * Tel: +7 (812) 331 01 71
 * Fax: +7 (812) 331 01 70
 * www.novardis.com
 * <p>
 * (c) 2015 by NOVARDIS
 *********************************************************************/
package ru.todo100.activer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import ru.todo100.activer.config.Environment;

/**
 * @author Igor Bobko
 */

public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler
{

	@Override
	public void onAuthenticationSuccess(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse,
	                                    final Authentication authentication) throws IOException, ServletException
	{
		Environment.terrarium = authentication;
		super.onAuthenticationSuccess(httpServletRequest,httpServletResponse,authentication);


	}
}
