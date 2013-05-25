jetty-launcher
==============

"jetty-launcher" is for Java web app developers who are using Maven and Eclipse. Its advantage over ["jetty-maven-plugin"](http://wiki.eclipse.org/Jetty/Feature/Jetty_Maven_Plugin) is that "jetty-launcher" does not require recompiling the dependencies of the web app project from the command line. It also works in debugger. Both are pretty important things.

The launcher is NOT an Eclipse plugin, even though it is most useful under Eclipse. It is a simple Java class with a "main" method that runs a single Java web app.

What's Included and What's Not Encluded
---------------------------------------

v1.0 is based on Jetty 8 and supports Java 1.6 and Servlet Spec 3.0. The launcher is intentionally crippled based on my personal Java webapp stack preferences. 

What's included:

* Servlets / filters and other web.xml goodies.
* JNDI

What's NOT included:

* As it happens I care very little about JSP, JSF, EL so those are excluded.
* No support for EJBs or other heavier JEE stuff.
* Servlet 3.0 annotation processing.
* Clean shutdown and hot redeploy (another thing I don't care about in development)

Patches to add these are welcomed!

Usage
-----

* Proxy [ObjectStyle Maven Repository](http://maven.objectstyle.org/nexus/content/repositories/releases/) in your own repository manager. Or simply grab jetty-laucher.jar from there and upload it to your repo.

* Add dependency on jetty-launcher to your web project, setting the scope as "provided" (i.e. you don't want jetty-laucher.jar end up in your .war during deployment).

* In Eclipse, right-click on your web project and select "Run As > Java Application". Select "org.objectstyle.jetty.Launcher" as your main class and click run. Check your app at (http://localhost:8080/mymodulename)[http://localhost:8080/mymodulename].

Customization
-------------

jetty-launcher supports two properties that can be passed on the command line with "-D":

* os.jetty.context - the name of the webapp context. Default is the name of the project module.
* os.jetty.port - the port to listen on. Default is "8080"

E.g.:

    -Dos.jetty.context=/myapp -Dos.jetty.port=7100

Web application configuration (including setting the context name) can be done via "jetty-web.xml" file that is placed in "WEB-INF/" folder. Read more about the format [here](http://wiki.eclipse.org/Jetty/Reference/jetty-web.xml).
