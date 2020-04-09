package triady.generator.core.interfaces;

import compozitor.generator.core.interfaces.Namespace;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.nio.file.Path;
import java.nio.file.Paths;

@Getter
public abstract class Resource {
  private final Namespace namespace;

  private final Id id;

  protected Resource(String path, Id id){
    this.namespace = Namespace.create(Paths.get(path));
    this.id = id;
  }

  public Path getPath(){
    return Paths.get(this.namespace.toPath().toString(), this.id.toString());
  }
}