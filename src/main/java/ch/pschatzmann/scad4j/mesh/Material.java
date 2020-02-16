package ch.pschatzmann.scad4j.mesh;

public class Material {
	private String name;
	
	public Material(){}
	public Material(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
