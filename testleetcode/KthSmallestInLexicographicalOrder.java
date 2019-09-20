package testleetcode;

class KthSmallestInLexicographicalOrder {
    public int findKthNumber(int n, int k) {
        int result = 1;
        int lastSteps = k - 1; //默认走了一步，走到第一个数
        while (lastSteps > 0) {
            long left = result; //用 long 类型，防止 n 达到 2的32次方附近时，left * 10 会溢出
            long right = result + 1;
            int count = 0;
            while(left <= n) { //向下或向右探索，计算需要跨过的节点数
                count += Math.min(right, n+1) - left;
                left *= 10;
                right *= 10;
            }
            
            if (count > lastSteps) { //节点太多，跨不过去，只能向下走一层
                lastSteps --;
                result *= 10;
            } else { //节点不足，当前剩余步数足够走完全部节点，直接跨过这个节点以及他的子节点，向右走一步
                lastSteps -= count;
                result ++;
            }
        }
        return result;
    }

	public static void main(String[] args) {
		int res = new KthSmallestInLexicographicalOrder().findKthNumber(1000, 20);	
		System.out.println(res);
	}
}