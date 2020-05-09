package triady.generator.schema.interfaces;

import lombok.Getter;
import triady.generator.context.interfaces.ContextMetadata;
import triady.generator.core.interfaces.Collection;
import triady.generator.core.interfaces.Description;
import triady.generator.core.interfaces.Metadata;
import triady.generator.core.interfaces.Name;
import triady.generator.type.interfaces.TypeSpecification;

@Getter
public class SchemaMetadata extends Metadata<SchemaMetadata> {
  private static final String NAMESPACE = "metadata._schemas";
  private final ContextMetadata context;
  private final TypeSpecification type;

  private SchemaMetadata (ContextMetadata context, Name name, Description description, TypeSpecification type) {
    super(Collection.SCHEMA, name, description);
    this.context = context;
    this.type = type;
  }

  public static SchemaMetadata create(Name name){
    return create(null, name, null, null);
  }

  public static SchemaMetadata create(ContextMetadata context, Name name, Description description, TypeSpecification type) {
    return new SchemaMetadata(context, name, description, type);
  }
}
