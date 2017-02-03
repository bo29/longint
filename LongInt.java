@SuppressWarnings({ "rawtypes", "unchecked" })
public class LongInt {

	// DO NOT CHANGE OR REMOVE THIS LINE (UNTIL STEP 3)
	private final LongIntList list = new SLLLongIntList();

	// USE THIS LINE IN STEP 3
	//private final LongIntList list = new ArrayLongIntList();

	private boolean isNegative;
	private boolean signChanged;
	private final String s;
	
	public LongInt() {
		this(null);
		signChanged=false;
		isNegative=false;
	}

	public LongInt(String s) {
		isNegative = false;
		signChanged=false;
		this.s=s;
		if(s!=null){
			if(s.length()<=8){//mainly for single digit LongInts but also handles any that are less than 8 digits
				if(s.charAt(0)=='-')
					list.insertLast(Integer.parseInt(s.substring(1, s.length())));
				else
					list.insertLast(Integer.parseInt(s.substring(0, s.length())));
			}
			else{
				int remainder=s.length()%8;
				if(remainder==1 && s.charAt(0)=='-'){//check for negative numbers that have a number of digits divisible by 8
					list.insertLast(Integer.parseInt(s.substring(1, 9)));
					remainder+=8;
				}
				else if(s.charAt(0)=='-')//for all other negative numbers
					list.insertLast(Integer.parseInt(s.substring(1, remainder)));
				else if (remainder>0)//positive numbers
					list.insertLast(Integer.parseInt(s.substring(0, remainder)));
				for(int i=remainder, j=remainder+8; j<=s.length(); i+=8, j+=8)//fills in the rest of the list
					list.insertFirst(Integer.parseInt(s.substring(i,j)));
			}
			setSign();
		}
	}
	
	private final LongIntList getList(){
		return list;//returns the list to compare it later
	}

	public void output() {//prints entire number with no spaces
		Position p=list.last();
		if(getSign()==true)
			System.out.print('-');
		while(p!=list.first()){
			if(p.getValue()==0){
				for(int i=0; i<7; i++)
					System.out.print('0');
			}
			else if(LongIntUtils.digits(p.getValue())<8 && !list.isLast(p)){
				for(int i=0; i<8-LongIntUtils.digits(p.getValue()); i++)
					System.out.print('0');
			}
			System.out.print(p.getValue());
			p=list.before(p);
		}
		if(LongIntUtils.digits(p.getValue())<8 && !list.isLast(p))
			for(int i=0; i<8-LongIntUtils.digits(p.getValue()); i++)
				System.out.print('0');
		System.out.println(p.getValue());
	}

	public void printNodes(){//prints node values separated by arrows
		Position p=list.last();
		while(p!=list.first()){
			System.out.print(p.getValue()+" -> ");
			p=list.before(p);
		}
		System.out.println(p.getValue());
	}

	private void setSign(){
		if(list.size()==1 && !list.isEmpty()){//handles single digit LongInts
			if(Integer.parseInt(s)<0)
				isNegative=true;
			else
				isNegative=false;
		}
		else if(Integer.parseInt(s.substring(0,2))>0)
			isNegative=false;//since isNegative is set to false this returns false
		else
			isNegative=true;
	}

	private void setSign(boolean isNegative){
		this.isNegative=isNegative;
	}

	private void setSignChanged(boolean signChanged){
		this.signChanged=signChanged;
	}

	public boolean getSign() {
		return isNegative;
	}

	public int getDigitCount() {
		Position p=list.first();
		int count=0;
		while(!list.isLast(p)){
			count+=LongIntUtils.digits(p.getValue());
			if(LongIntUtils.digits(p.getValue())<8)
				count+=8-LongIntUtils.digits(p.getValue());//compensates for leading 0s
			p=list.after(p);
		}
		return count+=LongIntUtils.digits(p.getValue());
	}

	public boolean equalTo(LongInt i) {
		if(getSign()!=i.getSign() || getDigitCount()!=i.getDigitCount())
			return false;//different digit counts or different signs
		LongIntList list2=i.getList();
		Position p1=list.first();
		Position p2=list2.first();
		while(!list.isLast(p1)){//compares each position
			if(p1.getValue()!=p2.getValue())
				return false;
			p1=list.after(p1);
			p2=list2.after(p2);
		}
		return p1.getValue()==p2.getValue();
	}

