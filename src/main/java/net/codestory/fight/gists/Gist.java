package net.codestory.fight.gists;

import static java.nio.charset.StandardCharsets.*;

import java.io.*;
import java.nio.file.*;

import net.codestory.fight.ranking.*;
import net.codestory.http.io.*;

public class Gist {
  private final String name;
  private final String url;
  private final Rank rank;
  private final String content;

  public Gist(String name, String url) {
    this.name = name;
    this.url = url;
    this.rank = new Rank();
    this.content = readContent(name);
  }

  public String name() {
    return name;
  }

  public String url() {
    return url;
  }

  public Rank rank() {
    return rank;
  }

  public String content() {
    return content;
  }

  private static String readContent(String name) {
    String file = "classpath:app/fight/gist/" + name + ".html";
    try {
      return Resources.read(Paths.get(file), UTF_8);
    } catch (IOException e) {
      return "";
    }
  }
}
