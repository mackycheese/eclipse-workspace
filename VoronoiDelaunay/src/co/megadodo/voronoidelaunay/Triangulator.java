package co.megadodo.voronoidelaunay;

import java.util.ArrayList;

public class Triangulator {

	public static int numOccur(Line l, ArrayList<Line> lines) {
		int n = 0;
		for (Line l1 : lines) {
			if (Line.isSame(l, l1))
				n++;
		}
		return n;
	}
	public static Vector2f bad1 = new Vector2f(0, 0);
	public static Vector2f bad2 = new Vector2f(15000, 0);
	public static Vector2f bad3 = new Vector2f(0, 15000);

	public static ArrayList<Triangle> triangulation(ArrayList<Vector2f> points) {
		ArrayList<Triangle> tris = new ArrayList<Triangle>();
		


		tris.add(new Triangle(bad1, bad2, bad3));

		for (Vector2f point : points) {
			ArrayList<Triangle> badTris = new ArrayList<Triangle>();
			for (int i = 0; i < tris.size(); i++) {
				Triangle tri = tris.get(i);
				Vector2f center = tri.circumcenter();
				float rad = tri.circumrad();
				if (Vector2f.dist(center, point) < rad) {
					badTris.add(tri);
				}
			}
			// ArrayList<Line>lineList=new ArrayList<Line>();
			ArrayList<Line> allLines = new ArrayList<Line>();
			ArrayList<Triangle> newTris = new ArrayList<Triangle>();
			for (Triangle t : badTris) {
				allLines.add(t.lineAB());
				allLines.add(t.lineBC());
				allLines.add(t.lineAC());
			}
			for (Triangle t : badTris) {
				Line ab = t.lineAB();
				Line bc = t.lineBC();
				Line ac = t.lineAC();
				if (numOccur(ab, allLines) == 1)// implement this method
					newTris.add(new Triangle(t.a, t.b, point));
				if (numOccur(bc, allLines) == 1)
					newTris.add(new Triangle(t.b, t.c, point));
				if (numOccur(ac, allLines) == 1)
					newTris.add(new Triangle(t.a, t.c, point));
			}
			tris.removeAll(badTris);
			tris.addAll(newTris);
			System.out.println(badTris.size() + " bad tris");
			System.out.println(newTris.size() + " new tris");
			System.out.println("Point end");
		}
//		 ArrayList<Triangle> extTris = new ArrayList<Triangle>();
//		 for(Triangle t : tris) {
//		 if(t.a.equals(bad1)||t.a.equals(bad2)||t.a.equals(bad3)
//		 ||t.b.equals(bad1)||t.b.equals(bad2)||t.b.equals(bad3)
//		 ||t.c.equals(bad1)||t.c.equals(bad2)||t.c.equals(bad3)) {
//		 extTris.add(t);
//		 }
//		 }
//		 tris.removeAll(extTris);
		return tris;
	}

}