	public boolean lessThan(LongInt i) {
		if(equalTo(i)==true)
			return false;
		if(getSign()!=i.getSign()){
			if(getSign()==true)
				return true;
			return false;
		}
		else if(getDigitCount()<i.getDigitCount()){
			if(getSign()==true)
				return false;//for negative numbers
			return true;
		}
		else if(getDigitCount()>i.getDigitCount()){
			if(getSign()==true)//for negative numbers
				return true;
			return false;
		}
		else{
			LongIntList list2=i.getList();
			Position p1=list.last();
			Position p2=list2.last();
			while(p1.getValue()==p2.getValue() && p1!=list.first()){
				p1=list.before(p1);//skips nodes with equal values
				p2=list2.before(p2);
			}
			if(p1.getValue()<p2.getValue()){
				if(getSign()==true)//accounts for negative numbers with one digit
					return false;
				return true;
			}
			else{
				if(getSign()==true)//accounts for negative numbers with one digit
					return true;
				return false;
			}
		}
	}

	public boolean greaterThan(LongInt i) {
		if(equalTo(i)==true)
			return false;
		if(getSign()!=i.getSign()){
			if(getSign()==true)
				return false;
			return true;
		}
		else if(getDigitCount()>i.getDigitCount()){
			if(getSign()==true)
				return false;//for negative numbers
			return true;
		}
		else if(getDigitCount()<i.getDigitCount()){
			if(getSign()==true)
				return true;//for negative numbers
			return false;
		}
		else{
			LongIntList list2=i.getList();
			Position p1=list.last();
			Position p2=list2.last();
			while(p1.getValue()==p2.getValue() && p1!=list.first()){
				p1=list.before(p1);//skips nodes with equal values
				p2=list2.before(p2);
			}
			if(p1.getValue()>p2.getValue()){
				if(getSign()==true)//accounts for negative numbers with one digit
					return false;
				return true;
			}
			else{
				if(getSign()==true)//accounts for negative numbers with one digit
					return true;
				return false;
			}
		}
	}

	public LongInt add(LongInt i) {
		if(list.isEmpty() && i.list.isEmpty())
			throw new NullPointerException("LongInts have not been initialized");
		else if(list.isEmpty())
			return i;
		else if(i.getList().isEmpty())
			return this;
		else if(getSign()==true && i.getSign()==false){
			setSign(false);
			setSignChanged(true);
			return i.subtract(this);
		}
		else if(getSign()==false && i.getSign()==true){
			i.setSign(false);
			i.setSignChanged(true);
			return subtract(i);
		}
		LongInt j=new LongInt();
		LongIntList jList=j.getList();
		LongIntList list2=i.getList();
		jList.setListSize(Math.max(list.size()+1, list2.size()+1));
		if(getSign()==true && i.getSign()==true)
			j.setSign(true);
		else
			j.setSign(false);
		Position p1=list.first();
		Position p2=list2.first();
		int minSize=Math.min(list.size(), list2.size());
		int k=0;
		int overflow=0;
		while(k<minSize){
			int toAdd=p1.getValue()+p2.getValue()+overflow;
			int underflow=LongIntUtils.underflow(toAdd);
			overflow=LongIntUtils.overflow(toAdd);
			jList.insertLast(underflow);
			k++;
			if(k==minSize)
				break;
			p1=list.after(p1);
			p2=list2.after(p2);
		}//the next part brings down leftmost nodes in larger list if applicable
		if(list.size()==list2.size()){
			if(LongIntUtils.overflow(p1.getValue()+p2.getValue())>0)
				jList.insertLast(LongIntUtils.overflow(p1.getValue()+p2.getValue()));
		}
		else if(minSize==list.size()){
			p2=list2.after(p2);
			while(p2!=list2.last()){
				if(overflow>0){
					jList.insertLast(p2.getValue()+overflow);
					overflow=0;
				}
				else
					jList.insertLast(p2.getValue());
				p2=list2.after(p2);
			}
			if(overflow>0)
				jList.insertLast(p2.getValue()+overflow);
			else
				jList.insertLast(p2.getValue());
		}
		else if(minSize==list2.size()){
			p1=list.after(p1);
			while(p1!=list.last()){
				if(overflow>0){
					jList.insertLast(p1.getValue()+overflow);
					overflow=0;
				}
				else
					jList.insertLast(p1.getValue());
				p1=list.after(p1);
			}
			if(overflow>0)
				jList.insertLast(p1.getValue()+overflow);
			else
				jList.insertLast(p1.getValue());
		}
		if(signChanged){//resets to default sign values
			setSign(!getSign());
			setSignChanged(false);
		}
		if(i.signChanged){
			i.setSign(!i.getSign());
			i.setSignChanged(false);
		}
		return j;
	}

