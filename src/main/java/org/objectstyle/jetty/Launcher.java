/*****************************************************************
 *  Licensed under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 ****************************************************************/
package org.objectstyle.jetty;

import java.io.File;
import java.net.MalformedURLException;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppContext;

public class Launcher {

	private static final String CONTEXT_PROPERTY = "os.jetty.context";
	private static final String PORT_PROPERTY = "os.jetty.port";

	private static final String WAR_PATH = "src" + File.separator + "main" + File.separator + "webapp";

	public static void main(String[] args) throws Exception {
		new Launcher().launch();
	}

	void launch() throws Exception {
		int port = getPort();

		Server server = new Server();

		SelectChannelConnector connector = new SelectChannelConnector();
		connector.setPort(port);
		server.addConnector(connector);

		WebAppContext context = createContext();

		// this is needed to get stuff from Eclipse project CLASSPATH
		context.setParentLoaderPriority(true);

		ContextHandlerCollection contexts = new ContextHandlerCollection();

		contexts.addHandler(context);

		server.setHandler(contexts);
		server.setStopAtShutdown(true);
		server.setSendServerVersion(true);

		server.start();
	}

	private WebAppContext createContext() throws MalformedURLException {

		File projectBase = new File(System.getProperty("user.dir"));

		File warFolder = new File(projectBase, WAR_PATH);
		String warUrl = warFolder.toURI().toURL().toExternalForm();

		WebAppContext context = new WebAppContext();

		context.setWar(warUrl);
		String contextPath = System.getProperty(CONTEXT_PROPERTY);
		if (contextPath == null) {
			contextPath = "/" + projectBase.getName();
		}

		context.setContextPath(contextPath);

		// "plus" configs
		context.setConfigurationClasses(new String[] { "org.eclipse.jetty.webapp.WebInfConfiguration",
				"org.eclipse.jetty.webapp.WebXmlConfiguration", "org.eclipse.jetty.webapp.MetaInfConfiguration",
				"org.eclipse.jetty.webapp.FragmentConfiguration", "org.eclipse.jetty.plus.webapp.EnvConfiguration",
				"org.eclipse.jetty.plus.webapp.PlusConfiguration",
				"org.eclipse.jetty.annotations.AnnotationConfiguration",
				"org.eclipse.jetty.webapp.JettyWebXmlConfiguration" });

		return context;
	}

	private int getPort() {
		String stringPort = System.getProperty(PORT_PROPERTY);

		if (stringPort == null) {
			return 8080;
		}

		try {
			return Integer.parseInt(stringPort);
		} catch (NumberFormatException nfex) {
			throw new RuntimeException("Non-numeric port specified for '" + PORT_PROPERTY + "': " + stringPort);
		}
	}
}
