import java.util.Enumeration;
import javax.swing.tree.DefaultMutableTreeNode;


public class MutableTree {

    public static void main(String[] args)
      {
        MutableTree tree = new MutableTree();

          tree.breedteOrdening();
          tree.preOrdening();
          tree.postOrdening();
      }

    private DefaultMutableTreeNode root;
    public MutableTree()
    {
        // Eerste tree bevat alle nodes die ergens boven staan
        DefaultMutableTreeNode person,employee,customer,us_customer;

        //nu alle tree's opbouwend, startend onderaan:
        us_customer = new DefaultMutableTreeNode("us_customer");
        us_customer.add(new DefaultMutableTreeNode("local_customers"));
        us_customer.add(new DefaultMutableTreeNode("regional_customers"));

        employee = new DefaultMutableTreeNode("employee");
        employee.add(new DefaultMutableTreeNode("sales_Rep"));
        employee.add(new DefaultMutableTreeNode("engineer"));

        customer = new DefaultMutableTreeNode("customer");
        customer.add(us_customer);
        customer.add(new DefaultMutableTreeNode("non_us_customer"));

        person = new DefaultMutableTreeNode("person");
        person.add(employee);
        person.add(customer);
        
        //set bovenste tree
        root = person;
    }

    public void breedteOrdening()
    {
        Enumeration<DefaultMutableTreeNode> lijst = root.breadthFirstEnumeration();
        System.out.print("Breedte-ordening: ");
        while(lijst.hasMoreElements())
        {
            System.out.print(lijst.nextElement() + ", ");
        }
        System.out.println("");
    }

    public void preOrdening()
    {
        Enumeration<DefaultMutableTreeNode> lijst = root.preorderEnumeration();
        System.out.print("Pre-ordening: ");
        while(lijst.hasMoreElements())
        {
            System.out.print(lijst.nextElement() + ", ");
        }
        System.out.println("");
    }
    public void postOrdening()
    {
        Enumeration<DefaultMutableTreeNode> lijst = root.postorderEnumeration();
        System.out.print("Post-ordening: ");
        while(lijst.hasMoreElements())
        {
            System.out.print(lijst.nextElement() + ", ");
        }
        System.out.println("");
    }
}