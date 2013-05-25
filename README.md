jetty-launcher
==============

"jetty-launcher" is a rapid turnaround launcher tool for Java web app developers who are using Maven and Eclipse. It is an alternative to ["jetty-maven-plugin"](http://wiki.eclipse.org/Jetty/Feature/Jetty_Maven_Plugin) that does not require manual recompiling dependencies of a web app project. It also works in debugger. I.e. it creates a much better Eclipse experience. 

At the same time the launcher is NOT an Eclipse plugin. It is a simple Java class with a "main" method that runs a single Java web app.

What's Included
---------------

* Jetty 8 that supports Java 1.6+ and Servlet Spec 3.0.
* Servlets / filters and other web.xml goodies.
* JNDI
* JSP, JSF, EL (inlcuded, but not tested)

What's NOT Included:
--------------------

The launcher is intentionally crippled to support a certain simple workflow. To achieve its rapid turnaround goals, it sacrifices many web container features:

* There's no support for EJBs or other heavier JEE stuff.
* There's no servlet 3.0 annotation processing.
* There's no hot redeploy

Usage
-----

* Proxy [ObjectStyle Maven Repository](http://maven.objectstyle.org/nexus/content/repositories/releases/) in your own repository manager. Or simply grab jetty-laucher.jar from there and upload it to your repo. (TODO: post to Maven central)

* Add dependency on jetty-launcher to your web project, setting the scope as "provided" (i.e. you don't want jetty-laucher.jar end up in your .war during deployment):

    <dependency>
        <groupId>org.objectstyle</groupId>
        <artifactId>jetty-launcher</artifactId>
        <version>1.1</version>
        <scope>provided</scope>
    </dependency>

* In Eclipse, right-click on your web project and select "Run As > Java Application". Select "org.objectstyle.jetty.Launcher" as your main class and click "Run". Check your app at a URL like http://localhost:8080/mymodulename .

Customization
-------------

jetty-launcher supports two properties that can be passed on the command line with "-D":

* os.jetty.context - the name of the webapp context. Default is the name of the project module.
* os.jetty.port - the port to listen on. Default is "8080"

E.g.:

    -Dos.jetty.context=/myapp 
    -Dos.jetty.port=7100

Web application configuration (including setting the context name) can be done via "jetty-web.xml" file that is placed in "WEB-INF/" folder. Read more about the format [here](http://wiki.eclipse.org/Jetty/Reference/jetty-web.xml).

Customizing the rest of the Jetty container (connector, etc.) should probably be done by forking the launcher and creating the desired configuration programmatically. "org.objectstyle.jetty.Launcher" is a small and transparent class and it should be easy to tweak to your liking.

