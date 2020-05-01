package typeHashFactoryTest;

import compozitor.processor.core.interfaces.CompilationBuilder;
import compozitor.processor.core.interfaces.CompileAssertion;
import compozitor.processor.core.interfaces.TestResources;
import org.junit.Test;

public class TypeHashFactoryTest {
  private final TestResources resources = TestResources.create(this.getClass());

  @Test
  public void givenTypeWithEnumWhenExecuteThenCreateHashWithOneElement() {
    final CompileAssertion compilation = CompilationBuilder.create()
      .withProcessors(new TypeHashProcessor())
      .withJavaSource(
        resources.testFile("ComplexHashType.java")
      ).build();

    compilation
      .assertSuccess()
      .generatedResourceAssertion("src/data/types/03cc6ccf-44ca-32be-9aa9-ff1249a44afb")
        .assertEquals(resources.testFile("type.yml"));
  }
}
