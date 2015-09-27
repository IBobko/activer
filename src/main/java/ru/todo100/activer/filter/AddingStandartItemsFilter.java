package ru.todo100.activer.filter;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.GenericFilterBean;

import ru.todo100.activer.form.SearchForm;
import ru.todo100.activer.model.CategoryItem;
import ru.todo100.activer.model.CountryItem;
import ru.todo100.activer.service.CategoryService;
import ru.todo100.activer.service.CountryService;
import ru.todo100.activer.service.MarkService;

@Transactional
@SuppressWarnings(value = {"unchecked"})
public class AddingStandartItemsFilter extends GenericFilterBean
{
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private CountryService  countryService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
					throws IOException, ServletException
	{
		request.setAttribute("countries", getCountry());
		request.setAttribute("months", getMonth());
		request.setAttribute("categories", getCategories());
		request.setAttribute("searchForm", new SearchForm());
		filterChain.doFilter(request, response);
	}

	private SortedMap<String, String> getCountry()
	{
		Criteria criteria = countryService.getCriteria();
		List<CountryItem> countriesList = (List<CountryItem>) criteria.list();
		SortedMap<String, String> countriesMap = new TreeMap<>();
		for (CountryItem item : countriesList)
		{
			countriesMap.put(item.getCode(), item.getName());
		}
		return countriesMap;
	}

	private String[] getMonth()
	{
		DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
		String[] months = dateFormatSymbols.getMonths();
		String[] newMonths = new String[12];
		System.arraycopy(months, 0, newMonths, 0, newMonths.length);
		return newMonths;
	}

	private List<CategoryItem> getCategories()
	{
		final Criteria criteria = categoryService.getCriteria();
		criteria.add(Restrictions.or(Restrictions.isNull("parent"), Restrictions.eq("parent", 0l)));
		return criteria.list();
	}
}
