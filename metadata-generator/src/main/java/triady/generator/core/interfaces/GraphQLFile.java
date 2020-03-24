package triady.generator.core.interfaces;

import java.nio.file.Path;
import java.nio.file.Paths;

public class GraphQLFile extends Resource {
  private static final Path PATH = Paths.get(TriadySettings.DATA_DIRECTORY, "graphql-schemas/");

  private GraphQLFile(Id id) {
    super(PATH.toString(), id);
  }
}
