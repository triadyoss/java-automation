package triady.generator.endpoint.interfaces;

import lombok.Getter;
import triady.generator.core.interfaces.Id;
import triady.generator.core.interfaces.Resource;
import triady.generator.core.interfaces.TriadySettings;

import java.nio.file.Path;
import java.nio.file.Paths;

@Getter
public class EndpointGraphQLSchema extends Resource {
  private static final Path PATH = Paths.get(TriadySettings.DATA_DIRECTORY, "graphql-schemas/");

  private final Path path;

  private EndpointGraphQLSchema(Id id, Path path){
    super(PATH.toString(), id);
    this.path = path;
  }

  public static EndpointGraphQLSchema create(Path pathToFile){
    return new EndpointGraphQLSchema(Id.create(pathToFile), pathToFile);
  }
}
