package triady.generator.context.interfaces;

import com.google.testing.compile.Compilation;
import compozitor.processor.core.interfaces.CompilationBuilder;
import compozitor.processor.core.interfaces.FileAssertion;
import org.junit.Assert;
import org.junit.Test;

public class ContextSpecificationProcessorTest {
  @Test
  public void givenContextSpecificationWhenCompileThenGenerateYmlFile(){
    final Compilation compilation =
      CompilationBuilder.create()
        .withProcessors(new ContextSpecificationProcessor())
        .withJavaSource("contextSpecificationProcessorTest/ContextTest.java")
        .build();

    Assert.assertEquals(2, compilation.generatedFiles().size());

    compilation.generatedFiles().forEach(file -> {
      if(file.getName().contains("yml")){
        FileAssertion.withResourceFile("/contextSpecificationProcessorTest/context.yml").assertEquals(file);
      }
    });
  }
}
