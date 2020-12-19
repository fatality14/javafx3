package application;

import java.io.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Scanner;

public class Tools
{
	public static class file{
		@SafeVarargs
		public static <T> void serialize(String filePath, final T... args) {
			try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath)))
	        {
				for(final T i : args) {
					oos.writeObject(i);
				}
	        }
	        catch(Exception e){
	        	System.out.println("An error occurred.");
				e.printStackTrace();
				System.exit(0);
	        }
		}
		public static ArrayList<Object> deserialize(String filePath) {
			ArrayList<Object> result = new ArrayList<Object>();
			
			File f = new File(filePath);
    		if(f.exists() && !f.isDirectory()) {
    			try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(f)))
    			{
    				while(true) {
    					result.add(in.readObject());
    				}
    			}
    			catch(EOFException e) {
    				//no need to do anything, the file is read
    			}
    			catch(IOException e)
    			{
    			    System.out.println("IOException is caught");
    			    e.printStackTrace();
    			    System.exit(0);
    			}
    			catch(ClassNotFoundException e)
    			{
    			    System.out.println("ClassNotFoundException is caught");
    			    e.printStackTrace();
    			    System.exit(0);
    			}
    		}
    		else {
    			Tools.file.createFile(filePath, "");
    		}
			
			return result;
		}
		
		/* method create a new file and write content in
	    /* method doesn't rewrite the content
	     */
	    public static void appendToFile(String path, String content) {
	    	try {
	    		if(content.charAt(content.length()-1) != '\n') {
	    			content += "\n";
	    		}
	    		
	    		File f = new File(path);
	    		if(f.exists() && !f.isDirectory()) {
	    			FileWriter writer = new FileWriter(f, true);
			        writer.write(content);
			        writer.close();
	    		}
	    		else {
	    			Tools.file.createFile(path, "");
	    		}
			}
			catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
				System.exit(0);
			}
	    }
	    
	    //method create a new file and write content in
	    public static void createFile(String path, String content) {
	    	try {
				FileWriter writer = new FileWriter(path);
		        writer.write(content);
		        writer.close();
	    	}
			catch (IOException e) {
				System.out.println("An error occured.");
				e.printStackTrace();
				System.exit(0);
			}
	    }
	    
	    //method to read content of the file
	    public static String readFile(String path) {
	    	String result = "";
	    	try {
	    		File f = new File(path);
	    		if(f.exists() && !f.isDirectory()) {
	    			FileReader reader = new FileReader(f);
		    		Scanner scan = new Scanner(reader);
		    		while (scan.hasNextLine()) {
		               result += scan.nextLine() + "\n";
		            }
		    		scan.close();
		    		reader.close();
	    		}
	    		else {
	    			Tools.file.createFile(path, "");
	    		}
	    	}
	    	catch(IOException e) {
	    		System.out.println("File not found or error occured.");
				e.printStackTrace();
				System.exit(0);
	    	}
	    	return result;
	    }
	}
	
	//prints indefinite amount of arguments
    @SafeVarargs
	public static <T> void print(final T... args) {
        for (final T i : args) {
            System.out.print(i);
        }
    }
    
    /* implement this interface like:
     * Tools.Func myFunc = () -> { }
     */
    public static interface Func extends Runnable{
    	public void run();
    }
    
    //run this method using Func or Runnable object
    public static long countTime(Func toRun) {
    	long now1 = System.currentTimeMillis();
    	toRun.run();
	    long now2 = System.currentTimeMillis();
	    //Tools.print("Elapsed milliseconds: ",now2-now1,"\n");
	    return now2-now1;
    }
    
    public static boolean isSameType(Object obj1, Object obj2) {
    	if(obj1.getClass() == obj2.getClass()) {
    		return true;
    	}
    	return false;
    }
    public static boolean isSameType(Object ob, Class cl) {
    	if(ob.getClass() == cl) {
    		return true;
    	}
    	return false;
    }
    
    public static float genRandomNum(float min, float max) {
    	return (float)(Math.random() * ((max - min) + 1)) + min;
    }
    public static double genRandomNum(double min, double max) {
    	return (Math.random() * ((max - min) + 1)) + min;
    }
    public static int genRandomNum(int min, int max) {
    	return (int)(Math.random() * ((max - min) + 1)) + min;
    }
    public static boolean genRandomBool() {
    	float rnd = Tools.genRandomNum(-1, 1);
    	return rnd < 0 ? false : true;
    	
    }
    public static String genRandomString(int len) {
    	final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    	SecureRandom rnd = new SecureRandom();
    	StringBuilder sb = new StringBuilder(len);
	    for(int i = 0; i < len; i++)
    	      sb.append(AB.charAt(rnd.nextInt(AB.length())));
    	return sb.toString();
    }
}
