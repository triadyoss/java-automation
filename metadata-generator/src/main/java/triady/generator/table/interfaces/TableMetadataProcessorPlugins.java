package triady.generator.table.interfaces;

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

public abstract class TableMetadataProcessorPlugins implements TypeModelPlugin<ContextMetadata>, TemplatePlugin {

  @Override
  public void accept(TemplateEngine templateEngine, TemplateRepository templateRepository) {
    final TemplateMetadata template = templateRepository.addRegularResourceTemplate();
    template.setTemplate(templateEngine.getTemplate("templates/triady/table.atf"));
    template.setFileName(Filename.create("${Table.Id}.yml"));
    template.setNamespace(Namespace.create("${Table.Namespace}"));
  }

  @Override
  public CodeGenerationCategory category() {
    return TableMetadataCategory.INSTANCE;
  }
}