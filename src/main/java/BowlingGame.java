public class BowlingGame {

    public int getBowlingScore (String bowlingCode){

		String context =bowlingCode;
		String source = null;
		String exrtal = null;
		String[] sourceAndExtral = context.split("\\|"+"\\|");

		if (sourceAndExtral.length<2) {
			source = sourceAndExtral[0];
			exrtal = "00";
		}else{
			source = sourceAndExtral[0];
			exrtal = sourceAndExtral[1];
		}

		/**
		 * 将X全部替换为10 
		 */
		int[] extralInt = new int[2];
		for (int i = 0; i < exrtal.length(); i++) {
			if (exrtal.charAt(i)=='X') {
				extralInt[i] = 10;
			}else
				extralInt[i] = Integer.parseInt(exrtal.charAt(i)+"");
		}

		/**
		 * 计算
		 */
		String[] fram = source.split("\\|");
		int[] temp = new int[]{extralInt[0],extralInt[1]};//length-1的情况

		int res = 0;

		for (int i = fram.length-1; i >=0; i--) {
			/**
			 * 将额外进行赋值
			 */
			if (i==fram.length-2) {
				temp[1] = temp[0];
				temp[0] = parse(fram[i+1]);
			}else if(i<fram.length-2){

				if (fram[i+1].contains("-")) {
					if (fram[i+1].startsWith("-")) {
						temp[0] = 0;
						temp[1]	= parse(fram[i+1]);				
					}else if (fram[i+1].endsWith("-")) {
						temp[0]	= parse(fram[i+1]);				
						temp[1] = 0;
					}
				}else if(fram[i+1].contains("/")){
					temp[0] = parse(fram[i+1]);
					temp[1] = 10-parse(fram[i+1]);
				}else{
					temp[0] = parse(fram[i+1]);
					temp[1] = parse(fram[i+2]);
				}
			}

			if (fram[i].contains("X")) {
				int sum = 10+temp[0]+temp[1];
				System.out.println("10+temp[0]+temp[1]"+10+"+"+temp[0]+"+"+temp[1]);


				res+=sum;
			}
			if (fram[i].contains("/")) {
				int sum = 10+temp[0];
				System.out.println("10+temp[0]"+10+"+"+temp[0]);

				res+=sum;
			}
			if (fram[i].contains("-")) {
				System.out.println("+"+fram[i]);
				res+=parse(fram[i]);
			}
			System.out.println("res==="+res);
		}
		return res;
	
	}

	public  int parse(String str){
		int res = 0;
		if (str.contains("X")) {
			str = str.replace("X", "");
			res = 10;
			return res;
		}
		if (str.contains("-")) {
			str = str.replace("-", "");
		}
		if (str.contains("/")) {
			str = str.replace("/", "");
		}
		return Integer.parseInt(str);
	}
}
