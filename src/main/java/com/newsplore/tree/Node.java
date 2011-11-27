package com.newsplore.tree;

import java.util.ArrayList;
import java.util.List;

import com.bookpac.server.contentcategory.WSTContentCategory;

public class Node {

  private WSTContentCategory data;

  private Node parent;
  private List<Node> children;

  public Node(WSTContentCategory rootCategory, Node parent) {
    data = rootCategory;
    children = new ArrayList<Node>();
    this.parent = parent;
  }

  public void addChild(Node node) {
    children.add(node);
  }
  
  public List<Node> getChildren() {
    return children;
  }
  
  public Node getParent() {
    return parent;
  }

  public WSTContentCategory getData() {
    return data;
  }
  
  public String toString() {
    StringBuffer sb = new StringBuffer();
    getPath(this, sb);
    return sb.toString() + "(" + data.getSize() + ")";
  }
  private void getPath(Node node, StringBuffer sb) {
    if(node != null) {
      getPath(node.getParent(), sb);
      sb.append(node.getData().getName() + "[" + node.getData().getID() + "]").append("->");
    }
  }
  
}