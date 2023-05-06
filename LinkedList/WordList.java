class WordList {
  Node content;
  WordList() {
    content = null;
  }
  WordList(Node l){
	  content = l;
  }
  static WordList foobar = new WordList(new Node("foo", new Node("bar", new Node("baz", null))));
  int length() {
	  return Node.length(this.content);
  }
  public String toString() {
	  return Node.makeString(this.content);
  }
  void addFirst(String w) {
	  content=new Node(w,content);
  }
  void addLast(String w) {
	  if(content==null) {
		  content = new Node(w,null);
		  return;
	  }
	  Node.addLast(w,content);
  }
  String removeFirst() {
	  if(content==null)
		  return null;
	  String s=content.head;
	  content=content.next;
	  return s;
  }
  String removeLast() {
	  if(this.content==null)
		  return null;
	  if(this.content.next==null) {
		  String s=this.content.head;
		  this.content=null;
		  return s;
	  }
	  WordList l=new WordList(this.content.next);
	  String s=l.removeLast();
	  this.content = new Node(this.content.head,l.content);
	  return s;
	  
  }
  void insert(String s) {
	  content=Node.insert(s,content);
  }
  void insertionSort() {
	  content=Node.insertionSort(content);
  }
  WordList(String[] t){
	  for(int i=t.length-1;i>=0;i++)
		  addFirst(t[i]);
  }
  String[] toArray() {
	  int n=Node.length(content);
	  String[] t= new String[n];
	  int i=0;
	  for(Node c=content;c!=null;c=c.next) {
		  t[i]=c.head;
		  i++;
	  }
	  return t;
	  
  }
}
