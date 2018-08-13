package oneSpringBoot;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liucaijin.dao.QuerySelect;

@Controller
@ComponentScan("com.liucaijin")
@EnableAutoConfiguration
public class Bootstrap  extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer{
	
	@Autowired
	QuerySelect querySelect;

	@RequestMapping("/")
	@ResponseBody
	String home() throws Exception { 
		throw new Exception("我是可以封装成对象的异常消息");
	}

	public static void main(String[] args) throws Exception {
		ApplicationContext context = SpringApplication.run(Bootstrap.class, args);
        if (context instanceof EmbeddedWebApplicationContext) {
            int port = ((EmbeddedWebApplicationContext) context).getEmbeddedServletContainer().getPort();
            String contextPath = context.getApplicationName();
            String url = String.format(Locale.US, "http://localhost:%d%s", port, contextPath);
            System.out.println("url: " + url);

        }
		 
	}

	@Override
	public void customize(ConfigurableEmbeddedServletContainer arg0) {
		arg0.setPort(8081);
		
	}

}
