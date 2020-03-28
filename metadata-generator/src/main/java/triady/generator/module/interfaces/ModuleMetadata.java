package triady.generator.module.interfaces;

import lombok.Getter;
import triady.generator.context.interfaces.ContextMetadata;
import triady.generator.core.interfaces.Collection;
import triady.generator.core.interfaces.Description;
import triady.generator.core.interfaces.Metadata;
import triady.generator.core.interfaces.Name;
import triady.generator.topic.interfaces.TopicCollection;

import java.util.stream.Collectors;

public class ModuleMetadata extends Metadata<ModuleMetadata> {
  private static final String NAMESPACE = "metadata._modules";
  @Getter
  private final ContextMetadata context;
  private final ConsumesTopics consumesTopics;
  private final ProducesTopics producesTopics;

  private ModuleMetadata(ContextMetadata context, Name name, Description description, ConsumesTopics consumesTopics, ProducesTopics producesTopics) {
    super(Collection.MODULE, name, description);
    this.context = context;
    this.consumesTopics = consumesTopics;
    this.producesTopics = producesTopics;
  }

  public static ModuleMetadata create(ContextMetadata context, Name name, Description description, ConsumesTopics consumesTopics, ProducesTopics producesTopics) {
    return new ModuleMetadata(context, name, description, consumesTopics, producesTopics);
  }

  public String getConsumesTopics(){
    return this.consumesTopics.collect();
  }

  public String getProducesTopics(){
    return this.producesTopics.collect();
  }
}
