package ru.todo100.activer.data;

/**
 * @author Igor Bobko
 */
public class MessageAccountData
{
	private String firstName;
	private String lastName;
	private String photo60x60;

	public String getPhoto60x60()
	{
		return photo60x60;
	}

	public void setPhoto60x60(final String photo60x60)
	{
		this.photo60x60 = photo60x60;
	}

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
