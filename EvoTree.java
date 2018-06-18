/** */
import java.util.ArrayList;
import java.util.Scanner;

/*
* Generates a phylogenetic tree representation
* from a phylogenetic data sentences of form "X evolved from Y"
* The EvoTree provides a methods:
*  ArrayList<String> compute(Scanner log) : Takes a scanner stream of
*  											phylogenetic data sentences and 
*  											provides a tree representation
* @author  Gayathri Balasubramanian
*/
public class EvoTree implements Tester{
	/*
	* Building entity of the phylogenetic tree
	* @Ref  https://stackoverflow.com/questions/3522454/java-tree-data-structure
	*/
	public class Node{
        private String data;
        private ArrayList<Node> children;
        
        public Node (String value){
    		data = value;
    		children = new ArrayList<Node>();
    	}
    }

	private Node root = new Node("");
	private ArrayList<String> output = new ArrayList<String>();
	
	/**
	 * Main method to start the execution using console input
	 * Calls the compute method and prints output
	 */
	public static void main() {
		Scanner objScanner = new Scanner(System.in);
		ArrayList<String> printOutput  = new EvoTree().compute(objScanner);
		
		for (int i = 0; i < printOutput.size(); i++) {
			System.out.println(printOutput.get(i));
		}
	}
		
	/**
	 * Overriding interface method to compute evolution tree
	 * 
	 * @param log Scanner object containing phylogenetic sentences
	 * @return ArrayList<String> Each element contains a line of output
	 */
	@Override
	public ArrayList<String> compute(Scanner log) {
		String line = log.nextLine();
		
		while (!line.equals("end")) {
			// Invoke insert with the parsed parent and child
			Insert(line.substring(line.indexOf(" evolved from")+14), 
					line.substring(0, line.indexOf(" evolved from")));
			line = log.nextLine();
		}
		// Store the tree in array list of required format
		if (!root.children.isEmpty()) {
			Traverse(root.children.get(0), "", "", "");
		}
		return output;
	}

	/**
	 * Find/create the parent position to insert the child 
	 * 
	 * @param parent string value of the parent node
	 * 		  child string value of the child node to be inserted
	 */
	private void Insert (String parent, String child) {
		Node insertPoint = find(root, parent);
		Node newNode = new Node(child);
		// Create parent as level 1 node, if not found
		if (insertPoint == null) {
			insertPoint = new Node(parent);
			root.children.add(insertPoint);
		}
		// Check and re-assign if child is an existing level 1 node
		for (Node rootlist : root.children) {
			if (rootlist.data.equals(child)) {
				newNode = rootlist;
				root.children.remove(newNode);
				break;
			}
		}
		InsertChild(insertPoint, newNode);		
	}
	 
	/**
	 * Find the node under the tree/subtree that contains the provided data
	 * 
	 * @param root Node object giving the root of tree/subtree to be searched
	 * 		  parent string value that is the searched for data
	 * @return Node containing the identified position or null if not found
	 */
	private Node find (Node root,String parent)	{
		Node result = root;
		if (!root.data.equals(parent)) {
			for (Node child : root.children) {
				result = find(child, parent);
				if (result != null) {
					return result;
				}
			}
			return null;
		}
		return root;
	}
	
	/**
	 * Insert/add child to a parent based on the lexical order between siblings 
	 * 
	 * @param parent Node object under which the child is to be added
	 * 		  newNode Node object that is added to the tree
	 */
	private void InsertChild (Node parent, Node newNode) {
		int i = 0;
		for (Node child : parent.children) {
			if (child.data.compareTo(newNode.data) > 0)	{
				break;
			}
			i++;
		}
		parent.children.add(i, newNode);
	}
	
	/**
	 * Pre-order traversal (recursive) of the tree to generate output format
	 * 
	 * @param root Node object that is currently being processed for output
	 * 		  indent string value to be used as suffix of output
	 * 		  addIndent string value to be added with indent for next level
	 * 		  endIndent string value that sparates indent and data 
	 */
	private void Traverse (Node root, 
			String indent, 
			String addIndent,
			String endIndent) {
		output.add(indent+endIndent+root.data);
		for (Node child : root.children) {
			Traverse(child, indent+addIndent, "| ", "|-");
		}
	}
}
