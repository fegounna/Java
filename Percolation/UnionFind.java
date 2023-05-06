public class UnionFind {
	static int equiv[];
	static int height[];
	static void init(int len) {
		equiv = new int[len+2];
		height = new int[len+2];
		for(int i=0;i<len+2;i++) {
			equiv[i]=i;
			height[i]=0;
		}
		for(int i=0;i<Percolation.size;i++) {
			equiv[i]=Percolation.length;
			equiv[Percolation.length-Percolation.size+i]=Percolation.length+1;
		}
		height[Percolation.length]=height[Percolation.length+1]=1;
	}
	static int naiveFind(int x) {
		return equiv[x];
	}
	static int naiveUnion(int x,int y) {
		int r=naiveFind(x);
		for(int i=0;i<equiv.length;i++) {
			if(equiv[i]==r)	equiv[i]=naiveFind(y);
		}
		return naiveFind(y);
	}
	static int find(int x) {
		return logFind(x);
	}
	static int union(int x,int y) {
		return logUnion(x,y);
	}
	static int fastFind(int x) {
		while(equiv[x]!=x) {
			x=equiv[x];
		}
		return x;
	}
	static int fastUnion(int x,int y){
		int a=fastFind(y);
		equiv[fastFind(x)]=a;
		return a;
	}
	static int logUnion(int x,int y) {
		int a=find(x);
		int b=find(y);
		if(height[a]>height[b]) {
			equiv[b]=a;
			return b;
		}
		else if(height[a]<height[b]) {
			equiv[a]=b;
			return a;
		}
		else {
			equiv[a]=b;
			height[a]++;
			return a;
		}
	}
	static int logFind(int x) {
		while(equiv[x]!=x) {
			x=equiv[x];
			equiv[x]=equiv[x];
		}
		return x;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
