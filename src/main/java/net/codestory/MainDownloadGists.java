package net.codestory;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.stream.*;

import net.codestory.fight.gists.*;

import com.google.common.io.*;

public class MainDownloadGists {
  public static void main(String[] args) throws IOException {
    Gists gists = new VoteConfiguration().createGists();

    StreamSupport.parallelStream(Spliterators.spliteratorUnknownSize(gists.iterator(), 0))
        .forEach(gist -> {
          String name = gist.name();
          File file = new File("app/gist", name + ".js");

          if (!file.exists()) {
            downloadGist(gist, file);
          }
        });
  }

  private static void downloadGist(Gist gist, File file) {
    System.out.println(gist.name());

    try {
      byte[] bytes = Resources.toByteArray(URI.create(gist.url()).toURL());

      File tmpFile = new File(file.getAbsolutePath() + ".tmp");
      Files.write(bytes, tmpFile);
      if (file.exists()) {
        file.delete();
      }
      tmpFile.renameTo(file);
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }
}
