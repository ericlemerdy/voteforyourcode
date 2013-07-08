package net.codestory.vote.gists;

import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.util.*;
import java.util.concurrent.*;

import com.google.common.cache.*;
import com.google.common.io.*;

public class GistCache {
  private final Gists gists;

  public GistCache(Gists gists) {
    this.gists = gists;
  }

  private final LoadingCache<String, byte[]> gistsContentPerUrl = CacheBuilder.newBuilder()
      .build(new CacheLoader<String, byte[]>() {
        @Override
        public byte[] load(String url) throws IOException {
          return Resources.toByteArray(URI.create(url).toURL());
        }
      });

  public byte[] getGistContent(String name) {
    Gist gist = gists.findByName(name);
    if (null == gist) {
      throw new NoSuchElementException(name);
    }

    try {
      return gistsContentPerUrl.get(gist.url());
    } catch (ExecutionException e) {
      return "ERROR".getBytes(StandardCharsets.UTF_8);
    }
  }
}
