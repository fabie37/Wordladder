public final class LevenstienDistance {
	
	public static int getDistance(String s1, String s2) {
		int cost = 0;
		int[][] table = new int[s1.length()+1][s2.length()+1];
		
		for (int i=0; i<s1.length()+1; i++) {
			table[i][0] = i;
		}
		
		for (int j=0; j<s2.length()+1; j++) {
			table[0][j] = j;
		}
		
		for (int j=1; j<s2.length()+1; j++) {
			for (int i=1; i<s1.length()+1; i++) {
				if (s1.charAt(i-1) == s2.charAt(j-1)) {
					cost = 0;
				} else {
					cost = 1;
				}
				table[i][j] = Math.min(Math.min(table[i-1][j] + 1, table[i][j-1] + 1), table[i-1][j-1] + cost);
			}
		}
		return table[s1.length()][s2.length()];
	}
}
