package com.mb.preDeal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Util {
	/**
	 * 该指标通过浏览次数、发帖数、帖子回复数和粉丝数四个网络参量来进行度量
	 * N = g2N2 + g3N3 + g4N4
	 * AI = 2 * sigmoid( (g1* N1/ Max(N1) + g5 * N / Max(N)) * 6.0) −1
	 * simgoid (x) =  1/(1+ e-x)
	 * 由于当x=6 的时候该函数已经非常接近1，而且随着x 继续增大该值变化
	 * 特别小，所以通过归一把x 控制在[0,6]，因为sigmoid 函数的值域为[0.5,1)，
	 * 2*simoid(x)-1 的值域为[0,1)
	 * 计算影响力
	 * g1取0.05，g2取0.25，g3取0.3，g4取0.4
	 * @param N1 总文章被浏览次数 N1
	 * @param N2 作者发表文章数 N2
	 * @param N2 文章被回复数 N3
	 * @param N4 粉丝数 N4
	 * @param g1 浏览次数权值
	 * @param g2 作者发表文章数权值
	 * @param g3 文章被回复数权值
	 * @param g4 粉丝数权值
	 * @param g5 N权值
	 * @return 活跃度
	 */
	public static double calfluence(double N1,double N2,double N3,double N4,double g1,double g2,double g3,
				double g4,double g5,List<Double> N1List,List<Double> NList){
		double N = calfluencePre(N2, N3, N4, g2, g3, g4);
//		System.out.println("N: " + N);
//		System.out.println("N1: " + N1);
		double maxN1 = getMaxCalData(N1List) ;
//		System.out.println("maxN1: " + maxN1);
		double maxN = getMaxCalData(NList) ;
//		System.out.println("maxN: " + maxN);
//		System.out.println("(g1*N1/maxN1 + g5*N/maxN): " + (g1*N1/maxN1 + g5*N/maxN));
		double ret = 2*sigmoid((g1*N1/maxN1 + g5*N/maxN) * 6.0) - 1 ;
		return ret;
	}
	/**
	 * 归一化函数
	 * @param x
	 * @return
	 */
	public static double sigmoid(double x) {
		double result =1/(1+Math.pow(Math.E,-x));
		return result;
	}


	public static double calfluencePre(double N2,double N3,double N4,double g2,double g3,double g4){
		return g2*N2 + g3*N3 + g4*N4;
	}
	
	/**
	 * 计算最大的数
	 * @param dataList
	 * @return
	 */
	public static double getMaxCalData(List<Double> dataList){
		double result = dataList.get(0);
		for (double d : dataList) {
			if(d>result) {
				result = d ;
			}
		}
		return result;
	}
	public static void writerTofile(Collection<String> resultRela,String path) {
		File file = new File(path);
		 FileWriter fw = null;
        BufferedWriter writer = null;
        try {
            fw = new FileWriter(file);
            writer = new BufferedWriter(fw);
            for(String s : resultRela){
                writer.write(s);
                writer.newLine();//换行
            }
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                writer.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
	//将M的每个元素都除于所在行的全部元素之和
	public static double[][] calcDiv(double[][] a) {
		System.out.println(a.length);
		for (int i = 0; i < a.length; i++) {
			double sum = calcSum(a[i]);
			for (int j = 0; j < a[0].length; j++) {
				 a[i][j] /= sum ;
			}
		}
		return a;
	}
	public static void writerTo(String path, List<AA> aaList, int k) {
		System.out.println("写入中。。。。");
	    File file = new File(path);
	    FileWriter fw = null;
        BufferedWriter writer = null;
        try {
            fw = new FileWriter(file);
            writer = new BufferedWriter(fw);
            for (int i = aaList.size() - 1; i >= aaList.size()-k; i--) {//前50
				writer.write(aaList.get(i).username + "\t" + aaList.get(i).influ);
				writer.newLine();
			}
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                writer.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("写入完毕。。。。");
		
	}
	public static void writerTo(String path, float[][] mT) {
		System.out.println("写入中。。。。");
	    File file = new File(path);
	    FileWriter fw = null;
        BufferedWriter writer = null;
        try {
            fw = new FileWriter(file);
            writer = new BufferedWriter(fw);
            for (int i = 0; i < mT.length; i++) {
//            	System.out.println(i);
                for (int j = 0; j < mT.length; j++)
              	    writer.write(mT[i][j]+"	");
                writer.newLine();
            }
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                writer.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("写入完毕。。。。");
	 }
	//计算数组的元素之和
	public static double calcSum(double[] ds) {
		double sum = 0.0;
		for (double d : ds) {
			sum += d ;
		}
		
		return sum==0 ? 1:sum;
	}
	/**
	 * 读取数据,边的二位数组
	 * @param path 数据的路径
	 * @return  二维数组 
	 * 			一行表示某人回复了某人
	 */
	public static Map<String, Integer> getData(String path){
		Map<String, Integer> maps = new HashMap<String, Integer>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String str= null;
			while((str=br.readLine())!=null){
				if(maps.get(str)==null){
					maps.put(str, 1);
				}else{
					int count = maps.get(str);
					maps.put(str, count+1);
				}
			}
//			for (String key : maps.keySet()) {
//				System.out.println(key + " : " + maps.get(key));
//			}
//			System.out.println("size: " + maps.size());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return maps;
	}
	public static String[][] getEdges(Map<String, Integer> mapData) {
		String[][] edges = new String[mapData.size()][3];
		int i = 0 ;
		for (String key : mapData.keySet()) {
			String []keys = key.split(",");
			edges[i][0] = keys[0];
			edges[i][1] = keys[1];
			edges[i][2] = mapData.get(key).toString();
//			System.out.println(edges[i][0] + " : " + edges[i][1] + ": " + edges[i][2]);
			i++;
		}
		System.out.println("边: " + edges.length);
		System.out.println("size2: " + edges[0].length);
		return edges;
	}
	public static String[] getNodes(Map<String, Integer> mapData) {
		Set<String> nodeSet = new HashSet<>();
		for (String key : mapData.keySet()) {
			String []keys = key.split(",");
			nodeSet.add(keys[0]);
			nodeSet.add(keys[1]);
		}
		String[] nodes = new String[nodeSet.size()];
		int i = 0 ;
		for (String node : nodeSet) {
			nodes[i] = node ;
			i++;
		}
		System.out.println("节点: " + nodes.length);
		return nodes;
	}
	public static double[][] readFrom(String path,int len) {
		double [][]a = new double[len][len];
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String str= null;
			int i = 0 ;
			while((str=br.readLine())!=null){
				System.out.println(i);
				int j = 0 ;
				for (String s : str.split("	")) {
					a[i][j] = Double.parseDouble(s);
					j ++;
				}
				i++;
			}
//			for (String key : maps.keySet()) {
//				System.out.println(key + " : " + maps.get(key));
//			}
//			System.out.println("size: " + maps.size());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("读取成功： " + a.length);
		return a;
	}
	public static double[][] readTransposeFrom(String path, int len) {
		double [][]a = new double[len][len];
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String str= null;
			int i = 0 ;
			while((str=br.readLine())!=null){
				System.out.println(i);
				int j = 0 ;
				for (String s : str.split("	")) {
					a[j][i] = Double.parseDouble(s);
					j ++;
				}
				i++;
			}
//			for (String key : maps.keySet()) {
//				System.out.println(key + " : " + maps.get(key));
//			}
//			System.out.println("size: " + maps.size());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("读取成功： " + a.length);
		return a;
	}
	public static double change(double dd) {
		DecimalFormat  df=new DecimalFormat("#.######");//保留六位小数
		return Double.parseDouble(df.format(dd));
	}
	public static float[][] getAFrom(String path, int len) {
		System.out.println("读取中。。");
		float [][]a = new float[len][len];
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String str= null;
			int i = 0 ;
			while((str=br.readLine())!=null){
				int j = 0 ;
				for (String s : str.split("	")) {
					float size = len ;
					a[i][j] = (float) (0.85*Float.parseFloat(s) + 0.15 / size) ;
					j ++;
				}
				i++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("读取成功： " + a.length);
		return a;
	}
	public static double[] calcAX(String path, int len, double[] x) {
		double []a = new double[len];
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String str= null;
			int i = 0 ;
			while((str=br.readLine())!=null){
//				System.out.println(i);
				int j = 0 ;
				double value = 0.0 ;
				for (String s : str.split("	")) {
					double size = len ;
					if(Double.parseDouble(s)==0.0){
						value += ((0.15 / size) * x[j]) ;
					}else{
						value += (Double.parseDouble(s) * 0.85 + (0.15 / size) * x[j]) ;
					}
					j ++;
				}
				a[i] = value;
				i++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("读取成功： " + a.length);
		return a;
	}
	public static float[] calcAX(float[][] A, float[] X) {
		float []a = new float[X.length];
		int size = X.length;
		for (int i = 0;i<size;i++) {
			float value = 0.0f ;
			for (int j = 0;j<size;j++) {
//				if(A[i][j]==0.0)
//					value += ((0.15 / size) * X[j]) ;
//				else
//					value += (A[i][j] *0.85 + (0.15 / size) * X[j]) ;
				value += A[i][j]* X[j] ;
			}
			a[i] = value;
		}
		return a;
	}
	public static void writerTo(String path, double[] aX) {
		System.out.println("写入中。。。。");
	    File file = new File(path);
	    FileWriter fw = null;
        BufferedWriter writer = null;
        try {
            fw = new FileWriter(file);
            writer = new BufferedWriter(fw);
            for (int i = 0; i < aX.length; i++) {
            	System.out.println(i);
          	    writer.write(aX[i]+"	");
            }
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                writer.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("写入完毕。。。。");
		
	}
	public static float[][] readTranspose2From(String path, int len) {
		float [][]a = new float[len][len];
		System.out.println("读取中。。");
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String str= null;
			int i = 0 ;
			while((str=br.readLine())!=null){
//				System.out.println(i);
				int j = 0 ;
				float value = 0.0f ;
				for (String s : str.split("	")) {
					value += Float.parseFloat(s);
				}
				for (String s : str.split("	")) {
					if(value == 0.0){
						a[j][i] = (Float.parseFloat(s));
					}else
						a[j][i] = (Float.parseFloat(s)) / value;
					j ++;
				}
				i++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("处理完毕： " + a.length);
		return a;
	}
	public static double[][] readTranspose2From(String path, int len, double[] x) {
		double [][]a = new double[len][len];
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String str= null;
			int i = 0 ;
			while((str=br.readLine())!=null){
				System.out.println(i);
				int j = 0 ;
				double value = 0.0 ;
				for (String s : str.split("	")) {
					value += Double.parseDouble(s);
				}
				for (String s : str.split("	")) {
					if(value == 0.0){
						a[j][i] = (Double.parseDouble(s)*x[j]);
					}else
						a[j][i] = (Double.parseDouble(s)*x[j]) / value;
					j ++;
				}
				i++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("读取成功： " + a.length);
		return a;
	}
	public static Map<String, Integer> getMapData(String path) {
		Map<String, Integer> maps = new HashMap<String, Integer>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String str= null;
			while((str=br.readLine())!=null){
				maps.put(str.split("\t")[0], Integer.parseInt(str.split("\t")[1]));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return maps;
	}
	public static String[] getTMNodes(String path) {
		int size = 0 ;
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String str= null;
			while((str=br.readLine())!=null){
				size++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("节点: " + size);
		String[] nodes = new String[size];
		int i = 0 ;
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String str= null;
			while((str=br.readLine())!=null){
				nodes[i] = str;
				i++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return nodes;
	}
	public static String[][] getTMEdges(String path) {
		
		
		int size = 0 ;
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String str= null;
			while((str=br.readLine())!=null){
				size++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[][] edges = new String[size][2];
		System.out.println("边: " + edges.length);
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String str= null;
			int i = 0 ;
			while((str=br.readLine())!=null){
				String []keys = str.split("\t");
				edges[i][0] = keys[0];
				edges[i][1] = keys[1];
				i++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return edges;
	}
	public static List<String> getFromSNS(String nodePath, String snsPath) {
		List<String> list = new ArrayList<String>();
		 try {
			 BufferedReader br = new BufferedReader(new FileReader(nodePath));
			 String str= null;
			 int i = 1 ;
			 while((str=br.readLine())!=null){// 530 166 277 408 32 347 23 49 708 508 8490 467 3689
				 list.add(str.split("\t")[0]);
			 }
		 } catch (FileNotFoundException e) {
			 e.printStackTrace();
		 } catch (IOException e) {
			 e.printStackTrace();
		 }
		 try {
			 BufferedReader br = new BufferedReader(new FileReader(snsPath));
			 String str= null;
			 while((str=br.readLine())!=null){// 530 166 277 408 32 347 23 49 708 508 8490 467 3689
				 String s = str.split("\t")[0];
				 for (int i=0;i<list.size();i++) {
					if(s.equals(list.get(i))){
						list.set(i, str);
					}
				}
			 }
		 } catch (FileNotFoundException e) {
			 e.printStackTrace();
		 } catch (IOException e) {
			 e.printStackTrace();
		 }
		 return list;
	}
	public static void writerresultTo(String path, List<String> resultList) {
		System.out.println("写入中。。。。");
	    File file = new File(path);
	    FileWriter fw = null;
        BufferedWriter writer = null;
        try {
            fw = new FileWriter(file);
            writer = new BufferedWriter(fw);
            for (String string : resultList) {
            	writer.write(string);
            	writer.newLine();
			}
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                writer.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("写入完毕。。。。");
		
		
	}
	public static void writerTo(String path, int[] m) {
		System.out.println("写入中。。。。");
	    File file = new File(path);
	    FileWriter fw = null;
        BufferedWriter writer = null;
        try {
            fw = new FileWriter(file,true);
            writer = new BufferedWriter(fw);
            for (int i = 0; i < m.length; i++) {
          	    writer.write(m[i]+"\t");
            }
            writer.newLine();
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                writer.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("写入完毕。。。。");
	}
	
	
}
