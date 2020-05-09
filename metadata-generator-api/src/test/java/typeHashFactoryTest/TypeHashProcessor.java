package typeHashFactoryTest;

import com.google.common.collect.Sets;
import compozitor.processor.core.interfaces.AnnotationProcessor;
import compozitor.processor.core.interfaces.TypeModel;
import triady.generator.type.interfaces.TypeSpecification;
import triady.generator.type.interfaces.TypeSpecificationGenerator;

import java.util.Set;

public class TypeHashProcessor extends AnnotationProcessor {
  @Override
  public Set<String> getSupportedAnnotationTypes() {
    return Sets.newHashSet(Hash.class.getName());
  }

  @Override
  protected void process(TypeModel typeModel) {
    TypeSpecification type = TypeSpecification.create(typeModel);
    TypeSpecificationGenerator.valueOf().generate(type, this.context);
  }
}
