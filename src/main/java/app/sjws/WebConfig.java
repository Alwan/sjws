package app.sjws;

import gg.jte.CodeResolver;
import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.output.PrintWriterOutput;
import gg.jte.resolve.DirectoryCodeResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@Configuration
public class WebConfig {

    @Bean
    public ViewResolver viewResolver(@Value("${spring.profiles.active}") String profile) {
        TemplateEngine templateEngine;
        if (!profile.equals("prod")) {
            CodeResolver codeResolver = new DirectoryCodeResolver(Path.of("src", "main", "resources", "templates"));
            templateEngine = TemplateEngine.create(codeResolver, Paths.get("jte-classes"), ContentType.Html, getClass().getClassLoader());
            templateEngine.setBinaryStaticContent(true);
        } else {
            templateEngine = TemplateEngine.createPrecompiled(ContentType.Html);
        }
        return (viewName, locale) -> (model, request, response) -> {
            response.setContentType("text/html");
            Map<String, Object> copy = Map.copyOf(model);
            templateEngine.render(viewName + ".jte", copy, new PrintWriterOutput(response.getWriter()));
        };
    }
}
