
// Called using LongIntUtils.method(...)

public class LongIntUtils {

	public static int overflow(int t) {
		if(digits(t)>8)
			return t/=Math.pow(10, 8);
		return 0;
	}//returns the most significant digits IF the number has more than 8 digits

	public static int underflow(int t) {
		if(t==0)
			return 0;
		return t%=Math.pow(10, 8);
	}//returns all digits if less than 8 digits or least significant 8 if number of digits is greater than 8

	public static int upperHalf(int t) {
		if(t==0)
			return 0;
		return t/=Math.pow(10, 4);//returns all digits except the 4 least significant
	}

	public static int lowerHalf(int t) {
		if(t==0)
			return 0;
		return t%=Math.pow(10, 4);
	}//returns 4 least signfiicant digits

	public static int digits(int t) {
		if(t==0)
			return 1;
		else if(t<0)
			t*=-1;
		return (int)Math.log10(t)+1;
	}//returns a value's number of digits
}