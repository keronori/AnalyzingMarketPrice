// Javaでスクレイピングを行う
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
 
public class Scraping {
	private int[] priceData;
	private int index = 0;
	private double mean;
	private double median;
	private int NUMBER_OF_WIDTH = 10;
	private int[] mode;
	private int modeElement = 0;
	

	public int getNUMBER_OF_WIDTH() {
		return NUMBER_OF_WIDTH;
	}
	public void analysis() {
		calculateMean();
		System.out.println("平均値:"+mean+"円");
		System.out.println("最頻値:"+calculateMode());
	}
	public int getModeElement() {
		return modeElement;
	}
	public int[] getMode() {
		return mode;
	}
	public String[] getClassRange() {
		String[] output = new String[NUMBER_OF_WIDTH];
		int range = (int)(mean/NUMBER_OF_WIDTH);
		for(int i=0; i<NUMBER_OF_WIDTH; i++) {
			/* output a frequency distribution table */
			if(i!=NUMBER_OF_WIDTH-1)output[i]=i*range+"~"+(i+1)*range+"円";
			else output[i] = i*range+"~円";
		}
		
		return output;
	}
	private String calculateMode() {
		//10の階級で考える
		mode = new int[NUMBER_OF_WIDTH];
		int range = (int)(mean/NUMBER_OF_WIDTH);
		
		for(int i=0; i<index; i++) {
			if(priceData[i]/range < NUMBER_OF_WIDTH) {
				mode[priceData[i]/range]++;
			} else {
				mode[NUMBER_OF_WIDTH-1]++;
			}
		}
		
		System.out.println("階級\t度数");
		int num=0;
		String output="";
		for(int i=0, max=0; i<mode.length; i++) {
			/* output a frequency distribution table */
			if(i!=NUMBER_OF_WIDTH-1)System.out.println(i*range+"~"+(i+1)*range+"円\t"+mode[i]);
			else System.out.println(i*range+"~円\t"+mode[i]);
			
			/* calculate mode */
			if(max < mode[i]) {
				num = i;
				modeElement = mode[i];
				if(i == NUMBER_OF_WIDTH-1) output = i*range+"~\t(度数: "+mode[i]+" --> 割合"+((double)mode[i]/index)*100+"%)";
				else output = i*range+"~"+(i+1)*range+"円\t(度数: "+mode[i]+" --> 割合"+((double)mode[i]/index)*100+"%)";
			}
		}
		
		return output;
	}
 
	public void run(String[] keywords, int target) {
		priceData = new int[target];
 
		try {
            // jsoupを使用して当ブログのトップページへアクセス
			String url = "https://www.mercari.com/jp/search/?keyword=";
			url += keywords[0];
			for(int i=1; i<keywords.length; i++) {
				url += "+"+keywords[i];
			}
			//System.out.println(url);
			Document doc = Jsoup.connect(url).get();
 
			/*  <div class="items-box-price font-5">¥350</div> */
			Elements elements = doc.select("div");
 
			int count = 0;
			for (Element element : elements) {
				if(element.text().contains("¥")) { // ¥を含む文字列
					if(element.text().length() < 1000 ) { // 空白を含む(いいねがある)
						if(++count % 3 == 0) {
							priceData[index] = Integer.parseInt(element.text().replace("¥", "").replace(",", ""));
							if(++index > target-1)break;
						}
					}
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void calculateMean() {
		double sum = 0;
		for(int price: priceData) {
			sum += price;
		}
		mean = sum/index;
	}

}