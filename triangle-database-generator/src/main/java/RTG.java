
public class RTG {

	private double[] _interval = {100, GeneratorMain.IMAGE_SIZE / 3};
	
	public Triangle scalene() {
		double a = RNG.random(this._interval);
		double b = RNG.random(this._interval);
		double c = RNG.random(new double[] {Math.abs(a-b), a+b});
		return new Triangle(a, b, c);
	}
	
	public Triangle isocele() {
		double a = RNG.random(this._interval);
		double c = RNG.random(new double[] {0, 2 * a});
		return new Triangle(a, a, c);
	}
	
	public Triangle equilateral() {
		double a = RNG.random(this._interval);
		return new Triangle(a, a, a);
	}
	
}
