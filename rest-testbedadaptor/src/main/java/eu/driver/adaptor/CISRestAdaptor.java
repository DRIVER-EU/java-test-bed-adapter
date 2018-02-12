package eu.driver.adaptor;

import javax.servlet.ServletContext;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import eu.driver.adaptor.ws.WebSocketServer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@ComponentScan
@EnableSwagger2
@SpringBootApplication
public class CISRestAdaptor {

	private Logger log = Logger.getLogger(this.getClass());
	private Boolean restEndpointAvailable = false;
	private Boolean wsEndpointAvailable = false;
	
	private WebSocketServer wsServer = null;
	
	public CISRestAdaptor() throws Exception {
		
		ConsoleAppender console = new ConsoleAppender(); //create appender
		//configure the appender
		String PATTERN = "%d [%p|%c|%C{1}] %m%n";
		console.setLayout(new PatternLayout(PATTERN)); 
		console.setThreshold(Level.DEBUG);
		console.activateOptions();
		//add appender to any Logger (here is root)
		Logger.getRootLogger().addAppender(console);
	
		FileAppender fa = new FileAppender();
		fa.setName("FileLogger");
		fa.setFile("./log/rest-adaptor.log");
		fa.setLayout(new PatternLayout("%d %-5p [%c{1}] %m%n"));
		fa.setThreshold(Level.INFO);
		fa.setAppend(true);
		fa.activateOptions();
	
		//add appender to any Logger (here is root)
		Logger.getRootLogger().addAppender((Appender)fa);
		//repeat with all other desired appenders
		  
		log.info("Init. CISRestAdaptor");
	}
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(CISRestAdaptor.class, args);
    }
	
	@Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("CISRestAdaptor")
                .apiInfo(apiInfo())
                .select()
                .paths(regex("/CISRestAdaptor.*"))
                .build();
    }
	
	private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("CISAdaptor REST Interface API Doc.")
                .description("This is the CISAdaptor REST Interface API Documentation made with Swagger.")
                .version("1.0")
                .build();
    }
	
	/*@Bean
    public ServletContextAware endpointExporterInitializer(final ApplicationContext applicationContext) {
        return new ServletContextAware() {
			
        	@Override
            public void setServletContext(ServletContext servletContext) {
                ServerEndpointExporter exporter = new ServerEndpointExporter();
                exporter.setApplicationContext(applicationContext);
                exporter.afterPropertiesSet();
            }
		};
    }*/
}
