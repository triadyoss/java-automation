package triady.generator.context.interfaces;

import triady.generator.core.interfaces.Collection;
import triady.generator.core.interfaces.Description;
import triady.generator.core.interfaces.Metadata;
import triady.generator.core.interfaces.Name;

public class ContextMetadata extends Metadata<ContextMetadata> {
  private ContextMetadata(Name name, Description description) {
    super(Collection.CONTEXT, name, description);
  }

  public static ContextMetadata create(Name name, Description description) {
    return new ContextMetadata(name, description);
  }
}
