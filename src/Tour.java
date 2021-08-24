
public class Tour implements TourInterface {

	private Node head;
	private Node lastNode;
	
	public Tour() {
		head = null;
		lastNode = null;
	}
	
	@Override
	public void draw(Point p) {
		Node temp = head;
		if (head == null) {
			return;
		}
		while (temp != lastNode) {
			if (temp == head || temp.next == lastNode) {
				PennDraw.setPenColor(PennDraw.RED);	
				
			} else {
				PennDraw.setPenColor(PennDraw.BLACK);	
			}
			Point curPoint = temp.point;
			curPoint.drawTo(temp.next.point);
			temp = temp.next;
		}
		
	}

	@Override
	public int size() {
		int count = 0;
		Node temp = head;
		if (temp == null) {
			return count;
		}
		while (temp != lastNode) {
			count++;
			temp = temp.next;
		}
		return count;
	}

	@Override
	public double distance() {
		double distance = 0;
		Node temp = head;
		if (temp == null) {
			return distance;
		}
		while (temp != lastNode) {
			distance += temp.point.distanceTo(temp.next.point);
			temp = temp.next;
		}
		return distance;
	}

	@Override
	public void insertInOrder(Point p) {
		if (head == null) {
			lastNode = new Node(null, p);
			head = new Node(lastNode, p);
		} else {
			Node temp = head;
			while (temp.next != lastNode) {
				temp = temp.next;
			}
			temp.next = new Node(lastNode, p);
		}
		
	}

	@Override
	public void insertNearest(Point p) {
		if (head == null) {
			lastNode = new Node(null, p);
			head = new Node(lastNode, p);
		} else {
			double minDist = Double.MAX_VALUE;
			Node temp = head;
			Node min = temp;
			while (temp != lastNode) {
				if (temp.point.distanceTo(p) < minDist) {
					minDist = temp.point.distanceTo(p);
					min = temp;
				}
				temp = temp.next;
			}
			Node newNode = new Node(min.next, p);
			min.next = newNode;
		}
	}

	@Override
	public void insertSmallest(Point p) {
		if (head == null) {
			lastNode = new Node(null, p);
			head = new Node(lastNode, p);
		} else {
			double minDist = Double.MAX_VALUE;
			Node temp = head;
			Node min = temp;
			while (temp != lastNode) {
				double sp = temp.point.distanceTo(p);
				double pt = p.distanceTo(temp.next.point);
				double st = temp.point.distanceTo(temp.next.point);
				double incrementalDist = sp + pt - st;
				if (incrementalDist < minDist) {
					minDist = incrementalDist;
					min = temp;
				}
				temp = temp.next;
			}
			Node insert = new Node(min.next, p);
			min.next = insert;
		}
		
	}
	
	@Override
	public String toString() {
		String ans = "";
		Node temp = head;
		while (temp != null) {
			ans += temp.point.toString() + "\n";
			temp = temp.next;
		}
		return ans;
	}

}
