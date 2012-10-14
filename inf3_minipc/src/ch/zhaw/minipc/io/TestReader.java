package ch.zhaw.minipc.io;

public class TestReader {

	    public static void main(String[] args) {
	        CodeReader blubb  = new CodeReader();
//	        System.out.println(blubb.readCodeFromFile("ch/zhaw/minipc/data/Testfile.txt").toString());
//	        System.out.println(blubb.readParameterFromFile("ch/zhaw/minipc/data/Testfile.txt").toString());
	        System.out.println(blubb.readCodeFromFile("/Users/pascal/Downloads/blubb.txt").toString());
	        System.out.println(blubb.readParameterFromFile("/Users/pascal/Downloads/blubb.txt").toString());
	    }
}
