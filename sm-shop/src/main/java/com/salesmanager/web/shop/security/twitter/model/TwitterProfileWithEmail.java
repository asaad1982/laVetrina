package com.salesmanager.web.shop.security.twitter.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.social.twitter.api.TwitterObject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TwitterProfileWithEmail extends TwitterObject
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private final long id;
  private final String screenName;
  private final String name;
  private final String url;
  private final String profileImageUrl;
  private final String description;
  private final String location;
  private Date createdDate;
  private String language;
  private int statusesCount;
  private int friendsCount;
  private int followersCount;
  private int favoritesCount;
  private int listedCount;
  private boolean following;
  private boolean followRequestSent;
  private boolean isProtected;
  private boolean notificationsEnabled;
  private boolean verified;
  private boolean geoEnabled;
  private boolean contributorsEnabled;
  private boolean translator;
  private String timeZone;
  private int utcOffset;
  private String sidebarBorderColor;
  private String sidebarFillColor;
  private String backgroundColor;
  private boolean useBackgroundImage;
  private String backgroundImageUrl;
  private boolean backgroundImageTiled;
  private String textColor;
  private String linkColor;
  private boolean showAllInlineMedia;
  private String profileBannerUrl;

  private String email;
  
  public static long getSerialversionuid()
  {
    return 1L;
  }

  public TwitterProfileWithEmail(long id, String screenName, String name, String url, String profileImageUrl, String description, String location, Date createdDate) {
	    this.id = id;
	    this.screenName = screenName;
	    this.name = name;
	    this.url = url;
	    this.profileImageUrl = profileImageUrl;
	    this.description = description;
	    this.location = location;
	    this.createdDate = createdDate;
	  }

  @JsonCreator
  public TwitterProfileWithEmail(@JsonProperty("id")long id, @JsonProperty("screen_name")String screenName, @JsonProperty("name")String name, @JsonProperty("url")String url, @JsonProperty("profile_image_url")String profileImageUrl, @JsonProperty("description")String description, @JsonProperty("location")String location, @JsonProperty("email")String email) {
    this.id = id;
    this.screenName = screenName;
    this.name = name;
    this.url = url;
    this.profileImageUrl = profileImageUrl;
    this.description = description;
    this.location = location;
    this.email = email;
  }

  public long getId()
  {
    return this.id;
  }

  public String getScreenName()
  {
    return this.screenName;
  }

  public String getName()
  {
    return this.name;
  }

  public String getUrl()
  {
    return this.url;
  }

  public String getDescription()
  {
    return this.description;
  }

  public String getLocation()
  {
    return this.location;
  }

  public String getProfileImageUrl()
  {
    return this.profileImageUrl;
  }

  public String getProfileUrl()
  {
    return "http://twitter.com/" + this.screenName;
  }

  public Date getCreatedDate()
  {
    return this.createdDate;
  }

  public boolean isNotificationsEnabled()
  {
    return this.notificationsEnabled;
  }

  public boolean isVerified()
  {
    return this.verified;
  }

  public boolean isGeoEnabled()
  {
    return this.geoEnabled;
  }

  public String getLanguage()
  {
    return this.language;
  }

  public int getStatusesCount()
  {
    return this.statusesCount;
  }

  public int getListedCount()
  {
    return this.listedCount;
  }

  public int getFriendsCount()
  {
    return this.friendsCount;
  }

  public int getFollowersCount()
  {
    return this.followersCount;
  }

  public boolean isFollowing()
  {
    return this.following;
  }

  public boolean isFollowRequestSent()
  {
    return this.followRequestSent;
  }

  public int getFavoritesCount()
  {
    return this.favoritesCount;
  }

  public boolean isProtected()
  {
    return this.isProtected;
  }

  public String getTimeZone()
  {
    return this.timeZone;
  }

  public int getUtcOffset()
  {
    return this.utcOffset;
  }

  public boolean isContributorsEnabled()
  {
    return this.contributorsEnabled;
  }

  public boolean isTranslator()
  {
    return this.translator;
  }

  public String getSidebarBorderColor()
  {
    return this.sidebarBorderColor;
  }

  public String getSidebarFillColor()
  {
    return this.sidebarFillColor;
  }

  public String getBackgroundColor()
  {
    return this.backgroundColor;
  }

  public boolean useBackgroundImage()
  {
    return this.useBackgroundImage;
  }

  public String getBackgroundImageUrl()
  {
    return this.backgroundImageUrl;
  }

  public boolean isBackgroundImageTiled()
  {
    return this.backgroundImageTiled;
  }

  public String getTextColor()
  {
    return this.textColor;
  }

  public String getLinkColor()
  {
    return this.linkColor;
  }

  public String getProfileBannerUrl()
  {
    return this.profileBannerUrl;
  }

  public boolean showAllInlineMedia()
  {
    return this.showAllInlineMedia;
  }

  public boolean equals(Object o)
  {
    if (this == o) {
      return true;
    }
    if ((o == null) || (getClass() != o.getClass())) {
      return false;
    }

    TwitterProfileWithEmail that = (TwitterProfileWithEmail)o;

    if (this.backgroundImageTiled != that.backgroundImageTiled) {
      return false;
    }
    if (this.contributorsEnabled != that.contributorsEnabled) {
      return false;
    }
    if (this.favoritesCount != that.favoritesCount) {
      return false;
    }
    if (this.followRequestSent != that.followRequestSent) {
      return false;
    }
    if (this.followersCount != that.followersCount) {
      return false;
    }
    if (this.following != that.following) {
      return false;
    }
    if (this.friendsCount != that.friendsCount) {
      return false;
    }
    if (this.geoEnabled != that.geoEnabled) {
      return false;
    }
    if (this.id != that.id) {
      return false;
    }
    if (this.isProtected != that.isProtected) {
      return false;
    }
    if (this.listedCount != that.listedCount) {
      return false;
    }
    if (this.notificationsEnabled != that.notificationsEnabled) {
      return false;
    }
    if (this.showAllInlineMedia != that.showAllInlineMedia) {
      return false;
    }
    if (this.statusesCount != that.statusesCount) {
      return false;
    }
    if (this.translator != that.translator) {
      return false;
    }
    if (this.useBackgroundImage != that.useBackgroundImage) {
      return false;
    }
    if (this.utcOffset != that.utcOffset) {
      return false;
    }
    if (this.verified != that.verified) {
      return false;
    }
    if (this.backgroundColor != null ? !this.backgroundColor.equals(that.backgroundColor) : that.backgroundColor != null) {
      return false;
    }
    if (this.backgroundImageUrl != null ? !this.backgroundImageUrl.equals(that.backgroundImageUrl) : that.backgroundImageUrl != null) {
      return false;
    }
    if (this.createdDate != null ? !this.createdDate.equals(that.createdDate) : that.createdDate != null) {
      return false;
    }
    if (this.description != null ? !this.description.equals(that.description) : that.description != null) {
      return false;
    }
    if (this.language != null ? !this.language.equals(that.language) : that.language != null) {
      return false;
    }
    if (this.linkColor != null ? !this.linkColor.equals(that.linkColor) : that.linkColor != null) {
      return false;
    }
    if (this.location != null ? !this.location.equals(that.location) : that.location != null) {
      return false;
    }
    if (this.name != null ? !this.name.equals(that.name) : that.name != null) {
      return false;
    }
    if (this.profileImageUrl != null ? !this.profileImageUrl.equals(that.profileImageUrl) : that.profileImageUrl != null) {
      return false;
    }
    if (this.screenName != null ? !this.screenName.equals(that.screenName) : that.screenName != null) {
      return false;
    }
    if (this.email != null ? !this.email.equals(that.email) : that.email != null) {
        return false;
    }
    if (this.sidebarBorderColor != null ? !this.sidebarBorderColor.equals(that.sidebarBorderColor) : that.sidebarBorderColor != null) {
      return false;
    }
    if (this.sidebarFillColor != null ? !this.sidebarFillColor.equals(that.sidebarFillColor) : that.sidebarFillColor != null) {
      return false;
    }
    if (this.textColor != null ? !this.textColor.equals(that.textColor) : that.textColor != null) {
      return false;
    }
    if (this.timeZone != null ? !this.timeZone.equals(that.timeZone) : that.timeZone != null) {
      return false;
    }
    if (this.url != null ? !this.url.equals(that.url) : that.url != null) {
      return false;
    }

    return true;
  }

  public int hashCode()
  {
    int result = (int)(this.id ^ this.id >>> 32);
    result = 31 * result + (this.screenName != null ? this.screenName.hashCode() : 0);
    result = 31 * result + (this.email != null ? this.email.hashCode() : 0);
    result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
    result = 31 * result + (this.url != null ? this.url.hashCode() : 0);
    result = 31 * result + (this.profileImageUrl != null ? this.profileImageUrl.hashCode() : 0);
    result = 31 * result + (this.description != null ? this.description.hashCode() : 0);
    result = 31 * result + (this.location != null ? this.location.hashCode() : 0);
    result = 31 * result + (this.createdDate != null ? this.createdDate.hashCode() : 0);
    result = 31 * result + (this.language != null ? this.language.hashCode() : 0);
    result = 31 * result + this.statusesCount;
    result = 31 * result + this.friendsCount;
    result = 31 * result + this.followersCount;
    result = 31 * result + this.favoritesCount;
    result = 31 * result + this.listedCount;
    result = 31 * result + (this.following ? 1 : 0);
    result = 31 * result + (this.followRequestSent ? 1 : 0);
    result = 31 * result + (this.isProtected ? 1 : 0);
    result = 31 * result + (this.notificationsEnabled ? 1 : 0);
    result = 31 * result + (this.verified ? 1 : 0);
    result = 31 * result + (this.geoEnabled ? 1 : 0);
    result = 31 * result + (this.contributorsEnabled ? 1 : 0);
    result = 31 * result + (this.translator ? 1 : 0);
    result = 31 * result + (this.timeZone != null ? this.timeZone.hashCode() : 0);
    result = 31 * result + this.utcOffset;
    result = 31 * result + (this.sidebarBorderColor != null ? this.sidebarBorderColor.hashCode() : 0);
    result = 31 * result + (this.sidebarFillColor != null ? this.sidebarFillColor.hashCode() : 0);
    result = 31 * result + (this.backgroundColor != null ? this.backgroundColor.hashCode() : 0);
    result = 31 * result + (this.useBackgroundImage ? 1 : 0);
    result = 31 * result + (this.backgroundImageUrl != null ? this.backgroundImageUrl.hashCode() : 0);
    result = 31 * result + (this.backgroundImageTiled ? 1 : 0);
    result = 31 * result + (this.textColor != null ? this.textColor.hashCode() : 0);
    result = 31 * result + (this.linkColor != null ? this.linkColor.hashCode() : 0);
    result = 31 * result + (this.showAllInlineMedia ? 1 : 0);
    return result;
  }

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}
}