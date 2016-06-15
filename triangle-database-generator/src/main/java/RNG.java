
public class RNG {

	public static double random(double[] interval) {
		double res = 0;
		if (interval == null) {
			res = Math.random();
		} else if (interval.length == 2 && interval[0] >= 0 && interval[0] <= interval[1]){
			res = interval[0] + (interval[1] - interval[0]) * Math.random();
		}
		return res;
	}
	
}
