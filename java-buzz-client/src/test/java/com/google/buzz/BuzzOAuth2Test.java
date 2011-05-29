package com.google.buzz;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Ignore;
import com.google.buzz.Buzz;
import com.google.buzz.model.BuzzContent;
import com.google.buzz.model.BuzzFeed;
import com.google.buzz.model.BuzzFeedEntry;



public class BuzzOAuth2Test
{


 @Before public void initBuzzClient()
                          throws Exception
 {
   if (buzz==null) {
     buzz = new Buzz();
     buzz.setOAuthVersion(2);
     buzz.setConsumerForScope(Config.buzzConsumerKey2,
                              Config.buzzConsumerSecret2,
                              Buzz.BUZZ_SCOPE_WRITE);
     //buzz.setAccessToken(Config.buzzAccessToken2);
     //buzz.setRefreshToken(Config.buzzRefreshToken2);
   }
 }

 @Ignore
 @Test public void testPublish2() throws Exception
 {
   String userId="@me";
   BuzzContent buzzContent = new BuzzContent();
   buzzContent.setText("test message");
   buzzContent.setType("text/plain");
   BuzzFeedEntry post = buzz.createPost(userId,buzzContent,null);
   Assert.assertTrue(post!=null);
 }


 private Buzz buzz=null;

}
