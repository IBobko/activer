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
package ru.todo100.activer.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author Igor Bobko
 */
@MappedSuperclass
public abstract class DateChanges extends Item
{
	@Column(name="created_date")
	private Calendar createdDate;

	public Calendar getCreatedDate()
	{
		return createdDate;
	}

	public void setCreatedDate(final Calendar createdDate)
	{
		this.createdDate = createdDate;
	}

}
