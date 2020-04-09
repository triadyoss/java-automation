package triady.generator.table.interfaces;

import lombok.Getter;
import triady.generator.core.interfaces.Collection;
import triady.generator.core.interfaces.Description;
import triady.generator.core.interfaces.JsonSchemaSpecification;
import triady.generator.core.interfaces.Metadata;
import triady.generator.core.interfaces.Name;
import triady.generator.module.interfaces.ModuleMetadata;

@Getter
public class TableMetadata extends Metadata<TableMetadata> {
  private final ModuleMetadata module;
  private final JsonSchemaSpecification jsonSchema;

  private TableMetadata(ModuleMetadata module, JsonSchemaSpecification jsonSchema, Name name, Description description) {
    super(Collection.TABLE, name, description);
    this.module = module;
    this.jsonSchema = jsonSchema;
  }

  public static TableMetadata create(Name name){
    return create(null, null, name, null);
  }

  public static TableMetadata create(ModuleMetadata module, JsonSchemaSpecification jsonSchema, Name name, Description description) {
    return new TableMetadata(module, jsonSchema, name, description);
  }
}
