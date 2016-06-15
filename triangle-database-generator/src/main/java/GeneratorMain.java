import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;

public class GeneratorMain {

	public static final int IMAGE_SIZE = 1000;
	private static final String EQUILATERAL_FOLDER = "C:/temp/equilateral";
	private static final String ISOCELES_FOLDER = "C:/temp/isoceles";
	private static final String SCALENE_FOLDER = "C:/temp/scalene";
	
	public static void main(String[] args) {
		System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
		
		RTG myRTG = new RTG();
		for (int i = 0; i < 100; i++) {
			String equilateralFileName = String.format("%s%s%s.jpg", EQUILATERAL_FOLDER, File.separator, i);
			String scaleneFileName = String.format("%s%s%s.jpg", ISOCELES_FOLDER, File.separator, i);
			String isocelesFileName = String.format("%s%s%s.jpg", SCALENE_FOLDER, File.separator, i);
			
			if (!new File(equilateralFileName).exists()) {
				Triangle equilateral = myRTG.equilateral();
				createImageForTriangle(equilateral, equilateralFileName);
			}
			
			if (!new File(scaleneFileName).exists()) {
				Triangle scalene = myRTG.scalene();
				createImageForTriangle(scalene, scaleneFileName);
			}
			
			if (!new File(isocelesFileName).exists()) {
				Triangle isocele = myRTG.isocele();
				createImageForTriangle(isocele, isocelesFileName);
			}
		}
	}
	
	private static void createImageForTriangle(Triangle triangle, String fileName) {
		Mat image = Mat.zeros(IMAGE_SIZE, IMAGE_SIZE, CvType.CV_8UC3);
		image.setTo(new Scalar(255, 255, 255));
		MatOfPoint pts = new MatOfPoint(triangle.points(null));
		List<MatOfPoint> list = new LinkedList<>();
		list.add(pts);
		Core.fillPoly(image, list, new Scalar(0, 0, 0));
		Highgui.imwrite(fileName, image);
	}
	
}
