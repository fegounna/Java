class Node {
  String head;
  Node next;

  Node(String head, Node next) {
    this.head = head;
    this.next = next;
  }
  static int lengthRec(Node l) {
	  if(l==null)
		  return 0;
	  return 1+lengthRec(l.next);
  }
  static int length(Node l) {
	  int r=0;
	  for (Node cur = l; cur != null; cur = cur.next) {
		  r++;
	  }
	  return r;
  }
  static String makeString(Node l) {
	  String s="[";
	  if(l==null)
		  return "[]";
	  for(Node cur = l; cur != null; cur=cur.next) {
		  if(cur.next ==null) {
			  s+=cur.head+"]";
		  }
		  else
			  s+=cur.head+", ";
	  }
	  return s;
  }
  static void addLast(String s,Node l) {
	  if(l.next==null) {
		  l.next=new Node(s,null);
	  }
	  else {
		  addLast(s,l.next);
	  }
  }
  static Node copy(Node the) {
	  if(the == null)
		  return null;
	  return new Node(the.head,copy(the.next));
  }
  static Node insert(String s,Node l) {
	  if(l==null)
		  return new Node(s,null);
	  if(l.next==null) {
		  if(s.compareTo(l.head)<=0) 
			  return new Node(s,l);
		  else 
			  return new Node(l.head,new Node(s,null));
	  }
	  else {
		  if(s.compareTo(l.head)<=0) 
			  return new Node(s,l);
		  return new Node(l.head,insert(s,l.next));
	  }
  }
  static Node insertionSort(Node l) {
	  if(l==null||l.next==null)
		  return l;
	  return insert(l.head,insertionSort(l.next));
  }
  public static void main(String[] args) {
	  Node foobar = new Node("foo", new Node("bar", new Node("baz", null)));
	  System.out.println(makeString(foobar));
  }
}	
