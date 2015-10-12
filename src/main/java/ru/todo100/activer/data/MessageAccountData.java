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
package ru.todo100.activer.data;

/**
 * @author igor
 * @package ru.todo100.activer.data
 * @link http://www.novardis.com/
 * @copyright 2015 NOVARDIS
 */
public class MessageAccountData
{
	private String firstName;
	private String lastName;

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(final String lastName)
	{
		this.lastName = lastName;
	}

	public String getFirstName()
	{

		return firstName;
	}

	public void setFirstName(final String firstName)
	{
		this.firstName = firstName;
	}
}
