package test;
import classifier.*;
import junit.framework.TestCase;



public class TestClassifier extends TestCase {

	public TestClassifier(String arg0) {
		super(arg0);
	}

    private DecisionTree buildTree(){
		Node root = new Node("AC");
		
		Node n1=new Node("ABS");
		Node n2=new Node("ABS");
		
		root.addChild("yes",n1);
		root.addChild("no",n2);
		
		// leaves
		Node h1 = new Node("DOGGY");
		Node h2 = new Node("DOGGY");
		Node h3 = new Node("DOGGY");
		Node h4 = new Node("DOGGY");

		n1.addChild("yes", h1);
		n1.addChild("no", h2);
		
		n2.addChild("yes", h3);
		n2.addChild("no", h4);
		
		// leaves
		Node l1 = new Node("high+");
		Node l2 = new Node("high");
		Node l3 = new Node("medium+");
		Node l4 = new Node("medium");
		Node l5 = new Node("medium+");
		Node l6 = new Node("medium");
		Node l7 = new Node("low+");
		Node l8 = new Node("low");

		h1.addChild("yes", l1);
		h1.addChild("no", l2);
		
		h2.addChild("yes", l3);
		h2.addChild("no", l4);
		
		h3.addChild("yes", l5);
		h3.addChild("no", l6);
		
		h4.addChild("yes", l7);
		h4.addChild("no", l8);		
		
		return new DecisionTree(root);
    }
	public void testCategory(){
		
		DecisionTree dt = buildTree();

		FeatureType yn = new FeatureType("YesNo"
						,new String[]{"yes","no"});

		Feature[] features = new Feature[] { 
			new Feature("AC",		"yes", yn),
			new Feature("ABS",		"yes", yn),
			new Feature("DOGGY",	"yes", yn)
		};
		
		Item item = new Item("car",features);
		
		String category = dt.assignCategory(item);
		assertEquals("high+",category);
		
		item.setFeatureValue("AC","no");
		category = dt.assignCategory(item);
		assertEquals("medium+",category);

		item.setFeatureValue("ABS","no");
		category = dt.assignCategory(item);
		assertEquals("low+",category);
		
		item.setFeatureValue("DOGGY","no");
		category = dt.assignCategory(item);
		assertEquals("low",category);
	}
}
