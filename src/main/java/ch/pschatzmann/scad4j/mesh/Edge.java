package ch.pschatzmann.scad4j.mesh;

/**
 * A vector consists of 2 Vertices
 * 
 * @author pschatzmann
 *
 */
public class Edge implements Comparable<Edge> {
	protected Vertex from, to;
	
	Edge(Vertex from, Vertex to){	
		this.from = from;
		this.to = to;
	}

	public Vertex getFrom() {
		return from;
	}
	
	public Vertex getTo() {
		return to;
	}
	
	public Vertex[] getVertices() {
		Vertex result[] = {from, to};
		return result;
	}
	
	@Override
	public int compareTo(Edge other) {
		int result = from.compareTo(other.from);
		if (result!=0) return result;
		result = to.compareTo(other.to);
		return result;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(from);
		sb.append(" -> ");
		sb.append(to);
		return sb.toString();
	}
	

}
