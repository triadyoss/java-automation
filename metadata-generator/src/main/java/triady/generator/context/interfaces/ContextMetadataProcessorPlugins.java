package triady.generator.context.interfaces;

import compozitor.engine.core.interfaces.TemplatePlugin;
import compozitor.engine.core.interfaces.TypeModelPlugin;
import compozitor.generator.core.interfaces.CodeGenerationCategory;
import compozitor.generator.core.interfaces.Filename;
import compozitor.generator.core.interfaces.Namespace;
import compozitor.generator.core.interfaces.TemplateMetadata;
import compozitor.generator.core.interfaces.TemplateRepository;
import compozitor.template.core.interfaces.TemplateEngine;

public abstract class ContextMetadataProcessorPlugins implements TypeModelPlugin<ContextMetadata>, TemplatePlugin {

  @Override
  public final void accept(TemplateEngine templateEngine, TemplateRepository templateRepository) {
    final TemplateMetadata template = templateRepository.addRegularResourceTemplate();
    template.setTemplate(templateEngine.getTemplate("templates/triady/context.atf"));
    template.setFileName(Filename.create("${Context.Id}.yml"));
    template.setNamespace(Namespace.create("${Context.Namespace}"));
  }

  @Override
  public CodeGenerationCategory category() {
    return ContextMetadataCategory.INSTANCE;
  }
}