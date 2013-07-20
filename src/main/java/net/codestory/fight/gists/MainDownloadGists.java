package net.codestory.fight.gists;

import static java.nio.charset.StandardCharsets.*;

import java.io.*;

import com.google.common.io.*;

public class MainDownloadGists {
  public static void main(String[] args) throws IOException {
    new Gists().forEach((gist) -> {
      String name = gist.name();

      File file = new File("src/main/resources/app/fight/gist", name + ".html");
      if (!file.exists()) {
        System.out.println(gist.name());

        downloadGist(gist, file);
      }
    });
  }

  private static void downloadGist(Gist gist, File file) {
    try {
      Files.write("<script id=\"GIST\" src=\"" + gist.url() + "\"></script>", new File("/tmp/index.html"), UTF_8);
      Files.write("var page = require('webpage').create();\n" +
          "var args = require('system').args;\n" +
          "var url = args[1];\n" +
          "\n" +
          "page.open(url, function (status) {\n" +
          "  var js = page.evaluate(function () {\n" +
          "    var root = document.getElementsByClassName('gist');\n" +
          "    if (typeof(root[0]) == 'undefined') {\n" +
          "      root = document.getElementsByClassName('gist-it-gist');\n" +
          "    }\n" +
          "    return root[0];\n" +
          "  });\n" +
          "  console.log(js.outerHTML); \n" +
          "  phantom.exit();\n" +
          "});", new File("/tmp/script"), UTF_8);

      File tmpFile = new File(file.getAbsolutePath() + ".tmp");

      new ProcessBuilder()
          .directory(new File("/tmp"))
          .command("/opt/boxen/homebrew/bin/phantomjs", "script", "index.html")
          .redirectOutput(tmpFile).start().waitFor();

      tmpFile.renameTo(file);
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }
}
