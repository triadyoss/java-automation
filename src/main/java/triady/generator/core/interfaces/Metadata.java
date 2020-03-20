package triady.generator.core.interfaces;

import compozitor.generator.core.interfaces.Namespace;
import compozitor.template.core.interfaces.TemplateContextData;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.nio.file.Path;
import java.nio.file.Paths;

@Getter
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public abstract class Metadata<M extends TemplateContextData<M>> implements TemplateContextData<M> {
  private final Collection collection;
  private final Id id;
  private final Name name;
  private final Description description;

  protected Metadata(Collection collection, Name name, Description description) {
    this.collection = collection;
    this.name = name;
    this.description = description;
    this.id = Id.create(collection.namespace(), name);
  }

  public Namespace getNamespace(){
    return this.collection.namespace();
  }

  @Override
  public final String key() {
    return this.collection.templateKey();
  }
}
