import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
public class Main{
	private static long startTime;
	private static long endTime;
	private static File file=new File("./array_op_times.txt");
	private static BufferedWriter out;
	static{
		try{
			out=new BufferedWriter(new FileWriter(file));
			out.write("Experimental Analysis(Array)\tAll measurements in nanoseconds\n\n");
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	private static void println(Object obj){//less typing
		System.out.println(obj);
	}
	private static void print(Object obj){//less typing
		System.out.print(obj);
	}
	private static void output(HashMap<Character,LongInt> map) throws IOException{//test case steps 2-4
		out.write("Output method\tTime(ns)\n");
		for(char c: map.keySet()){
			print(c+": ");
			startTrial();
			map.get(c).output();
			endTrial();
			out.write("Output for "+c+":\t"+endTime+"\n");
			print("Nodes: ");
			startTrial();
			map.get(c).printNodes();
			endTrial();
			out.write("Nodes for "+c+":\t"+endTime+"\n");
			startTrial();
			println("Number of digits: "+map.get(c).getDigitCount());
			endTrial();
			out.write("Digits of "+c+":\t"+endTime+"\n");
			startTrial();
			println("isNegative: "+map.get(c).getSign()+"\n");
			endTrial();
			out.write("Sign of "+c+":\t"+endTime+"\n\n");
		}
	}
	private static void printUtils(int t) throws IOException{//test case step 5
		out.write("LongIntUtils for "+t+"\tTime(ns)\n");
		println("LongIntUtils for "+t+":");
		startTrial();
		println("overflow: "+LongIntUtils.overflow(t));
		endTrial();
		out.write("Overflow of "+t+":\t"+endTime+"\n");
		startTrial();
		println("underflow: "+LongIntUtils.underflow(t));
		endTrial();
		out.write("Underflow of "+t+":\t"+endTime+"\n");
		startTrial();
		println("upperHalf: "+LongIntUtils.upperHalf(t));
		endTrial();
		out.write("Upper half of "+t+":\t"+endTime+"\n");
		startTrial();
		println("lowerHalf: "+LongIntUtils.lowerHalf(t));
		endTrial();
		out.write("Lower half of "+t+":\t"+endTime+"\n");
		startTrial();
		println("digits: "+LongIntUtils.digits(t));
		endTrial();
		out.write("Digits of "+t+":\t"+endTime+"\n\n");
		println("");
	}
	private static void compare(HashMap<Character,LongInt> map) throws IOException{//test case step 6
		out.write("equalTo\tTime(ns)\tlessThan\tTime(ns)\tgreaterThan\tTime(ns)\n");
		println("equalTo\t\t\tlessThan\t\tgreaterThan");
		println("-----------------------------------------------------------");
		for(char c: map.keySet()){
			for(char ch: map.keySet()){
				startTrial();
				print(c+"="+ch+": "+map.get(c).equalTo(map.get(ch))+"\t\t");
				endTrial();
				out.write(c+"="+ch+":\t"+endTime+"\t");
				startTrial();
				print(c+"<"+ch+": "+map.get(c).lessThan(map.get(ch))+"\t\t");
				endTrial();
				out.write(c+"<"+ch+":\t"+endTime+"\t");
				startTrial();
				println(c+">"+ch+": "+map.get(c).greaterThan(map.get(ch)));
				endTrial();
				out.write(c+">"+ch+":\t"+endTime+"\n");
			}
			out.write("\n");
			println("");
		}
	}
	private static void addition(HashMap<Character,LongInt> map) throws IOException{
		out.write("Addition\tTime(ns)\n");
		println("Addition\n--------");
		for(char c: map.keySet()){
			for(char ch: map.keySet()){
				print(c+"+"+ch+": ");
				startTrial();
				map.get(c).add(map.get(ch)).output();
				endTrial();
				out.write(c+"+"+ch+":\t"+endTime+"\n");
			}
			out.write("\n");
			println("");
		}
	}
	private static void subtraction(HashMap<Character,LongInt> map) throws IOException{
		out.write("Subtraction\tTime(ns)\n");
		println("Subtraction\n-----------");
		for(char c: map.keySet()){
			for(char ch: map.keySet()){
				print(c+"-"+ch+": ");
				startTrial();
				map.get(c).subtract(map.get(ch)).output();
				endTrial();
				out.write(c+"-"+ch+":\t"+endTime+"\n");
			}
			out.write("\n");
			println("");
		}
	}
	private static void multiplication(HashMap<Character,LongInt> map) throws IOException{
		out.write("Multiplication\tTime(ns)\n");
		println("Multiplication\n--------------");
		for(char c: map.keySet()){
			for(char ch: map.keySet()){
				print(c+"*"+ch+": ");
				startTrial();
				map.get(c).multiply(map.get(ch)).output();
				endTrial();
				out.write(c+"*"+ch+":\t"+endTime+"\n");
			}
			out.write("\n");
			println("");
		}
	}
	private static void powers(HashMap<Character,LongInt> map) throws IOException{
		out.write("Power\tTime(ns)\n");
		println("Power\n-----");
		for(char c: map.keySet()){
			print(c+"^5:  ");
			startTrial();
			map.get(c).power(5).output();
			endTrial();
			out.write(c+"^5:\t"+endTime+"\n");
			print(c+"^10: ");
			startTrial();
			map.get(c).power(10).output();
			endTrial();
			out.write(c+"^10:\t"+endTime+"\n");
			print(c+"^20: ");
			startTrial();
			map.get(c).power(20).output();
			endTrial();
			out.write(c+"^20:\t"+endTime+"\n");
			println("");
		}
		out.write("\n");
	}
	private static void startTrial(){
		startTime=System.nanoTime();
	}
	private static void endTrial(){
		endTime=System.nanoTime()-startTime;
	}
	public static void main(String[] args) throws IOException {
		long start=System.nanoTime();
		final int a=2222;
		final int b=99999999;
		out.write("Initializing\tTime(ns)\n");
		startTrial();
		final LongInt A=new LongInt("2222");//initialization, test case step 1
		endTrial();
		out.write("A:\t"+endTime+"\n");
		startTrial();
		final LongInt B=new LongInt("99999999");
		endTrial();
		out.write("B:\t"+endTime+"\n");
		startTrial();
		final LongInt C=new LongInt("-246813575732");
		endTrial();
		out.write("C:\t"+endTime+"\n");
		startTrial();
		final LongInt D=new LongInt("180270361023456789");
		endTrial();
		out.write("D:\t"+endTime+"\n");
		startTrial();
		final LongInt E=new LongInt("1423550000000010056810000054593452907711568359");
		endTrial();
		out.write("E:\t"+endTime+"\n");
		startTrial();
		final LongInt F=new LongInt("-350003274594847454317890");
		endTrial();
		out.write("F:\t"+endTime+"\n");
		startTrial();
		final LongInt G=new LongInt("29302390234702973402973420937420973420937420937234872349872934872893472893749287423847");
		endTrial();
		out.write("G:\t"+endTime+"\n");
		startTrial();
		final LongInt H=new LongInt("-98534342983742987342987339234098230498203894209928374662342342342356723423423");
		endTrial();
		out.write("H:\t"+endTime+"\n");
		startTrial();
		final LongInt I=new LongInt("8436413168438618351351684694835434894364351846843435168484351684684315384684313846813153843135138413513843813513813138438435153454154515151513141592654543515316848613242587561516511233246174561276521672162416274123076527612");
		endTrial();
		out.write("I:\t"+endTime+"\n\n");
		HashMap<Character,LongInt> map=new HashMap<Character,LongInt>();//makes test cases easier to read and type
		map.put('A', A); map.put('B', B); map.put('C', C); map.put('D', D); map.put('E', E);
		map.put('F', F); map.put('G', G); map.put('H', H); map.put('I', I);
		output(map);
		printUtils(a);
		printUtils(b);
		compare(map);
		addition(map);
		subtraction(map);
		multiplication(map);
		powers(map);
		startTrial();
		out.write("Arithmetic Ops\tTime(ns)\n");
		final LongInt J=B.add(C);//for part 2
		endTrial();
		out.write("J=B+C:\t"+endTime+"\n");
		startTrial();
		final LongInt K=C.add(D);
		endTrial();
		out.write("K=C+D:\t"+endTime+"\n");
		startTrial();
		final LongInt L=I.add(I);
		endTrial();
		out.write("L=I+I:\t"+endTime+"\n");
		startTrial();
		final LongInt M=A.add(I);
		endTrial();
		out.write("M=A+I:\t"+endTime+"\n");
		startTrial();
		final LongInt N=B.add(K);
		endTrial();
		out.write("N=B+K:\t"+endTime+"\n");
		startTrial();
		final LongInt O=A.subtract(D);
		endTrial();
		out.write("O=A-D:\t"+endTime+"\n");
		startTrial();
		final LongInt P=C.subtract(D);
		endTrial();
		out.write("P=C-D:\t"+endTime+"\n");
		startTrial();
		final LongInt Q=D.subtract(C);
		endTrial();
		out.write("Q=D-C:\t"+endTime+"\n");
		startTrial();
		final LongInt R=L.subtract(L);
		endTrial();
		out.write("R=L-L:\t"+endTime+"\n");
		startTrial();
		final LongInt S=P.subtract(O);
		endTrial();
		out.write("S=P-O:\t"+endTime+"\n");
		startTrial();
		final LongInt T=N.subtract(Q);
		endTrial();
		out.write("T=N-Q:\t"+endTime+"\n");
		startTrial();
		final LongInt U=A.multiply(D);
		endTrial();
		out.write("U=A*D:\t"+endTime+"\n");
		startTrial();
		final LongInt V=B.multiply(C);
		endTrial();
		out.write("V=B*C:\t"+endTime+"\n");
		startTrial();
		final LongInt W=D.multiply(D);
		endTrial();
		out.write("W=D*D:\t"+endTime+"\n");
		startTrial();
		final LongInt X=O.multiply(I);
		endTrial();
		out.write("X=O*I:\t"+endTime+"\n");
		startTrial();
		final LongInt Y=J.multiply(P);
		endTrial();
		out.write("Y=J*P:\t"+endTime+"\n");
		startTrial();
		final LongInt Z=M.multiply(N);
		endTrial();
		out.write("Z=M*N:\t"+endTime+"\n\n");
		HashMap<Character,LongInt> map2=new HashMap<Character,LongInt>();
		map2.put('K', K); map2.put('L', L); map2.put('M', M); map2.put('N', N); map2.put('O', O);
		map2.put('P', P); map2.put('Q', Q); map2.put('R', R); map2.put('S', S); map2.put('T', T);
		map2.put('U', U); map2.put('V', V); map2.put('W', W); map2.put('X', X); map2.put('Y', Y);
		map2.put('Z', Z);
		out.write("Arithmetic Op Output\tTime(ns)\n");
		print("J=B+C: ");
		startTrial();
		J.output();
		endTrial();
		out.write("Output for J:\t"+endTime+"\n");
		print("K=C+D: ");
		startTrial();
		K.output();
		endTrial();
		out.write("Output for K:\t"+endTime+"\n");
		print("L=I+I: ");
		startTrial();
		L.output();
		endTrial();
		out.write("Output for L:\t"+endTime+"\n");
		print("M=A+I: ");
		startTrial();
		M.output();
		endTrial();
		out.write("Output for M:\t"+endTime+"\n");
		print("N=B+K: ");
		startTrial();
		N.output();
		endTrial();
		out.write("Output for N:\t"+endTime+"\n");
		print("O=A-D: ");
		startTrial();
		O.output();
		endTrial();
		out.write("Output for O:\t"+endTime+"\n");
		print("P=C-D: ");
		startTrial();
		P.output();
		endTrial();
		out.write("Output for P:\t"+endTime+"\n");
		print("Q=D-C: ");
		startTrial();
		Q.output();
		endTrial();
		out.write("Output for Q:\t"+endTime+"\n");
		print("R=L-L: ");
		startTrial();
		R.output();
		endTrial();
		out.write("Output for R:\t"+endTime+"\n");
		print("S=P-O: ");
		startTrial();
		S.output();
		endTrial();
		out.write("Output for S:\t"+endTime+"\n");
		print("T=N-Q: ");
		startTrial();
		T.output();
		endTrial();
		out.write("Output for T:\t"+endTime+"\n");
		print("U=A*D: ");
		startTrial();
		U.output();
		endTrial();
		out.write("Output for U:\t"+endTime+"\n");
		print("V=B*C: ");
		startTrial();
		V.output();
		endTrial();
		out.write("Output for V:\t"+endTime+"\n");
		print("W=D*D: ");
		startTrial();
		W.output();
		endTrial();
		out.write("Output for W:\t"+endTime+"\n");
		print("X=O*I: ");
		startTrial();
		X.output();
		endTrial();
		out.write("Output for X:\t"+endTime+"\n");
		print("Y=J*P: ");
		startTrial();
		Y.output();
		endTrial();
		out.write("Output for Y:\t"+endTime+"\n");
		print("Z=M*N: ");
		startTrial();
		Z.output();
		endTrial();
		out.write("Output for Z:\t"+endTime+"\n\n");
		long end=System.nanoTime()-start;
		out.write("Total time(ns): "+end);
		out.flush();
		out.close();
	}
}