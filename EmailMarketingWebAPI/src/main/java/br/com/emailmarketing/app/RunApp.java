package br.com.emailmarketing.app;

import br.com.emailmarketing.tomcat.TomcatEmbedded;

public class RunApp {

    public static void main(String[] args) {
        TomcatEmbedded.getInstance().start();
    }

}
