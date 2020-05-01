package contextSpecificationProcessorTest;

import compozitor.engine.core.interfaces.ProcessorPlugin;
import compozitor.engine.core.interfaces.TypeModelPlugin;
import compozitor.processor.core.interfaces.AnnotationModel;
import compozitor.processor.core.interfaces.ProcessingContext;
import compozitor.processor.core.interfaces.TypeModel;
import triady.generator.context.interfaces.ContextMetadata;
import triady.generator.context.interfaces.ContextMetadataTemplatePlugin;
import triady.generator.core.interfaces.Description;
import triady.generator.core.interfaces.Name;

@ProcessorPlugin
public class ContextSpecificationProcessorPlugins extends ContextMetadataTemplatePlugin implements TypeModelPlugin<ContextMetadata> {

  @Override
  public ContextMetadata accept(ProcessingContext context, TypeModel typeModel) {
    AnnotationModel annotationModel = typeModel.getAnnotations().getBy(ContextSpecification.class).get();

    Name name = Name.create(annotationModel.value("name"));
    Description description = Description.create(annotationModel.value("description"));

    return ContextMetadata.create(name, description);
  }
}
