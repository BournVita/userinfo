package webapp.model;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class UserDetailsTest {

  @Test
  public void testUserDetailsNotNull() {
	  UserDetails userDetails = new UserDetails();
	  assertNotNull(userDetails);
  }

  @Test
  public void testUserDetailsSetters() {
	  UserDetails userDetails = new UserDetails();
	  userDetails.setCity_alti("123");
	  userDetails.setCity_temp("123");
	  userDetails.setCity_longi("123");
	  userDetails.setCity_name("123");

	  assertEquals(userDetails.getCity_alti(), "123");
	  assertEquals(userDetails.getCity_temp(), "123");
	  assertEquals(userDetails.getCity_longi(), "123");
	  assertEquals(userDetails.getCity_name(), "123");

  }
}