	public LongInt subtract(LongInt i) {
		if(list.isEmpty() && i.list.isEmpty())
			throw new NullPointerException("LongInts have not been initialized");
		else if(list.isEmpty()){
			return i;
		}
		else if(i.getList().isEmpty()){
			return this;
		}
		else if(equalTo(i)){
			LongInt j=new LongInt();
			LongIntList jList=j.getList();
			jList.setListSize(1);
			jList.insertFirst(0);
			return j;
		}
		else if(getSign()==false && i.getSign()==true){
			i.setSign(false);
			i.setSignChanged(true);
			return add(i);
		}
		else if(getSign()==true && i.getSign()==true){
			i.setSign(false);
			i.setSignChanged(true);
			setSign(false);
			setSignChanged(true);
			return i.subtract(this);
		}
		else if(getSign()==true && i.getSign()==false){
			LongInt j=new LongInt();//pretty much the same as the add function
			LongIntList jList=j.getList();
			LongIntList list2=i.getList();
			jList.setListSize(Math.max(list.size()+1, list2.size()+1));
			j.setSign(true);
			setSign(false);
			setSignChanged(true);
			Position p1=list.first();
			Position p2=list2.first();
			int minSize=Math.min(list.size(), list2.size());
			int k=0;
			int overflow=0;
			while(k<minSize){
				int toAdd=p1.getValue()+p2.getValue()+overflow;
				overflow=LongIntUtils.overflow(toAdd);
				int underflow=LongIntUtils.underflow(toAdd);
				jList.insertLast(underflow);
				k++;
				if(k==minSize)
					break;
				p1=list.after(p1);
				p2=list2.after(p2);
			}//the next part brings down leftmost nodes in larger list if applicable
			if(list.size()==list2.size()){
				if(LongIntUtils.overflow(p1.getValue()+p2.getValue())>0)
					jList.insertLast(LongIntUtils.overflow(p1.getValue()+p2.getValue()));
			}
			else if(minSize==list.size()){
				p2=list2.after(p2);
				while(p2!=list2.last()){
					if(overflow>0){
						jList.insertLast(p2.getValue()+overflow);
						overflow=0;
					}
					else
						jList.insertLast(p2.getValue());
					p2=list2.after(p2);
				}
				if(overflow>0)
					jList.insertLast(p2.getValue()+overflow);
				else
					jList.insertLast(p2.getValue());
			}
			else if(minSize==list2.size()){
				p1=list.after(p1);
				while(p1!=list.last()){
					if(overflow>0){
						jList.insertLast(p1.getValue()+overflow);
						overflow=0;
					}
					else
						jList.insertLast(p1.getValue());
					p1=list.after(p1);
				}
				if(overflow>0)
					jList.insertLast(p1.getValue()+overflow);
				else
					jList.insertLast(p1.getValue());
			}
			if(signChanged){//resets to default sign values
				setSign(!getSign());
				setSignChanged(false);
			}
			if(i.signChanged){
				i.setSign(!i.getSign());
				i.setSignChanged(false);
			}
			return j;
		}
		LongInt j=new LongInt();
		LongIntList jList=j.getList();
		LongIntList list2=i.getList();
		jList.setListSize(Math.max(list.size(), list2.size()));
		if(getSign()==i.getSign() && getSign()==false){
			if(greaterThan(i))
				j.setSign(false);
			else if(lessThan(i))
				j.setSign(true);
			int minSize=Math.min(list.size(), list2.size());
			int k=0;
			Position p1;
			Position p2;
			if(list.size()==list2.size()){
				p1=list.last();
				p2=list2.last();
				while(p1.getValue()==p2.getValue()){
					minSize--;
					p1=list.before(p1);
					p2=list2.before(p2);
				}
			}
			p1=list.first();
			p2=list2.first();
			int carry=0;
			while(k<minSize){
				int toSub;
				if(p1.getValue()<p2.getValue()){
					if(j.getSign()==false)
						toSub=100000000-Math.abs(p1.getValue()-p2.getValue())-carry;
					else
						toSub=Math.abs(p1.getValue()-p2.getValue())-carry;
				}
				else{
					if(j.getSign()==false)
						toSub=Math.abs(p1.getValue()-p2.getValue())-carry;
					else{
						toSub=100000000-Math.abs(p1.getValue()-p2.getValue())-carry;
						if(toSub==100000000)
							toSub=0;
					}
				}
				carry=p1.getValue()-p2.getValue();
				if(carry==0 && (p1==list.last() || p2==list2.last()))
					break;
				if(carry<0){
					if(j.getSign()==false)
						carry=1;
					else
						carry=0;
				}
				else if(carry>0){
					if(j.getSign()==false)
						carry=0;
					else
						carry=1;
				}
				if(list.size()==list2.size() && (p1==list.last() || k==minSize-1)){
					if(toSub==0)
						break;
				}
				jList.insertLast(toSub);
				k++;
				if(k==minSize)
					break;
				p1=list.after(p1);
				p2=list2.after(p2);
			}
			if(list.size()==list2.size()){
				if(Math.abs(LongIntUtils.overflow(p1.getValue()-p2.getValue()))>0)
					jList.insertLast(Math.abs(LongIntUtils.overflow(p1.getValue()-p2.getValue()-carry)));
			}
			else if(minSize==list.size()){
				int overflow=0;
				p2=list2.after(p2);
				while(p2!=list2.last()){
					if(p2.getValue()-carry<0){
						jList.insertLast(100000000+(p2.getValue()-carry-overflow));
						overflow=Math.abs(p2.getValue()-carry);
					}
					else{
						jList.insertLast(p2.getValue()-carry-overflow);
						overflow=0;
					}
					p2=list2.after(p2);
					carry=0;
				}
				jList.insertLast(p2.getValue()-carry-overflow);
			}
			else if(minSize==list2.size()){
				int overflow=0;
				p1=list.after(p1);
				while(p1!=list.last()){
					if(p1.getValue()-carry<0){
						jList.insertLast(100000000+(p1.getValue()-carry-overflow));
						overflow=Math.abs(p1.getValue()-carry);
					}
					else{
						jList.insertLast(p1.getValue()-carry-overflow);
						overflow=0;
					}
					p1=list.after(p1);
					carry=0;
				}
				jList.insertLast(p1.getValue()-carry-overflow);
			}
		}
		if(signChanged==true){//resets to default sign values
			setSign(!getSign());
			setSignChanged(false);
		}
		if(i.signChanged==true){
			i.setSign(!i.getSign());
			i.setSignChanged(false);
		}
		return j;
	}	

