package org.jboss.seam.social.examples.foobarter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.seam.social.Twitter;
import org.jboss.seam.social.twitter.TwitterFriendService;
import org.jboss.seam.social.twitter.TwitterUserService;

@Named
@SessionScoped
public class FriendBean implements Serializable {
	
	Set<Long> friendIds;
	
	@Inject
	@Twitter
	TwitterFriendService friendService;
	
	@Inject
	@Twitter
	TwitterUserService userService;
	
	private Set<Long> getFriendIds() {
		if (friendIds == null) {
			friendIds = new HashSet<Long>();
			
			for (Long id : friendService.getFriendIds()) {
				friendIds.add(id);
			}
		}
		
		return friendIds;
	}
	
	
	public boolean isFriend(String oauthId) {
		return oauthId.equals(userService.getProfileId()) || getFriendIds().contains(Long.parseLong(oauthId));
	}
	
	public void follow(String oauthId) {
		if (friendService.follow(Long.parseLong(oauthId)) != null) {
			getFriendIds().add(Long.parseLong(oauthId));
		}		
	}
}
