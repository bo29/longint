public class SLLLongIntList implements LongIntList<SLLNode> {

	private SLLNode head;
	private SLLNode tail;
	private int size;

	public SLLLongIntList() {
		head=null;
		tail=null;
		size=0;
	}

	public void insertFirst(int value) {
		SLLNode insert=new SLLNode(value);
		if(head==null){
			head=insert;
			tail=insert;
		}
		else{
			insert.setNext(head);
			head=insert;
		}
		size++;
	}

	public void insertLast(int value) {
		SLLNode insert=new SLLNode(value);
		if(head==null){
			head=insert;
			tail=insert;
		}
		else{
			tail.setNext(insert);
			tail=tail.getNext();
		}
		size++;
	}

	public SLLNode first() {
		if(head==null)
			return null;
		return head;
	}

	public SLLNode last() {
		if(head==null)
			return null;
	    else if(head.getNext()==null)
			return head;
		return tail;
	}

	public boolean isFirst(SLLNode p) {
		if(head==p)
			return true;
		return false;
	}

	public boolean isLast(SLLNode p) {
		if(tail==p)
			return true;
		return false;
	}

	public SLLNode before(SLLNode p) {
		if(head==null)
			throw new NullPointerException("Empty list.");
		else if(head==p)
			throw new NullPointerException("Attempting to access node before head.");
		SLLNode temp=head;
		while(temp.getNext()!=null){
			if(temp.getNext()==p)
				return temp;
			temp=temp.getNext();
		}
		return null;
	}

	public SLLNode after(SLLNode p) {
		if(head==null)
			throw new NullPointerException("Empty list.");
		else if(tail==p)
			throw new NullPointerException("Attempting to access node after tail.");
		SLLNode temp=head;
		while(temp.getNext()!=null){
			if(temp==p)
				return temp.getNext();
			temp=temp.getNext();
		}
		return null;
	}

	public boolean isEmpty() {
		if(head==null)
			return true;
		return false;
	}

	public int size() {
		if(size<=0)
			return 0;
		return size;
	}

	public void setListSize(int size) {
		//do nothing!
	}
}