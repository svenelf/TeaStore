package tools.descartes.petsupplystore.webui.servlet;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.junit.Assert;
import org.junit.Test;

import tools.descartes.petsupplystore.entities.Category;
import tools.descartes.petsupplystore.entities.message.SessionBlob;

public class HeaderTest extends AbstractUiTest {

	@Test
	public void TestHeaderLogging() throws IOException, ServletException, InterruptedException {
		mockCategories(1);
		mockValidPostRestCall(null, "/tools.descartes.petsupplystore.store/rest/useractions/isloggedin");
		String html = getResultingHTML();
		
		Assert.assertEquals("Test if header shows correct illustration: no Profile", 0,
				countString("glyphicon-user", html));
		Assert.assertEquals("Test if header shows correct illustration: Login", 1, countString("Sign", html));
		//
		
		SessionBlob blob = new SessionBlob();
		mockValidPostRestCall(blob, "/tools.descartes.petsupplystore.store/rest/useractions/isloggedin");
		html = getResultingHTML();
		Assert.assertEquals("Test if header shows correct illustration: Profile", 1,
				countString("glyphicon-user", html));
		Assert.assertEquals("Test if header shows correct illustration: Log out", 1, countString("Logout", html));
		
	}

	@Override
	protected Servlet getServlet() {
		return new IndexServlet();
	}

}