package net.codestory.vote.gists;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.*;

import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.mockito.runners.*;

@RunWith(MockitoJUnitRunner.class)
public class DemoGistsTest {
  @Mock
  Random random;

  @InjectMocks
  DemoGists gists;

  @Test
  public void random_candidates() {
    when(random.nextInt(2)).thenReturn(0, 1);

    Candidates candidates = gists.candidates();

    assertThat(candidates.left()).isEqualTo(gists.get(0));
    assertThat(candidates.right()).isEqualTo(gists.get(1));
  }

  @Test
  public void no_doubles() {
    when(random.nextInt(2)).thenReturn(1, 1, 1, 0);

    Candidates candidates = gists.candidates();

    assertThat(candidates.left()).isNotEqualTo(candidates.right());
  }
}
