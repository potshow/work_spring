package practiceLotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Lotto {

	public static void main(String[] args) {
		Lotto lotto = new Lotto();
		Scanner input = new Scanner(System.in);
		/*	
		System.out.println("몇 주 째 로또번호를 알고싶으신가요? >> ");
		int thisWeekLotto = input.nextInt();

		System.out.println(thisWeekLotto + "째 주 로또 번호 입니다 :");
		System.out.println(lotto.getLottoNum());
		 */

		System.out.println(lotto.getLottoNum());

	}

	String getLottoNum() {

		List<Integer> lottoNum = new ArrayList<Integer>();

		for (int i = 1; i <= 45; i++) {
			lottoNum.add(i);
		}

		Random random = new Random();

		// 자동으로 정렬해주는 set 종류
		Set<Integer> getLotto = new TreeSet<Integer>();
		
		// 실제 set에 몇 개가 들어있는지 검사
		while (getLotto.size() < 6) {
			int Hi = random.nextInt(44) + 1;
			getLotto.add(Hi);
		}
		
		
		/*
		Collections.shuffle(lottoNum);

		int[] getLotto = new int[6]; // << 얻은 숫자를 새로운 배열에
		for (int i = 0; i < 6; i++) {
			getLotto[i] = lottoNum.get(i);
			System.out.println((i + 1) + "번째 숫자 : " + getLotto[i]);
		}


		 */
		
		return getLotto.toString();
	}

}
