package triady.generator.schema.interfaces;

import compozitor.engine.core.interfaces.TemplatePlugin;
import compozitor.generator.core.interfaces.CodeGenerationCategory;
import compozitor.generator.core.interfaces.Filename;
import compozitor.generator.core.interfaces.Namespace;
import compozitor.generator.core.interfaces.TemplateMetadata;
import compozitor.generator.core.interfaces.TemplateRepository;
import compozitor.template.core.interfaces.TemplateEngine;

public abstract class SchemaMetadataTemplatePlugin implements TemplatePlugin {

  @Override
  public final void accept(TemplateEngine templateEngine, TemplateRepository templateRepository) {
    final TemplateMetadata template = templateRepository.addRegularResourceTemplate();
    template.setTemplate(templateEngine.getTemplate("templates/triady/schema.atf"));
    template.setFileName(Filename.create("${Schema.Id}.yml"));
    template.setNamespace(Namespace.create("${Schema.Namespace}"));
  }

  @Override
  public final CodeGenerationCategory category() {
    return SchemaMetadataCategory.INSTANCE;
  }
}