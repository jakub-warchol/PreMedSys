package pl.pwsztar.premedsys.utils;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WebPageUtil {

  static String WEBSITE_TEMPLATE;

  static {
    try {
      String filename="website_content.txt";
      Path websiteContentPath = Paths.get(filename);
      WEBSITE_TEMPLATE = new String(Files.readAllBytes(websiteContentPath.toAbsolutePath()));
    } catch (IOException e) {
      log.error("Cannot open website content");
    }
  }

  public static String getPage() {
    return WEBSITE_TEMPLATE;
  }
}
