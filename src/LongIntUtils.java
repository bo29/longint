
// Called using LongIntUtils.method(...)

public class LongIntUtils {

	public static int overflow(int t) {
		if(digits(t)>8)
			return t/=Math.pow(10, 8);
		return 0;
	}

	public static int underflow(int t) {
		if(t==0)
			return 0;
		return t%=Math.pow(10, 8);
	}

	public static int upperHalf(int t) {
		if(t==0)
			return 0;
		return t/=Math.pow(10, 4);
	}

	public static int lowerHalf(int t) {
		if(t==0)
			return 0;
		return t%=Math.pow(10, 4);
	}

	public static int digits(int t) {
		if(t==0)
			return 1;
		else if(t<0)
			t*=-1;
		return (int)Math.log10(t)+1;
	}
}