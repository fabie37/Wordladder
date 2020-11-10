package program2;

import java.util.Comparator;

public class AdjListNodeComparator implements Comparator<AdjListNode> {

	@Override
	public int compare(AdjListNode n1, AdjListNode n2) {
		// TODO Auto-generated method stub
		return Integer.compare(n1.getWeight(), n2.getWeight());
	}

}
