package www.exam.com;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class FindRandomNums {
	public HashSet<Integer> returnRandomNums(List<List<Integer>> nums) {
		Random random = new Random();
		//从50个List里随机抽出
		HashSet<Integer> res = new HashSet<>(100000);
		while(true) {
			int n = random.nextInt(50);
			List<Integer> list = nums.get(n);
			int j=0;
			while(j<=10000) {
				int i = random.nextInt(10000);
				res.add(list.get(i));
				if(res.size()>=50000) {
					return res;
				}
				j++;
			}
		}
	}
	/**
	 * 将HashSet中的数据写入到文件中
	 * @param set
	 */
	public void numsToFile(HashSet<Integer> set) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(new File("F:\\Workspace\\FindRandomNums\\src\\main\\resources\\nums_50.txt"),true);
			bw = new BufferedWriter(fw);
			int size = 1;
			for (Integer in : set) {
				String.valueOf(in);
				bw.write(String.valueOf(in));
				bw.write(',');
				if(size%1000==0) {
					bw.newLine();
				}
				size++;
			}
			
			bw.flush();
			bw.close();
			fw.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//从文件中取出50万个数
	public List<List<Integer>> gainSourceNums(File file){
		FileReader fis = null;
		List<List<Integer>> nums=null;
		try {
			//向文件中读取内容
			fis = new FileReader(file);
		    
			BufferedReader br = new BufferedReader(fis);
			String txt = null;
			nums = new ArrayList<>(50);
			List<Integer> num = new ArrayList<>(10000);
//			int[][] nums = new int[50][10000];
			//从文件中每1行获取1000个数字，10行就是1万
			while((txt=br.readLine())!=null) {
				char[] c = txt.toCharArray();
				for (char d : c) {
					if(d==',') {
						continue;
					}else {
						num.add(d-'0');
					}
				}
				if(num.size()==10000) {
					nums.add(new ArrayList<>(num));
					num.clear();
				}
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return nums;
	}
	
	public static void main(String[] args) {
		FindRandomNums nums = new FindRandomNums();
		//nums.gainSourceNums(new File("F:\\Workspace\\FindRandomNums\\src\\main\\resources/nums_50.txt"));
		List<List<Integer>> num = new ArrayList<>();
		List<Integer> n = new ArrayList<>(); 
		int size = 0;
//		System.out.println("temp:");
		for(int i=0;i<50;i++) {
			for(int j=0;j<10000;j++) {
				int temp = i*10+j;
				n.add(temp);
				//System.out.print(temp+",");
//				if(j%1000==0) {
//					System.out.println();
//				}
			}
			size+=n.size();
			num.add(new ArrayList<>(n));
			n.clear();
		}
		HashSet<Integer> set = nums.returnRandomNums(num);
		nums.numsToFile(set);
		
		
	}
}
