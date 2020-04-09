package triady.generator.core.interfaces;

import com.google.common.collect.Maps;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

  public void persist() {
    try {
      String json = Json.toString(this.schemas);
      Files.write(Paths.get("schemas.json"), json.getBytes());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
