public class Percolation {
	static final int size = 10;
	static int length = size*size ;
	static boolean[] grid = new boolean[length];
	static void init() {
		UnionFind.init(length);
		for(int i=0;i<length;i++)
			grid[i]=false;
	}
	static void print() {
		for(int i=0;i<length;i++) {
			if(grid[i])	System.out.print('*'); else System.out.print('-');
			if((i+1)%size==0) System.out.println();
		}
	}
	static void propagateUnion(int x) {
		if ((x+1)%size!=0&&grid[x+1])	UnionFind.union(x,x+1);
		if  (x%size!=0&&grid[x-1])	UnionFind.union(x,x-1);
		if(x+size<length&&grid[x+size])	UnionFind.union(x,x+size);
		if(x-size>=0&&grid[x-size])	UnionFind.union(x,x-size);
		for(int i=0;i<size;i++) {
			if(UnionFind.find(i)==UnionFind.find(x))	UnionFind.union(x,length);
			if(UnionFind.find(length-size+i)==UnionFind.find(x))	UnionFind.union(x,length+1);
		}
	}
	static int randomShadow() {
		int i = (int )(Math.random()*length);
		if(!grid[i]) {
		grid[i]=true;
		propagateUnion(i);
		return i;
		}
		return randomShadow();
	}
	static boolean detectPath(boolean[] seen,int n,boolean up) {
		if(up) {
			if(n<size&&n>=0)	return true;
			seen[n]=true;
			if ( (n+1)%size!=0&& !seen[n+1] && grid[n+1]&&detectPath(seen,n+1,up) )    return true;
			if(  n%size!=0&& !seen[n-1] &&grid[n-1]&&detectPath(seen,n-1,up) )   return true;
			if ( !seen[n-size]&&grid[n-size]&&detectPath(seen,n-size,up) ) return true;
			if ( n+size<length && !seen[n+size]&&grid[n+size]&&detectPath(seen,n+size,up) ) return true;
			return false;
		}
		else {
			if(n<length&&n>=length-size)	return true;
			seen[n]=true;
			if ( (n+1)%size!=0&& !seen[n+1] && grid[n+1]&&detectPath(seen,n+1,up) )    return true;
			if(  n%size!=0&& !seen[n-1] &&grid[n-1]&&detectPath(seen,n-1,up) )   return true;
			if ( !seen[n+size]&&grid[n+size]&&detectPath(seen,n+size,up) ) return true;
			if ( n-size>=0 && !seen[n-size]&&grid[n-size]&&detectPath(seen,n-size,up) ) return true;
			return false;
		}
	}
	static boolean isNaivePercolation(int n) {
		boolean[] seen=new boolean[length];
		for(int i=0;i<length;i++)
			seen[i]=false;
		boolean t1=detectPath(seen,n,true);
		for(int i=0;i<length;i++)
			seen[i]=false;
		return t1&&detectPath(seen,n,false);
	}
	static double percolation() {
		int n=0;
		double s=0;
		while( !isPercolation(n)) {
			n=randomShadow();
			s++;
		}
		return s/(double)(length);
	}
	static double monteCarlo(int n) {
		double e=0;
		for(int i=0;i<n;i++) {
			init();
			e+= percolation();
		}
		return e/(double)(n);
	}
	static boolean isFastPercolation(int n) {
		boolean up=false;
		for(int i=0;i<size;i++)
			if(UnionFind.find(n)==UnionFind.find(i)) {
				up=true;
				break;
			}
		if(up) {
			for(int i=length-size;i<length;i++)
				if(UnionFind.find(n)==UnionFind.find(i))
					return true;
		}
		return false;
	}
	static boolean isPercolation(int n) {
		return isFastPercolation(n);
	}
	static boolean isLogPercolation() {
		return UnionFind.find(length)== UnionFind.find(length+1);
	}

}
