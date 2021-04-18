package shapecalservice;

import java.math.*;
import java.lang.Math;

public class ShapeCalServiceImpl implements ShapeCalService {
	
		//implenting the methods
	
		//method to calculate the area of a rectangle
		public double rarea(double n1, double n2) {
				
				double value = n1*n2;
				return value;
				
			}
			
		//method to calculate the perimeter of a rectangle
		public double rperimeter(double n1, double n2) {
				
				double value = (n1+n2)*2;
				return value;
				
			}

		//method to calculate the area of a square
		public double sarea(double n) {
			
			double value = n*n;
			return value;
			
		}

		//method to calculate the perimeter of a square
		public double sperimeter(double n) {
			
			double value = n*4;
			return value;
			
		}

		//method to calculate the area of a triangle
		public double tarea(double n1, double n2) {
			
			double value = 0.5*n1*n2;
			return value;
			
		}

		//method to calculate the area of a circle
		public double carea(double r) {
			
			double value = 3.14*r*r;
			return value;
			
		}

		//method to calculate the circumference of a circle
		public double ccircumference(double r) {
			
			double value = 2*3.14*r;
			return value;
			
		}

		//method to calculate the area of a trapezium
		public double trarea(double n1, double n2, double n3 ) {
			
			double value = 0.5*(n1+n2)*n3;
			return value;
			
		}

		//method to calculate the area of a parellelogram
		public double parea(double n1, double n2) {
			
			double value = n1*n2;
			return value;
			
		}

}
