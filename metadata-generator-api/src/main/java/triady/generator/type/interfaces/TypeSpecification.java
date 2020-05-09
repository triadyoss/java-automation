package triady.generator.type.interfaces;

import compozitor.processor.core.interfaces.JavaFiles;
import compozitor.processor.core.interfaces.JavaResource;
import compozitor.processor.core.interfaces.JavaResources;
import compozitor.processor.core.interfaces.ProcessingContext;
import compozitor.processor.core.interfaces.ResourceName;
import compozitor.processor.core.interfaces.TypeModel;
import triady.generator.core.interfaces.Id;
import triady.generator.core.interfaces.Resource;
import triady.generator.core.interfaces.TriadySettings;
import triady.generator.core.interfaces.Yaml;

import javax.tools.FileObject;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class TypeSpecification extends Resource {
  private static final Path PATH = Paths.get(TriadySettings.DATA_DIRECTORY, "types/");
  private final TypeHash typeHash;
  private final Boolean undefined;

  private TypeSpecification(Id id, TypeHash typeHash, Boolean undefined) {
    super(PATH.toString(), id);
    this.typeHash = typeHash;
    this.undefined = undefined;
  }

  public static TypeSpecification create() {
    return new TypeSpecification(Id.create(), null, true) {
      @Override
      public Path getPath() {
        return Paths.get("");
      }
    };
  }

  public static TypeSpecification create(TypeModel typeModel) {
    Id id = Id.create(typeModel.getQualifiedName());
    TypeHash typeHash = TypeHashFactory.create(typeModel);
    return new TypeSpecification(id, typeHash, false);
  }

  public Path getEndpoint() {
    String endpoint = this.getPath().toString().replace("src", "");
    return Paths.get(endpoint);
  }

  public void generate(ProcessingContext context) {
    if (this.undefined) {
      return;
    }

    try {
      JavaFiles files = context.getJavaFiles();

      JavaResource javaResource = JavaResources.create(ResourceName.create(this.getPath().toString()));
      FileObject file = files.resourceFile(javaResource);
      try (Writer writer = file.openWriter()) {
        writer.write(this.typeHash.toYaml());
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
