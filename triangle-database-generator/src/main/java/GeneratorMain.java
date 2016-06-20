import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
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
	private static final String OUTPUT_FOLDER = "output";
	private static final String EQUILATERAL_FOLDER = OUTPUT_FOLDER + "/equilateral";
	private static final String ISOCELES_FOLDER = OUTPUT_FOLDER + "/isosceles";
	private static final String SCALENE_FOLDER = OUTPUT_FOLDER + "/scalene";
	
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		RTG myRTG = new RTG();

		createOutputDirs();
		
		for (int i = 0; i < 100; i++) {
			String equilateralFileName = String.format("%s%s%s.jpg", EQUILATERAL_FOLDER, File.separator, i);
			String scaleneFileName = String.format("%s%s%s.jpg", ISOCELES_FOLDER, File.separator, i);
			String isocelesFileName = String.format("%s%s%s.jpg", SCALENE_FOLDER, File.separator, i);

			if (!new File(equilateralFileName).exists()) {
				System.out.println("Criando triangulo equilateral "+equilateralFileName+"...");
				Triangle equilateral = myRTG.equilateral();
				createImageForTriangle(equilateral, equilateralFileName);
			}

			if (!new File(scaleneFileName).exists()) {
				System.out.println("Criando triangulo escaleno "+scaleneFileName+"...");
				Triangle scalene = myRTG.scalene();
				createImageForTriangle(scalene, scaleneFileName);
			}

			if (!new File(isocelesFileName).exists()) {
				System.out.println("Criando triangulo isosceles "+isocelesFileName+"...");
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
	
	private static final void createOutputDirs() {
		File destPath = new File(OUTPUT_FOLDER);
		try {
			if (!destPath.exists()) {
				Files.createDirectories(destPath.toPath());
			} else {
				Files.walkFileTree(destPath.toPath(), new FileVisitor<Path>() {

					@Override
					public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
						Files.delete(dir);
						return FileVisitResult.CONTINUE;
					}

					@Override
					public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
						return FileVisitResult.CONTINUE;
					}

					@Override
					public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
						Files.delete(file);
						return FileVisitResult.CONTINUE;
					}

					@Override
					public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
						return FileVisitResult.CONTINUE;
					}
				});
			}
			Files.createDirectories(new File(EQUILATERAL_FOLDER).toPath());
			Files.createDirectories(new File(ISOCELES_FOLDER).toPath());
			Files.createDirectories(new File(SCALENE_FOLDER).toPath());
		} catch (IOException e) {
			throw new RuntimeException("Não foi possível manipular o diretorio " + destPath, e);
		}
	}

}
