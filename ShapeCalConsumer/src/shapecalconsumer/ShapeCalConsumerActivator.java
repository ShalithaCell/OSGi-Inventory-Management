package shapecalconsumer;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import shapecalservice.ShapeCalService;


public class ShapeCalConsumerActivator implements BundleActivator {

	ServiceReference serviceReference;
	
	public void start(BundleContext context) throws Exception {
		System.out.println("Cooray M.D.D.M  IT18156652");
		System.out.println("Inventory Calculation of Area , Perimeter , Circumference of Shapes in a Building");
		System.out.println(" ");
		System.out.println("Calculate subscriber Service Start");
		
		serviceReference = context.getServiceReference(ShapeCalService.class.getName());
		ShapeCalService cal = (ShapeCalService) context.getService(serviceReference);
		
		Scanner sc = new Scanner(System.in);
		double n1 = 0,n2 = 0,r = 0,n = 0,n3 = 0, result = 0;
		
		//user input the shape of an object

		System.out.println(" ");
 		System.out.print("Enter the shape of the object (square/rectangle/triangle/circle/trapezium/parellelogram):"); 
 		String s = sc.next();  
 		
 		System.out.println("***************************");
		System.out.println("You can calcaulate,");
		System.out.println();
		System.out.println("   Square = Area/Perimeter");
		System.out.println("   Rectangle = Area/Perimeter");
		System.out.println("   Triangle = Area");
		System.out.println("   Circle = Area/Circumference");
		System.out.println("   Trapezium = Area");
		System.out.println("   Parellelogram = Area");
		System.out.println("***************************");
		System.out.println();
		
		
		//user input what they want to calculate
 		System.out.print("Calculate area/perimeter/circumference: ");
  	   	String s2 = sc.next();
  	   	
  	   	//entering the values of parameter according the shape
  	   	switch(s) {
  	   	
  	   		case "square":
  	   			System.out.println("Enter side length: ");
  	   			n = sc.nextDouble();
  	   			break;
			
			case "rectangle":
				System.out.println("Enter length: ");
	        	n1 = sc.nextDouble();
				
				System.out.println("Enter height: ");
	        	n2 = sc.nextDouble();
	        	break;

			case "triangle":
				System.out.println("Enter perpendicular height: ");
	        	n1 = sc.nextDouble();
	        	
	        	System.out.println("Enter side legth: ");
	        	n2 = sc.nextDouble();
				break;
				
			case "trapezium":
				System.out.println("Enter length 1: ");
	        	n1 = sc.nextDouble();
	        	
	        	System.out.println("Enter legth 2: ");
	        	n2 = sc.nextDouble();
	        	
	        	System.out.println("Enter height: ");
	        	n3 = sc.nextDouble();
				break;
				
			case "parellelogram":
				System.out.println("Enter length: ");
	        	n1 = sc.nextDouble();
	        	
	        	System.out.println("Enter height: ");
	        	n2 = sc.nextDouble();
				break;
				
			case "circle":
  	   			System.out.println("Enter radius: ");
  	   			r = sc.nextDouble();
  	   			break;
  	   			
  	   		default:
  	   		   System.out.println("Something went wrong! Try Again!!!");
  	   		    break;
  	   	}
       
  	   	
  	   	//calculate the value according the shape and requirement
       
  	      if(s.equals("square") && s2.equals("area")){
	    	  
	    	 result = cal.sarea(n);
	    	  
	      }else if (s.equals("square") && s2.equals("perimeter")){
	    	  
	    	 result = cal.sperimeter(n);
	    	  
	      }else if(s.equals("rectangle") && s2.equals("area")) {
 	    	  
 	    	  result = cal.rarea(n1, n2);
 	    	  
 	      }else if (s.equals("rectangle") && s2.equals("perimeter")){
 	    	  
 	    	 result = cal.rperimeter(n1, n2);
 	    	  
 	      }else if (s.equals("triangle") && s2.equals("area")){
 	    	  
 	    	 result = cal.tarea(n1, n2);
 	    	  
 	      }else if (s.equals("circle") && s2.equals("area")){
 	    	  
 	    	 result = cal.carea(r);
 	    	  
 	      }else if (s.equals("circle") && s2.equals("circumference")){
 	    	  
 	    	 result = cal.ccircumference(r);
 	    	  
 	      }else if(s.equals("trapezium") && s2.equals("area")) {
 	    	  
 	    	  result = cal.trarea(n1, n2, n3);
 	    	  
 	      }else if(s.equals("parellelogram") && s2.equals("area")) {
 	    	  
 	    	  result = cal.parea(n1, n2);
 	    	  
 	      }
 	      
  	      //display the result
  	      
 	      System.out.println("Result : " + result);
		
		
	}
	
	
	

	public void stop(BundleContext context) throws Exception {
		
		System.out.println("Thank you!");
		context.ungetService(serviceReference);
		
		
	}

}
