package ru.todo100.activer.data;

/**
 * @author Igor Bobko
 */
public class ProfileData
{
	private Integer id;
	private String  firstName;
	private String  lastName;
	private String  facePhotoUrl;

	public String getFacePhotoUrl()
	{
		return facePhotoUrl;
	}

	public void setFacePhotoUrl(final String facePhotoUrl)
	{
		this.facePhotoUrl = facePhotoUrl;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(final Integer id)
	{
		this.id = id;
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
