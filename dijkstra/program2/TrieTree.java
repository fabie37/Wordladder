package program2;
import java.util.HashMap;

public abstract class TrieTree<Type> {
	
	protected TrieNode root;
	
	public TrieTree() {
		this.root = new TrieNode();
	}
	
	public TrieNode getRoot() {
		return this.root;
	}
	
	public class TrieNode {
		protected Type data;
		protected HashMap<Type, TrieNode> children;
		protected Type index;
		
		public TrieNode() {
			this.data = null;
			this.index = null;
			this.children = new HashMap<Type, TrieNode>();
		}
		
		public void setIndex(Type c) {
			this.index = c;
		}
		
		public Type getIndex() {
			return this.index;
		}
		
		public void addChild(Type c) {
			this.children.put(c, new TrieNode());
			this.children.get(c).setIndex(c);
		}
		
		public TrieNode getChild(Type c) {
			return this.children.get(c);
		}
		
		public HashMap<Type, TrieNode> getChildren() {
			return this.children;
		}
		
		public void setData(Type data) {
			this.data = data;
		}
		
		public Type getData() {
			return this.data;
		}
	}
}
