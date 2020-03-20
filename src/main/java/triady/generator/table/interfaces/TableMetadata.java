package triady.generator.table.interfaces;

import lombok.Getter;
import triady.generator.core.interfaces.Collection;
import triady.generator.core.interfaces.Description;
import triady.generator.core.interfaces.JsonSchema;
import triady.generator.core.interfaces.Metadata;
import triady.generator.core.interfaces.Name;
import triady.generator.module.interfaces.ModuleMetadata;
import triady.generator.schema.interfaces.SchemaMetadata;

@Getter
public class TableMetadata extends Metadata<TableMetadata> {
  private final ModuleMetadata module;
  private final JsonSchema jsonSchema;

  private TableMetadata(ModuleMetadata module, JsonSchema jsonSchema, Name name, Description description) {
    super(Collection.TABLE, name, description);
    this.module = module;
    this.jsonSchema = jsonSchema;
  }

  public static TableMetadata create(ModuleMetadata module, JsonSchema jsonSchema, Name name, Description description) {
    return new TableMetadata(module, jsonSchema, name, description);
  }
}
