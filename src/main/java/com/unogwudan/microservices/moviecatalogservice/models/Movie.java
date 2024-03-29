package com.unogwudan.microservices.moviecatalogservice.models;

public class Movie {
	
	private String id;
	private String name;
	private String desc;

	public Movie() {}
	
	public Movie(String id, String name, String desc) {
		this.id = id;
		this.name = name;
		this.desc = desc;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", name=" + name + ", desc=" + desc + "]";
	}

}