	public LongInt multiply(LongInt i) {
		if(list.isEmpty() && i.list.isEmpty())
			throw new NullPointerException("LongInts have not been initialized");
		else if(list.isEmpty())
			return i;
		else if(i.getList().isEmpty())
			return this;
		LongInt j=new LongInt();
		LongIntList jList=j.getList();
		LongIntList list2=i.getList();
		if((getSign()==false && i.getSign()==false) || (getSign()==true && i.getSign()==true))
			j.setSign(false);
		else if((getSign()==true && i.getSign()==false) || (getSign()==false && i.getSign()==true))
			j.setSign(true);
		if(list.size()==1){
			if(list.first().getValue()==0){
				jList.insertFirst(0);
				jList.setListSize(1);
				j.setSign(false);
				return j;
			}
			else if(list.first().getValue()==1){
				boolean jSign=j.getSign();
				j=i;
				j.setSign(jSign);
				j.getList().setListSize(i.getList().size());
				return j;
			}
		}
		if(list2.size()==1){
			if(list2.first().getValue()==0){
				jList.insertFirst(0);
				j.setSign(false);
				jList.setListSize(1);
				return j;
			}
			else if(list2.first().getValue()==1){
				boolean jSign=j.getSign();
				j=this;
				j.setSign(jSign);
				j.getList().setListSize(list.size());
				return j;
			}
		}
		jList.setListSize(list.size()+list2.size());
		Position p1=list.first();
		Position p2=list2.first();
		int minSize=Math.min(list.size(), list2.size());
		int maxSize=Math.max(list.size(), list2.size());
		int z1, z2, z3, v1, v2;
		int columns=list.size()+list2.size();
		int rows=list.size()*list2.size();
		int count=columns;
		int k=maxSize-1;//counter for list nodes
		int m=0;
		int n=columns-1;
		LongInt[][] arrays=new LongInt[rows][columns];
		LongInt V1;
		LongInt V2;
		while(m<rows){
			z1=LongIntUtils.upperHalf(p1.getValue())*LongIntUtils.upperHalf(p2.getValue());
			z3=LongIntUtils.lowerHalf(p1.getValue())*LongIntUtils.lowerHalf(p2.getValue());
			z2=(LongIntUtils.upperHalf(p1.getValue())+LongIntUtils.lowerHalf(p1.getValue()))*
					(LongIntUtils.upperHalf(p2.getValue())+LongIntUtils.lowerHalf(p2.getValue()))-z1-z3;
			v1=z1+LongIntUtils.upperHalf(z2);
			V1=new LongInt();
			V1.getList().setListSize(1);
			V1.getList().insertLast(v1);
			v2=(int)(z3+LongIntUtils.lowerHalf(z2)*Math.pow(10, 4));
			V2=new LongInt();
			V2.getList().setListSize(1);
			V2.getList().insertLast(v2);
			arrays[m][n]=V2;
			arrays[m][n-1]=V1;
			m++; n--;
			if(k>0){
				if(list2.size()==minSize)
					p1=list.after(p1);
				else if(list.size()==minSize)
					p2=list2.after(p2);
				k--; 
			}
			else if(k==0){
				if(list2.size()==minSize && p2!=list2.last()){
					count--;
					n=count-1;
					p2=list2.after(p2);
					p1=list.first();
					k=maxSize-1;
				}
				else if(list.size()==minSize && p1!=list.last()){
					count--;
					n=count-1;
					p1=list.after(p1);
					p2=list2.first();
					k=maxSize-1;
				}
			}
		}
		final LongInt zero=new LongInt();
		LongIntList zList=zero.getList();
		zList.setListSize(1);
		zList.insertLast(0);
		LongInt toInsert=new LongInt();
		LongInt overflow=zero;
		for(n=columns-1; n>=0; n--){
			toInsert=zero;
			for(m=0; m<rows; m++){
				if(arrays[m][n]==null || arrays[m][n].equalTo(zero))
					continue;//arrays[m][n]=zero;//changing this line to continue sped up the program A LOT
				toInsert=toInsert.add(arrays[m][n]);//after all, there's no reason to do this if you're adding zero, so skip it
			}
			if(!overflow.equalTo(zero)){
				toInsert=toInsert.add(overflow);
			}
			if(n==0 && toInsert.getList().first().getValue()==0)
				continue;//don't insert a zero node on the last iteration
			jList.insertLast(toInsert.getList().first().getValue());
			if(toInsert.getDigitCount()>8){
				overflow=new LongInt();
				overflow.getList().setListSize(1);
				overflow.getList().insertLast(toInsert.getList().last().getValue());
			}
			else
				overflow=zero;
		}
		return j;
	}

	public LongInt power(int p) {
		if(list.isEmpty())
			throw new NullPointerException("LongInt has not been initialized");
		if(p<=0){
			LongInt i=new LongInt();
			LongIntList list2=i.getList();
			list2.insertLast(1);
			return i;
		}
		else if(p==1)
			return this;
		else if(p%2==0)
			return multiply(this).power(p/2);
		return multiply(multiply(this).power((p-1)/2));
	}
}