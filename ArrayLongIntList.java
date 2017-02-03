public class ArrayLongIntList implements LongIntList<ArrayEntry> {

	private int n;

	private int size;

	private boolean setListSize;

	private ArrayEntry [] entries;

	public ArrayLongIntList() {
		size=0;
		n=5;
		entries=new ArrayEntry[n];
		setListSize=false;
	}

	public void insertFirst(int value) {
		if(!setListSize){
			if(size==n){
				ArrayEntry[] temp=new ArrayEntry[n*2];
				for(int i=0; i<size; i++){
					entries[i].setIndex(entries[i].getIndex()+1);
					temp[i+1]=entries[i];
				}
				temp[0]=new ArrayEntry(value,0);
				n*=2;
				entries=temp;
			}
			else{
				if(size>0){
					for(int i=size; i>=0; i--){
						if(entries[i]!=null){
							entries[i].setIndex(entries[i].getIndex()+1);
							entries[i+1]=entries[i];
						}
					}
				}
				entries[0]=new ArrayEntry(value,0);
			}
		}
		else{
			ArrayEntry[] temp=new ArrayEntry[n];
			for(int i=0; i<size; i++){
				entries[i].setIndex(entries[i].getIndex()+1);
				temp[i+1]=entries[i];
			}
			temp[0]=new ArrayEntry(value,0);
			entries=temp;
		}
		size++;
	}

	public void insertLast(int value) {
		if(size==n && !setListSize){
			ArrayEntry[] temp=new ArrayEntry[n*2];
			for(int i=0; i<size; i++)
				temp[i]=entries[i];
			temp[size]=new ArrayEntry(value,size);
			n*=2;
			entries=temp;
		}
		else{
			entries[size]=new ArrayEntry(value,size);
		}
		size++;
	}

	public ArrayEntry first() {
		if(isEmpty())
			return null;
		return entries[0];
	}

	public ArrayEntry last() {
		if(isEmpty())
			return null;
		return entries[size-1];
	}

	public boolean isFirst(ArrayEntry p) {
		if(entries[0]==p)
			return true;
		return false;
	}

	public boolean isLast(ArrayEntry p) {
		if(entries[size-1]==p)
			return true;
		return false;
	}

	public ArrayEntry before(ArrayEntry p) {
		if(isEmpty())
			return null;
		if(entries[0]==p)
			throw new ArrayIndexOutOfBoundsException(-1);
		return entries[p.getIndex()-1];
	}

	public ArrayEntry after(ArrayEntry p) {
		if(isEmpty())
			return null;
		if(p.getIndex()==size-1)
			throw new ArrayIndexOutOfBoundsException(n+1);
		return entries[p.getIndex()+1];
	}

	public boolean isEmpty() {
		if(size==0)
			return true;
		return false;
	}

	public int size() {
		return size;
	}

	public void setListSize(int n){
		entries=new ArrayEntry[n];
		this.n=n;
		setListSize=true;
	}
}