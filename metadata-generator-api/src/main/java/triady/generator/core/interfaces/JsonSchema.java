package triady.generator.core.interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;
import compozitor.processor.core.interfaces.LazyLoadProxy;
import compozitor.processor.core.interfaces.TypeModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Supplier;

public class JsonSchema extends Resource {
  private static final Path PATH = Paths.get(TriadySettings.DATA_DIRECTORY, "json-schemas/");

  protected static final ObjectMapper json = new ObjectMapper();

  private final LazyLoadProxy<String> schemaLoader;

  static {
    json.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
  }

  protected JsonSchema(Id id, Supplier<String> schemaLoader){
    super(PATH.toString(), id);
    this.schemaLoader = new LazyLoadProxy<>(schemaLoader);
  }

  private JsonSchema(Id id, String targetClassName) {
    super(PATH.toString(), id);
    this.schemaLoader = new LazyLoadProxy<>(new Schema(targetClassName));
  }

  public static JsonSchema create(TypeModel typeModel) {
    Id id = Id.create(typeModel.getQualifiedName());
    return new JsonSchema(id, typeModel.getQualifiedName());
  }

  public Path getEndpoint() {
    String endpoint = this.getPath().toString().replace("src", "");
    return Paths.get(endpoint);
  }

  public String getContent(){
    return this.schemaLoader.execute();
  }

  public void write() {
    try {
      Path path = this.getPath();
      String content = this.getContent();
      Files.write(path, content.getBytes());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @RequiredArgsConstructor
  class Schema implements Supplier<String> {
    private final String targetClassName;

    @Override
    public String get() {
      try {
        Class schemaClass = Class.forName(targetClassName);

        JsonSchemaGenerator schemaGenerator = new JsonSchemaGenerator(json);
        com.fasterxml.jackson.module.jsonSchema.JsonSchema schema = schemaGenerator.generateSchema(schemaClass);

        return json.writerWithDefaultPrettyPrinter().writeValueAsString(schema);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }
}
