package com.newsplore.library;

import org.junit.Assert;
import org.junit.Test;

import com.bookpac.server.common.error.WSException;
import com.newsplore.tree.Node;

public class TxtrLibBrowserTest {

	@Test
	public void test() throws WSException {
		TxtrLibBrowser browser = new TxtrLibBrowser();
		Node rootCat = browser.getAllCategories();
		Assert.assertTrue(rootCat.getChildren().size() > 0);
	}

}
