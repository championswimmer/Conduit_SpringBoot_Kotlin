package tech.arnav.conduit.conduit_springboot_kotlin

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.DispatcherServlet
import org.springframework.web.servlet.config.annotation.EnableWebMvc


@SpringBootApplication
@EnableWebMvc
class ConduitSpringBootKotlinApplication {
    @Autowired
    private lateinit var servlet: DispatcherServlet

    @Bean
    fun getCommandLineRunner(context: ApplicationContext?): CommandLineRunner? {
        servlet.setThrowExceptionIfNoHandlerFound(true)
        return CommandLineRunner { args: Array<String?>? -> }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<ConduitSpringBootKotlinApplication>(*args)
        }
    }


}
