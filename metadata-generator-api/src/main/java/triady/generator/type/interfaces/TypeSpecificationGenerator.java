package triady.generator.type.interfaces;

import compozitor.processor.core.interfaces.JavaFiles;
import compozitor.processor.core.interfaces.JavaResource;
import compozitor.processor.core.interfaces.JavaResources;
import compozitor.processor.core.interfaces.ProcessingContext;
import compozitor.processor.core.interfaces.ResourceName;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import triady.generator.core.interfaces.Id;

import javax.tools.FileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TypeSpecificationGenerator {
  private static TypeSpecificationGenerator valueOf = new TypeSpecificationGenerator();

  private final List<Id> generatedTypes = new ArrayList<>();

  public static TypeSpecificationGenerator valueOf(){
    return valueOf;
  }

  public void generate(TypeSpecification type, ProcessingContext context) {
    if (type.undefined()) {
      return;
    }

    if(this.generatedTypes.contains(type.getId())){
      return;
    }

    this.generatedTypes.add(type.getId());

    try {
      JavaFiles files = context.getJavaFiles();

      JavaResource javaResource = JavaResources.create(ResourceName.create(type.getPath().toString()));
      FileObject file = files.resourceFile(javaResource);
      try (Writer writer = file.openWriter()) {
        writer.write(type.toYaml());
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
