package br.com.emailmarketing.tomcat;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import java.io.File;

public class TomcatEmbedded {

    private static final Logger logger = LoggerFactory.getLogger(TomcatEmbedded.class);

    private static final String webappDirLocation = "src/main/webapp/";

    private static final String webPort = "8001";

    private static TomcatEmbedded embedded = new TomcatEmbedded();

    private TomcatEmbedded(){

    }

    public static TomcatEmbedded getInstance(){
        if(embedded != null){
            return embedded;
        }
        return new TomcatEmbedded();
    }

    public void start(){

        try {

            this.createTomcatEmbedded();

        }
        catch(ServletException e){
            e.printStackTrace();
            System.exit(0);
        }
        catch(LifecycleException e) {
            e.printStackTrace();
            System.exit(0);
        }
        catch(Exception e){
            e.printStackTrace();
            System.exit(0);
        }

    }

    private void createTomcatEmbedded() throws ServletException, LifecycleException {
        Tomcat tomcat = new Tomcat();

        tomcat.setPort(Integer.valueOf(webPort));

        StandardContext ctx = (StandardContext) tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());

        File additionWebInfClasses = new File("target/classes");

        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(), "/"));
        ctx.setResources(resources);

        tomcat.start();
        tomcat.getServer().await();
    }

}
