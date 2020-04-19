package triady.generator.jsonschema.interfaces;

import compozitor.processor.core.interfaces.TypeModel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import triady.generator.core.interfaces.Id;
import triady.generator.core.interfaces.Resource;
import triady.generator.core.interfaces.TriadySettings;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonSchemaSpecification extends Resource {
  private static final Path PATH = Paths.get(TriadySettings.DATA_DIRECTORY, "json-schemas/");

  protected static final String VALUE = "value";

  @Getter(value = AccessLevel.PACKAGE)
  private final List<SchemaProperty> properties;

  protected JsonSchemaSpecification(Id id) {
    super(PATH.toString(), id);
    this.properties = new ArrayList<>();
  }

  public static JsonSchemaSpecification create(TypeModel typeModel) {
    Id id = Id.create(typeModel.getQualifiedName());
    return new JsonSchemaSpecification(id).put(VALUE, typeModel.getQualifiedName());
  }

  protected JsonSchemaSpecification put(String key, String value){
    this.properties.add(new SchemaProperty(key, value));
    return this;
  }

  public Path getEndpoint() {
    String endpoint = this.getPath().toString().replace("src", "");
    return Paths.get(endpoint);
  }

  @RequiredArgsConstructor
  @Getter
  public class SchemaProperty {
    private final String key;
    private final String value;
  }
}
