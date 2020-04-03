package triady.generator.context.interfaces;

import compozitor.engine.core.interfaces.ProcessorPlugin;
import compozitor.engine.core.interfaces.TypeModelPlugin;
import compozitor.processor.core.interfaces.AnnotationModel;
import compozitor.processor.core.interfaces.AnnotationRepository;
import compozitor.processor.core.interfaces.JavaModel;
import compozitor.processor.core.interfaces.ProcessingContext;
import compozitor.processor.core.interfaces.TypeModel;
import triady.generator.core.interfaces.Description;
import triady.generator.core.interfaces.Name;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

@ProcessorPlugin
public class ContextSpecificationProcessorPlugins extends ContextMetadataTemplatePlugin implements TypeModelPlugin<ContextMetadata> {

  @Override
  public ContextMetadata accept(ProcessingContext context, TypeModel typeModel) {
    Predicate<AnnotationModel> specificationPredicate = getSpecificationPredicate(context.getJavaModel());

    AnnotationModel annotationModel = typeModel.getAnnotations().get(specificationPredicate).get();

    Name name = Name.create(annotationModel.value("name"));
    Description description = Description.create(annotationModel.value("description"));

    return ContextMetadata.create(name, description);
  }

  private static Predicate<AnnotationModel> getSpecificationPredicate(final JavaModel javaModel) {
    return (annotation) -> annotation.equals(getSpecification(javaModel));
  }

  private static TypeModel getSpecification(final JavaModel javaModel) {
    return javaModel.getType(ContextSpecification.class.getName());
  }
}
