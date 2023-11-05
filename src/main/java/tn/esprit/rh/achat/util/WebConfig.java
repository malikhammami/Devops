@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/SpringMVC/*")
                .allowedOrigins("http://angular:4200")
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}
