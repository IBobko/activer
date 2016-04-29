package ru.todo100.activer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.data.ProfileData;


/**
 * @author Igor Bobko
 */

public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler
{
	public AccountDao getAccountService() {
		return accountService;
	}

	@Autowired
	public void setAccountService(AccountDao accountService) {
		this.accountService = accountService;
	}

	private AccountDao accountService;
	@Override
	public void onAuthenticationSuccess(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse,
	                                    final Authentication authentication) throws IOException, ServletException
	{
		getAccountService().initCurrentProfile(httpServletRequest.getSession());

		super.onAuthenticationSuccess(httpServletRequest,httpServletResponse,authentication);
	}
}
