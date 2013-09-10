package net.codestory;

import static org.fest.assertions.Assertions.*;

import org.junit.*;

public class IndexTest extends AbstractWebTest {
	@Test
	public void redirect_to_blog() {
		goTo("/");

		assertThat(title()).isEqualTo("Code-Story Blog");
	}
}
