package triady.generator.api.interfaces;

import compozitor.engine.core.interfaces.TemplatePlugin;
import compozitor.engine.core.interfaces.TypeModelPlugin;
import compozitor.generator.core.interfaces.CodeGenerationCategory;
import compozitor.generator.core.interfaces.Filename;
import compozitor.generator.core.interfaces.Namespace;
import compozitor.generator.core.interfaces.TemplateMetadata;
import compozitor.generator.core.interfaces.TemplateRepository;
import compozitor.template.core.interfaces.TemplateEngine;

public abstract class ApiMetadataTemplatePlugin implements TemplatePlugin {

  @Override
  public final void accept(TemplateEngine templateEngine, TemplateRepository templateRepository) {
    final TemplateMetadata template = templateRepository.addRegularResourceTemplate();
    template.setTemplate(templateEngine.getTemplate("templates/triady/api.atf"));
    template.setFileName(Filename.create("${Api.Id}.yml"));
    template.setNamespace(Namespace.create("${Api.Namespace}"));
  }

  @Override
  public final CodeGenerationCategory category() {
    return ApiMetadataCategory.INSTANCE;
  }
}