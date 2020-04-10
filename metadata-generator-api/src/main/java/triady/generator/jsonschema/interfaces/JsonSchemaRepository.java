package triady.generator.jsonschema.interfaces;

import com.google.common.collect.Maps;
import compozitor.processor.core.interfaces.JavaFileName;
import compozitor.processor.core.interfaces.JavaFiles;
import compozitor.processor.core.interfaces.JavaResource;
import compozitor.processor.core.interfaces.JavaResources;
import compozitor.processor.core.interfaces.ProcessingContext;
import compozitor.processor.core.interfaces.ResourceName;
import triady.generator.core.interfaces.Id;
import triady.generator.core.interfaces.Json;

import javax.tools.FileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.Map;

public class JsonSchemaRepository {
  private static final JsonSchemaRepository valueOf = new JsonSchemaRepository();

  private final Map<Id, Iterable<JsonSchemaSpecification.SchemaProperty>> schemas;

  private JsonSchemaRepository() {
    this.schemas = Maps.newHashMap();
  }

  public static JsonSchemaRepository valueOf(){
    return valueOf;
  }

  public void add(JsonSchemaSpecification jsonSchema){
    this.schemas.put(jsonSchema.getId(), jsonSchema.getProperties());
  }

  public void add(Collection<JsonSchemaSpecification> schemas){
    schemas.forEach(this::add);
  }

  public void persist(ProcessingContext context) {
    try {
      JavaFiles files = context.getJavaFiles();
      String json = Json.toString(this.schemas);

      JavaResource javaResource = JavaResources.create(ResourceName.create("schemas.json"));
      FileObject file = files.resourceFile(javaResource);
      try(Writer writer = file.openWriter()){
        writer.write(json);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
