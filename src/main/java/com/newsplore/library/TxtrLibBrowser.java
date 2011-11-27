package com.newsplore.library;

import java.util.ArrayList;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.bookpac.server.common.error.WSException;
import com.bookpac.server.contentcategory.IWSContentCategoryMgmt;
import com.bookpac.server.contentcategory.WSTContentCategory;
import com.newsplore.tree.Node;

public class TxtrLibBrowser {

	private static final String USER_NATURE = "txtr.de";
  public static final String CATALOG_SERVICE = "http://txtr.com/WSContentCategoryMgmtService/WSContentCategoryMgmt";
	IWSContentCategoryMgmt categoryManager;
	
	public  TxtrLibBrowser() {
		JaxWsProxyFactoryBean jwpfb = new JaxWsProxyFactoryBean();
		jwpfb.setAddress(CATALOG_SERVICE);
		jwpfb.setServiceClass(IWSContentCategoryMgmt.class);
		categoryManager = (IWSContentCategoryMgmt)jwpfb.create();
	}

	public Node getAllCategories() throws WSException {
		ArrayList<WSTContentCategory> cats = categoryManager.getCatalogContentCategoryRootsForUser(USER_NATURE, -1, 70000);
		WSTContentCategory rootCat = new WSTContentCategory();
		rootCat.setName("root");
	  Node root = new Node(rootCat, null);
		digestCategory(cats, root);
		return root;
	}

	private void digestCategory(ArrayList<WSTContentCategory> cats, Node node) throws WSException {
		for(WSTContentCategory cat : cats) {
		  Node newNode = new Node(cat, node);
      node.addChild(newNode);
			for(String catId : cat.getChildrenIDs()) {
				ArrayList<WSTContentCategory> subCats = categoryManager.getChildren(USER_NATURE, catId, 100, 0, 1000);
				if(subCats != null) {
					digestCategory(subCats, newNode);
				}
			}
	    System.out.println(newNode);
		}
	}
}
