package ru.todo100.activer.data;

import java.util.List;

/**
 * @author Igor Bobko
 */
public class ProfileData
{
	private Integer id;
	private String  firstName;
	private String  lastName;
	private String  facePhotoUrl;

	public String getPhoto60x60()
	{
		return photo60x60;
	}

	public void setPhoto60x60(final String photo60x60)
	{
		this.photo60x60 = photo60x60;
	}

	private String            photo60x60;
	private List<ProfileData> friends;

	public List<ProfileData> getFriends()
	{
		return friends;
	}

	public void setFriends(final List<ProfileData> friends)
	{
		this.friends = friends;
	}

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
