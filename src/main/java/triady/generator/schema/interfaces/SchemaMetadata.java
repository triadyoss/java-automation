package triady.generator.schema.interfaces;

import lombok.Getter;
import triady.generator.context.interfaces.ContextMetadata;
import triady.generator.core.interfaces.Collection;
import triady.generator.core.interfaces.Description;
import triady.generator.core.interfaces.JsonSchema;
import triady.generator.core.interfaces.Metadata;
import triady.generator.core.interfaces.Name;

@Getter
public class SchemaMetadata extends Metadata<SchemaMetadata> {
  private static final String NAMESPACE = "metadata._schemas";
  private final ContextMetadata context;
  private final JsonSchema jsonSchema;

  private SchemaMetadata (ContextMetadata context, Name name, Description description, JsonSchema jsonSchema) {
    super(Collection.SCHEMA, name, description);
    this.context = context;
    this.jsonSchema = jsonSchema;
  }

  public static SchemaMetadata create(ContextMetadata context, Name name, Description description, JsonSchema jsonSchema) {
    return new SchemaMetadata(context, name, description, jsonSchema);
  }
}
