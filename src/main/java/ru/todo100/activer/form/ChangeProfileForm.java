package ru.todo100.activer.form;

/**
 * @author Igor Bobko
 */
@SuppressWarnings("unused")
public class ChangeProfileForm
{
	private String firstName;
	private String lastName;
	private String password;
	private String repeatPassword;
	private String email;

	public String getEmail()
	{
		return email;
	}

	public void setEmail(final String email)
	{
		this.email = email;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(final String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(final String lastName)
	{
		this.lastName = lastName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(final String password)
	{
		this.password = password;
	}

	public String getRepeatPassword()
	{
		return repeatPassword;
	}

	public void setRepeatPassword(final String repeatPassword)
	{
		this.repeatPassword = repeatPassword;
	}
}
