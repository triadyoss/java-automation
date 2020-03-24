package triady.generator.topic.interfaces;

import compozitor.engine.core.interfaces.TemplatePlugin;
import compozitor.engine.core.interfaces.TypeModelPlugin;
import compozitor.generator.core.interfaces.CodeGenerationCategory;
import compozitor.generator.core.interfaces.Filename;
import compozitor.generator.core.interfaces.Namespace;
import compozitor.generator.core.interfaces.TemplateMetadata;
import compozitor.generator.core.interfaces.TemplateRepository;
import compozitor.template.core.interfaces.TemplateEngine;
import triady.generator.context.interfaces.ContextMetadata;
import triady.generator.context.interfaces.ContextMetadataCategory;

public abstract class TopicMetadataProcessorPlugins implements TypeModelPlugin<ContextMetadata>, TemplatePlugin {

  @Override
  public void accept(TemplateEngine templateEngine, TemplateRepository templateRepository) {
    final TemplateMetadata template = templateRepository.addRegularResourceTemplate();
    template.setTemplate(templateEngine.getTemplate("templates/triady/topic.atf"));
    template.setFileName(Filename.create("${Topic.Id}.yml"));
    template.setNamespace(Namespace.create("${Topic.Namespace}"));
  }

  @Override
  public CodeGenerationCategory category() {
    return TopicMetadataCategory.INSTANCE;
  }
}