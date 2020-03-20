package triady.generator.core.interfaces;

import java.nio.file.Path;
import java.nio.file.Paths;

public class SwaggerFile extends Resource {
  private static final Path PATH = Paths.get(TriadySettings.DATA_DIRECTORY, "swagger/");

  private SwaggerFile(Id id) {
    super(PATH.toString(), id);
  }
}