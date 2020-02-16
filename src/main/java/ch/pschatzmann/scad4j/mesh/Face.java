package ch.pschatzmann.scad4j.mesh;

import java.util.ArrayList;
import java.util.List;

/**
 * A triangle consists of 3 vectors
 * @author pschatzmann
 *
 */
public class Face {
	private Edge v1,v2,v3;
	private float[] normals;
	private Material material;
	private List<Face> faces = new ArrayList();
	
	Face(Edge v1, Edge v2, Edge v3){
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
	}
	
	public Edge[] getEdges() {
		Edge[] result = {v1, v2,v3};
		return result;
	}

	public float[] getNormals() {
		return normals;
	}

	public void setNormals(float[] normals) {
		this.normals = normals;
	}
	
	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append(v1);
		sb.append("=>");
		sb.append(v2);
		sb.append("=>");
		sb.append(v3);
		sb.append("}");
		return sb.toString();
	}
		
	
}
