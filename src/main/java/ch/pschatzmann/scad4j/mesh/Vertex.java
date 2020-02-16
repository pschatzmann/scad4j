package ch.pschatzmann.scad4j.mesh;

import java.util.ArrayList;
import java.util.List;

/**
 * A vertex consists of 3 dimensions
 * 
 * @author pschatzmann
 *
 */
public class Vertex implements Comparable<Vertex> {
	protected float x,y,z;
	protected List<Edge> edges = new ArrayList();
	
	public Vertex(Mesh mesh, float value[]){
		this.x = value[0];
		this.y = value[1];
		this.z = value[2];
	}

	public Vertex(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}

	protected int sign(double result) {
		if (result < 0) return -1;
		if (result > 0) return 1;
		return 0;
	}
	
	public float[] toArray() {
		float result[] = new float[3];
		result[0] = x;
		result[1] = y;
		result[2] = z;
		return result;
	}
	
	public List<Edge> getEdge() {
		return this.edges;
	}
	
	public void addEdge(Edge edge) {
		this.edges.add(edge);
	}

	@Override
	public int compareTo(Vertex o) {
		double result = this.x - o.x;
		if (result!=0.0) {
			return sign(result);
		}
		result = this.y - o.y;
		if (result!=0.0) {
			return sign(result);
		}
		result = this.z - o.z;
		if (result!=0.0) {
			return sign(result);
		}
		return 0;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append(x);
		sb.append(",");
		sb.append(y);
		sb.append(",");
		sb.append(z);
		sb.append("]");
		return sb.toString();
	}

}
