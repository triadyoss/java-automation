package triady.generator.context.interfaces;

import compozitor.engine.core.interfaces.ProcessorPlugin;
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
public class ContextSpecificationProcessorPlugins extends ContextMetadataTemplatePlugin {


  @Override
  public Collection<ContextMetadata> accept(ProcessingContext context, AnnotationRepository annotationRepository) {
    Predicate<AnnotationModel> specificationPredicate = getSpecificationPredicate(context.getJavaModel());
    Collection<ContextMetadata> collection = new ArrayList<>();

    annotationRepository.elementsAnnotatedWith(context.getTypeElement(ContextSpecification.class.getName()))
      .forEach((annotation, elements) ->{
        elements.forEach(element -> {
          TypeModel contextModel = context.getJavaModel().getClass(element);

          AnnotationModel annotationModel = contextModel.getAnnotations().get(specificationPredicate).get();

          Name name = Name.create(annotationModel.value("name"));
          Description description = Description.create(annotationModel.value("description"));

          collection.add(ContextMetadata.create(name, description));
        });
      });

    return collection;
  }

  private static Predicate<AnnotationModel> getSpecificationPredicate(final JavaModel javaModel) {
    return (annotation) -> annotation.equals(getSpecification(javaModel));
  }

  private static TypeModel getSpecification(final JavaModel javaModel) {
    return javaModel.getType(ContextSpecification.class.getName());
  }
}
