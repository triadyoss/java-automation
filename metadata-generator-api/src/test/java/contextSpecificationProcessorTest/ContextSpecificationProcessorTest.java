package contextSpecificationProcessorTest;

import compozitor.processor.core.interfaces.CompilationBuilder;
import compozitor.processor.core.interfaces.CompileAssertion;
import compozitor.processor.core.interfaces.TestResources;
import org.junit.Test;

public class ContextSpecificationProcessorTest {
  private TestResources resources = TestResources.create(this.getClass());

  @Test
  public void givenContextSpecificationWhenCompileThenGenerateYmlFile(){
    final CompileAssertion compilation = CompilationBuilder.create()
        .withProcessors(new ContextSpecificationProcessor())
        .withJavaSource(resources.testFile("ContextTest.java"))
        .build();

    compilation.assertSuccess();
    compilation.assertGeneratedFiles(2);
  }
}
