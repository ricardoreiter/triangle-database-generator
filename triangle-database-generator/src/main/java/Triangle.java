import org.opencv.core.Point;

public class Triangle {

	private double a;
	private double b;
	private double c;
	
	public Triangle(double a, double b, double c) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public double area() {
		double s = (this.a + this.b + this.c) / 2;
		return Math.sqrt(s * (s - this.a) * (s - this.b) * (s - this.c));
	}
	
	public double perimeter() {
		return this.a + this.b + this.c;
	}
	
	public Point[] points(double[] options) {
		double[] interval = (options == null) ? new double[] {GeneratorMain.IMAGE_SIZE / 2, GeneratorMain.IMAGE_SIZE / 2} : options;
		Point p1 = new Point(RNG.random(interval), RNG.random(interval));
		double alpha = RNG.random(new double[] {0, 2 * Math.PI});
		
		Point p2 = new Point(p1.x + this.a * Math.cos(alpha), p1.y + this.a * Math.sin(alpha));
		
		double beta = Math.acos((this.a*this.a + this.c*this.c - this.b*this.b)/(2*this.a*this.c)) + alpha;
		Point p3 = new Point(p1.x + this.c * Math.cos(beta), p1.y + this.c * Math.sin(beta));
		
		return new Point[] {p1, p2, p3};
	}
	
	public double getA() {
		return a;
	}

	public void setA(double a) {
		this.a = a;
	}

	public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}

	public double getC() {
		return c;
	}

	public void setC(double c) {
		this.c = c;
	}
	
}
