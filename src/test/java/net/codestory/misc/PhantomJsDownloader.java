package net.codestory.misc;

import static org.openqa.selenium.phantomjs.PhantomJSDriverService.*;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.zip.*;

import org.openqa.selenium.*;
import org.openqa.selenium.phantomjs.*;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.remote.service.*;

public class PhantomJsDownloader {
  private final boolean isWindows;
  private final boolean isMac;

  public PhantomJsDownloader() {
    isWindows = System.getProperty("os.name").startsWith("Windows");
    isMac = System.getProperty("os.name").startsWith("Mac OS X");
  }

  public WebDriver createDriver() {
    File phantomJsExe = downloadAndExtract();

    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(PHANTOMJS_EXECUTABLE_PATH_PROPERTY, phantomJsExe.getAbsolutePath());

    DriverService service = PhantomJSDriverService.createDefaultService(capabilities);

    return new PhantomJSDriver(service, capabilities);
  }

  public File downloadAndExtract() {
    File installDir = new File(new File(System.getProperty("user.home")), ".phantomjstest");

    String url;
    File phantomJsExe;
    if (isWindows) {
      url = "http://phantomjs.googlecode.com/files/phantomjs-1.9.1-windows.zip";
      phantomJsExe = new File(installDir, "phantomjs-1.9.1-windows/phantomjs.exe");
    } else if (isMac) {
      url = "http://phantomjs.googlecode.com/files/phantomjs-1.9.1-macosx.zip";
      phantomJsExe = new File(installDir, "phantomjs-1.9.1-macosx/bin/phantomjs");
    } else {
      url = "http://phantomjs.googlecode.com/files/phantomjs-1.9.1-linux-x86_64.tar.bz2";
      phantomJsExe = new File(installDir, "phantomjs-1.9.1-linux-x86_64/bin/phantomjs");
    }

    extractExe(url, installDir, phantomJsExe);

    return phantomJsExe;
  }

  private void extractExe(String url, File phantomInstallDir, File phantomJsExe) {
    if (phantomJsExe.exists()) {
      return;
    }

    String zipName = url.substring(url.lastIndexOf('/') + 1);
    File targetZip = new File(phantomInstallDir, zipName);
    downloadZip(url, targetZip);

    System.out.println("Extracting phantomjs");
    try {
      if (isWindows) {
        unzip(targetZip, phantomInstallDir);
      } else if (isMac) {
        executeNative(phantomInstallDir, "/usr/bin/unzip", "-qo", zipName);
      } else {
        executeNative(phantomInstallDir, "/usr/bin/tar", "-xjvf", zipName);
      }
    } catch (Exception e) {
      throw new IllegalStateException("Unable to unzip phantomjs from " + targetZip.getAbsolutePath());
    }
  }

  private void downloadZip(final String url, File targetZip) {
    if (targetZip.exists()) {
      return;
    }

    System.out.printf("Downloading phantomjs from %s...%n", url);

    File zipTemp = new File(targetZip.getAbsolutePath() + ".temp");
    try {
      copy(new InputSupplier() {
        @Override
        public InputStream get() throws IOException {
          return URI.create(url).toURL().openStream();
        }
      }, zipTemp);
    } catch (IOException e) {
      throw new IllegalStateException("Unable to download phantomjs from " + url);
    }

    if (!zipTemp.renameTo(targetZip)) {
      throw new IllegalStateException(String.format("Unable to rename %s to %s", zipTemp.getAbsolutePath(), targetZip.getAbsolutePath()));
    }
  }

  private static void unzip(File zip, File toDir) throws IOException {
    final ZipFile zipFile = new ZipFile(zip);
    try {
      Enumeration<? extends ZipEntry> entries = zipFile.entries();
      while (entries.hasMoreElements()) {
        final ZipEntry entry = entries.nextElement();
        if (entry.isDirectory()) {
          continue;
        }

        File to = new File(toDir, entry.getName());

        copy(new InputSupplier() {
          @Override
          public InputStream get() throws IOException {
            return zipFile.getInputStream(entry);
          }
        }, to);
      }
    } finally {
      zipFile.close();
    }
  }

  private static void executeNative(File workingDir, String... commands) throws IOException, InterruptedException {
    new ProcessBuilder().command(commands).directory(workingDir).start().waitFor();
  }

  private static interface InputSupplier {
    InputStream get() throws IOException;
  }

  private static final int BUF_SIZE = 0x1000; // 4K

  private static void copy(InputSupplier from, File to) throws IOException {
    File parent = to.getParentFile();
    if (!parent.exists()) {
      if (!parent.mkdirs()) {
        throw new IOException("Unable to create folder " + parent);
      }
    }

    InputStream input = null;
    try {
      input = from.get();

      OutputStream output = null;
      try {
        output = new FileOutputStream(to);

        byte[] buf = new byte[BUF_SIZE];
        while (true) {
          int r = input.read(buf);
          if (r == -1) {
            return;
          }
          output.write(buf, 0, r);
        }
      } finally {
        if (output != null) {
          output.close();
        }
      }
    } finally {
      if (input != null) {
        input.close();
      }
    }
  }
}
