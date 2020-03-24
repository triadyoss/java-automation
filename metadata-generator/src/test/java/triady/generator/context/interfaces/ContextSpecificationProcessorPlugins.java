package triady.generator.context.interfaces;

import compozitor.engine.core.interfaces.ProcessorPlugin;
import compozitor.processor.core.interfaces.AnnotationModel;
import compozitor.processor.core.interfaces.JavaModel;
import compozitor.processor.core.interfaces.ProcessingContext;
import compozitor.processor.core.interfaces.TypeModel;
import triady.generator.core.interfaces.Description;
import triady.generator.core.interfaces.Name;

import java.util.function.Predicate;

@ProcessorPlugin
public class ContextSpecificationProcessorPlugins extends ContextMetadataProcessorPlugins {
  @Override
  public ContextMetadata accept(ProcessingContext processingContext, TypeModel typeModel) {
    Predicate<AnnotationModel> specificationPredicate = getSpecificationPredicate(processingContext.getJavaModel());
    AnnotationModel annotationModel = typeModel.getAnnotations().get(specificationPredicate).get();

    Name name = Name.create(annotationModel.getValue("name").getValue().toString());
    Description description = Description.create(annotationModel.getValue("description").getValue().toString());

    return ContextMetadata.create(name, description);
  }

  private static Predicate<AnnotationModel> getSpecificationPredicate(final JavaModel javaModel) {
    return (annotation) -> annotation.equals(getSpecification(javaModel));
  }

  private static TypeModel getSpecification(final JavaModel javaModel) {
    return javaModel.getType(ContextSpecification.class.getName());
  }
}
