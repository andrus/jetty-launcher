jetty-launcher
==============

A launcher that runs a Java web app from a simple "main" method. Its primary purpose is to run Java web apps in Eclipse.

What's Included
---------------

'jetty-launcher' 1.0 is based on Jetty 8 and supports Java 1.6 and Servlet Spec 3.0. 

As it happens I care very little about JSP, JSF, EL and other common web container things. So those specs are exlcuded. It should be very easy to add them back by declaring corresponding artifacts in the pom.

Usage
-----

* Proxy [ObjectStyle Maven Repository](http://maven.objectstyle.org/nexus/content/repositories/releases/) in your own repository manager. Or simply grab jetty-laucher.jar from there and upload it to your repo.

* Add dependency on jetty-launcher to your web project, setting the scope as "provided" (i.e. you don't want jetty-laucher.jar end up in your .war during deployment).

* In Eclipse, right-click on your web project and select "Run As > Java Application". Select "org.objectstyle.jetty.Launcher" as your main class and click run. Check your app at (http://localhost:8080/)[http://localhost:8080/].

Customization
-------------

jetty-launcher supports two properties that can be passed on the command line with "-D":

* os.jetty.context - the name of the webapp context. Default is "/"
* os.jetty.port - the port to listen on. Default is "8080"

E.g.:

    -Dos.jetty.context=/myapp -Dos.jetty.port=7100

Web application configuration (including setting the context name) can be done via "jetty-web.xml" file that is placed in "WEB-INF/" folder. Read more about the format [here](http://wiki.eclipse.org/Jetty/Reference/jetty-web.xml).
