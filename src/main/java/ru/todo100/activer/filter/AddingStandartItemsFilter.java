package ru.todo100.activer.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.web.filter.GenericFilterBean;

import ru.todo100.activer.form.SearchForm;

public class AddingStandartItemsFilter extends GenericFilterBean
{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
					throws IOException, ServletException
	{
		request.setAttribute("searchForm", new SearchForm());
		filterChain.doFilter(request, response);
	}
}
