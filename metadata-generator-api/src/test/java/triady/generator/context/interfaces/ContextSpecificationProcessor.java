package triady.generator.context.interfaces;

import com.google.common.collect.Sets;
import compozitor.engine.core.interfaces.ProcessorEngine;
import compozitor.generator.core.interfaces.CodeGenerationCategory;

import java.util.Set;

public class ContextSpecificationProcessor extends ProcessorEngine<ContextMetadata> {
  @Override
  public Set<String> getSupportedAnnotationTypes() {
    return Sets.newHashSet(ContextSpecification.class.getName());
  }

  @Override
  protected CodeGenerationCategory category() {
    return ContextMetadataCategory.INSTANCE;
  }
}
