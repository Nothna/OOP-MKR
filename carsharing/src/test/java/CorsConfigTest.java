import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import static org.mockito.Mockito.*;
import com.example.carsharing.configs.*;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistration;



class CorsConfigTest {

    @Test
    void addCorsMappings() {
        CorsRegistry registry = mock(CorsRegistry.class);
        CorsConfig config = new CorsConfig();

        CorsRegistration corsRegistration = mock(CorsRegistration.class);
        when(registry.addMapping(anyString())).thenReturn(corsRegistration);
        when(corsRegistration.allowedOrigins(anyString())).thenReturn(corsRegistration);
        when(corsRegistration.allowedMethods(anyString())).thenReturn(corsRegistration);


        config.addCorsMappings(registry);

        verify(registry).addMapping("/**");
        verify(corsRegistration).allowedOrigins("*");
        verify(corsRegistration).allowedMethods("*");
    }








//...

    @Test
    void addInterceptors() {
        InterceptorRegistry registry = mock(InterceptorRegistry.class);
        InterceptorConfig config = new InterceptorConfig();

        when(registry.addInterceptor(any())).thenReturn(mock(InterceptorRegistration.class));

        config.addInterceptors(registry);
    }

    @Test
    void addResourceHandlers() {
        ResourceHandlerRegistry registry = mock(ResourceHandlerRegistry.class);
        StaticResourceConfiguration config = new StaticResourceConfiguration();

        when(registry.addResourceHandler(anyString())).thenReturn(mock(ResourceHandlerRegistration.class));

        config.addResourceHandlers(registry);

        verify(registry).addResourceHandler("/static/image/**");
    }
}
