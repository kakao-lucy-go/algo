
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class P_14890 {

	public static void main(String[] args) throws IOException {

		InputStream is = System.in;
		BufferedReader bf = new BufferedReader(new InputStreamReader(is));

		String[] temp = bf.readLine().split(" ");

		int n = Integer.parseInt(temp[0]);
		int l = Integer.parseInt(temp[1]);

		int[][] array = new int[n][n];

		for (int i = 0; i < array.length; i++) {
			temp = bf.readLine().split(" ");
			for (int j = 0; j < array.length; j++) {
				array[i][j] = Integer.parseInt(temp[j]);

			}
		}
		bf.close();
		int answer = 0;
		/*
		 * 1.경사로는 낮은 칸에 놓으며, L개의 연속된 칸에 경사로의 바닥이 모두 접해야 한다. 2.낮은 칸과 높은 칸의 높이 차이는 1이어야
		 * 한다. 3.경사로를 놓을 낮은 칸의 높이는 모두 같아야 하고, L개의 칸이 연속되어 있어야 한다.
		 * 
		 * 아래와 같은 경우에는 경사로를 놓을 수 없다. 1.경사로를 놓은 곳에 또 경사로를 놓는 경우 2낮은 칸과 높은 칸의 높이 차이가 1이 아닌
		 * 경우 3.낮은 지점의 칸의 높이가 모두 같지 않거나, L개가 연속되지 않은 경우 4.경사로를 놓다가 범위를 벗어나는 경우
		 */
		// 1. 열
		for (int i = 0; i < n; i++) {
			//System.out.println("i" + i);
			boolean[] isVisited = new boolean[n];
			f1: for (int j = 1; j < n; j++) {
				//System.out.println("j : " + j + " , " + array[i][j - 1] + " , " + array[i][j]);

				// 높이가 같으므로 건넌다.
				if (array[i][j] == array[i][j - 1]) {
				}
				// 높이가 1 넘게 차이가 나면 건너지 못한다. 그러므로 이 길은 망한 길이다. 다음 행으로.
				else if (Math.abs(array[i][j - 1] - array[i][j]) > 1) {
					break f1;
				}
				// 차이가 1이면 경사로를 두어야한다.
				else if (Math.abs(array[i][j - 1] - array[i][j]) == 1) {

					// 높이 차이가 1이 난다. 건널 수 있다. 건너자. 이제 경사로를 둘 것이다.
					// 오르막길
					if (array[i][j - 1] < array[i][j]) {
						// 낮은 방향으로 경사로를 두는데 경사로가 배열을 넘어서면 건널수없으므로 망한 길이니까 다음 행으로
						if (j - l < 0) {
							break f1;
						} else {
							// 경사로를 둘수있다! 그러면 경사로를 두는 방향의 높이가 다 같아야한다.
							for (int j2 = 0; j2 < l; j2++) {
								// 이미 어딘가에서 경사로를 둬서 겹치게 놓지 못하게 되면 이 길은 망함. 다음 길로
								if (isVisited[j - 1 - j2]) {
									break f1;
								}
								// 근데 경사로에 둘 곳의 높이가 하나라도 다르면 망한 길이다. 다음 행으로
								if (array[i][j - 1 - j2] != array[i][j - 1]) {
									break f1;
								}

							}

							// 경사로를 두게 되면 그곳들을 전부 true로 변경해준다.
							for (int j2 = 0; j2 < l; j2++) {
								isVisited[j - 1 - j2] = true;
							}
						}
					} else {
						// 내리막길
						// 내리막길로 경사로를 두는데 배열을 넘어가면 건널수없으므로 망한 길이니까 다음 행으로
						if (j + l > n) {
							break f1;
						} else {
							for (int j2 = 0; j2 < l; j2++) {
								// 이미 방문햇으면 경사로를 둘수없으므로 겹치니까 이 길은 망함. 다음길로
								if (isVisited[j + j2]) {
									break f1;
								}
								if (array[i][j + j2] != array[i][j]) {
									break f1;
								}
							}
							for (int j2 = 0; j2 < l; j2++) {
								isVisited[j + j2] = true;
							}
						}

					}

				}

				// break 안당하고 마지막까지 왔을 경우 길이라고 인정.
				if (j == n - 1) {
					//System.out.println("answer 추가 행 : " + i);
					answer++;
				}

			}

		}
		//System.out.println("열 answer : " +answer);
		//System.out.println("====================================");

		// 2. 행
		for (int i = 0; i < n; i++) {
			//System.out.println("i" + i);
			boolean[] isVisited = new boolean[n];
			f1: for (int j = 1; j < n; j++) {
				//System.out.println("j : " + j + " , " + array[j-1][i] + " , " + array[j][i]);

				// 높이가 같으므로 건넌다.
				if (array[j][i] == array[j-1][i]) {
				}
				// 높이가 1 넘게 차이가 나면 건너지 못한다. 그러므로 이 길은 망한 길이다. 다음 행으로.
				else if (Math.abs(array[j-1][i] - array[j][i]) > 1) {
					break f1;
				}
				// 차이가 1이면 경사로를 두어야한다.
				else if (Math.abs(array[j-1][i] - array[j][i]) == 1) {

					// 높이 차이가 1이 난다. 건널 수 있다. 건너자. 이제 경사로를 둘 것이다.
					// 오르막길
					if (array[j-1][i] < array[j][i]) {
						// 낮은 방향으로 경사로를 두는데 경사로가 배열을 넘어서면 건널수없으므로 망한 길이니까 다음 행으로
						if (j - l < 0) {
							break f1;
						} else {
							// 경사로를 둘수있다! 그러면 경사로를 두는 방향의 높이가 다 같아야한다.
							for (int j2 = 0; j2 < l; j2++) {
								// 이미 어딘가에서 경사로를 둬서 겹치게 놓지 못하게 되면 이 길은 망함. 다음 길로
								if (isVisited[j - 1 - j2]) {
									break f1;
								}
								// 근데 경사로에 둘 곳의 높이가 하나라도 다르면 망한 길이다. 다음 행으로
								if (array[j-1-j2][i] != array[j-1][i]) {
									break f1;
								}

							}

							// 경사로를 두게 되면 그곳들을 전부 true로 변경해준다.
							for (int j2 = 0; j2 < l; j2++) {
								isVisited[j - 1 - j2] = true;
							}
						}
					} else {
						// 내리막길
						// 내리막길로 경사로를 두는데 배열을 넘어가면 건널수없으므로 망한 길이니까 다음 행으로
						if (j + l > n) {
							break f1;
						} else {
							for (int j2 = 0; j2 < l; j2++) {
								// 이미 방문햇으면 경사로를 둘수없으므로 겹치니까 이 길은 망함. 다음길로
								if (isVisited[j + j2]) {
									break f1;
								}
								//System.out.println("j2 : " + j2 + " , " + array[j+j2][i] + " , " + array[j][i]);
								if (array[j+j2][i] != array[j][i]) {
									break f1;
								}
							}
							for (int j2 = 0; j2 < l; j2++) {
								isVisited[j + j2] = true;
							}
						}

					}

				}

				// break 안당하고 마지막까지 왔을 경우 길이라고 인정.
				if (j == n - 1) {
					//System.out.println("answer 추가 행 : " + i);
					answer++;
				}

			}

		}

		System.out.println(answer);

	}

}
