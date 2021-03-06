package co.megadodo.voronoidelaunay;

public class Triangle {
	
	public Vector2f a;
	public Vector2f b;
	public Vector2f c;
	
	public Triangle() {
		
	}
	
	public Triangle(Vector2f a, Vector2f b, Vector2f c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	public Line lineAB() {
		return new Line(a, b);
	}
	
	public Line lineBC() {
		return new Line(b, c);
	}
	
	public Line lineAC() {
		return new Line(a, c);
	}
	
	public Vector2f circumcenter() {
		return Line.intersection(Line.bisector(lineAB(),10), Line.bisector(lineBC(),10));
	}
	
	public float circumrad() {
		return Vector2f.dist(a, circumcenter());
	}

}
