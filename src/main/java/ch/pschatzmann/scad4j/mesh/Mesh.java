package ch.pschatzmann.scad4j.mesh;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * A mash is a representation of objects with the help of triangles.
 * 
 * @author pschatzmann
 *
 */
public class Mesh {
	private Map<Vertex,Vertex> vertextMap = new TreeMap();
	private Map<Edge,Edge> vectorMap = new TreeMap();
	private List<Face> facesList = new ArrayList();
	
	public Vertex uniqueVertex(Vertex v) {
		Vertex result = vertextMap.get(v);
		if (result == null) {
			vertextMap.put(v,v);
			result = v;
		} 
		return result;
	};
	
	public Edge uniqueEdge(Vertex from, Vertex to) {
		Vertex fromUnique = uniqueVertex(from);
		Vertex toUnique = uniqueVertex(to);
		Edge result = uniqueEdge(new Edge(fromUnique,toUnique));
		return result;
	};
	
	public Edge uniqueEdge(Edge vector) {
		Edge result = vectorMap.get(vector);
		if (result==null) {
			vectorMap.put(vector,vector);
			result = vector;
		}
		return result;
	}
	
	public Face addFace(Edge v1, Edge v2, Edge v3) {
		Face result = new Face(uniqueEdge(v1),uniqueEdge(v2),uniqueEdge(v2));
		facesList.add(result);
		
		// update cross references
		for (Edge edge : result.getEdges()) {
			for (Vertex v :edge.getVertices()){
				v.addEdge(edge);
			}
		}
		
		return result;
	};
	
	public List<Face> getFaces(){
		return this.facesList;
	}

	public Set<Vertex> getVertexSet(){
		return this.vertextMap.keySet();
	}

	public Set<Edge> getVectorSet(){
		return this.vectorMap.keySet();
	}

	public float[][][] toArray() {
		float[][][] result = new float[getFaces().size()][2*3][3];
		int facesPos = 0;
		int edgesPos = 0;
		int vertexPos = 0;
		int dimensionPos = 0;
		for (Face t : getFaces()) {
			edgesPos = 0;
			for (Edge v : t.getEdges()) {
				vertexPos = 0;
				for (Vertex vx : v.getVertices()) {
					dimensionPos = 0;
					for (float value : vx.toArray()) {
						result[facesPos][vertexPos+edgesPos][dimensionPos]  = value;
						dimensionPos++;
					}
					vertexPos++;
				}
				edgesPos++;
			}
			facesPos++;
		}
		return result;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(System.lineSeparator());
		for (Face face : facesList) {
			sb.append(face);
			sb.append(System.lineSeparator());			
		}
		return sb.toString();
	}
	
}
