package com.multi.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j;

@RestController
@Log4j
public class NaverSummaryController {

	private String clientId="o7f06gidj5";
	private String clientSecret="xdGveejrHqBZYlACGrOgHQKXn1xawggpIbBzRXza";
	private String apiUrl="https://naveropenapi.apigw.ntruss.com/text-summary/v1/summarize";
	
	@GetMapping("/summaryForm")
	public ModelAndView summaryForm() {
		
		ModelAndView mv=new ModelAndView("naver/clova_summary");//뷰네임 
		return mv;
		//"/WEB-INF/views/naver/clova_summary.jsp"
	}//----------
	/*pom.xml에 필요한 라이브러리 
	 * https://mvnrepository.com/artifact/org.json/json -->
        <dependency>
            <groupId>org.json</groupId>
            <artifactId>json</artifactId>
            <version>20210307</version>
        </dependency>
       */
	
	@PostMapping(value="/summaryEnd", produces= {"text/plain; charset=UTF-8"})
	public String summary(String title, String content) throws Exception{
		System.out.println("title: "+title+",content: "+content);
		String result="test";
		URL url=new URL(apiUrl);
		URLConnection con=url.openConnection();
		HttpURLConnection httpCon=(HttpURLConnection)con;//http통신을 할 수 있다 
		
		httpCon.setRequestMethod("POST");//post요청방식 
		httpCon.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
		httpCon.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
		httpCon.setRequestProperty("Content-Type", "application/json");
		//네이버에 전송할 데이터를 json형식으로 만들자 
		JSONObject root=new JSONObject();
		JSONObject doc=new JSONObject();
		JSONObject option=new JSONObject();
		
		//document 에 들어갈 데이터 
		doc.put("title",title);
		doc.put("content",content);
		
		//option 에 들어갈 데이터 
		option.put("language", "ko");//ko - korean, ja - japanese
		option.put("model", "news"); //news, general 
		option.put("tone", "2"); //2: 정중체 
		option.put("summaryCount", "3");
		
		root.put("document",doc);
		root.put("option",option);
		
		String jsonStr=root.toString();
		System.out.println("jsonStr: "+jsonStr);
		
		httpCon.setUseCaches(false);
		httpCon.setDoOutput(true);
		httpCon.setDoInput(true);
		
		//네이버 서버로 요청을 보내자 
		OutputStream os=httpCon.getOutputStream();
		PrintWriter pw=new PrintWriter(new OutputStreamWriter(os,"UTF-8"),true);
		pw.println(jsonStr);//데이터 전송 
		pw.flush();
		pw.close();
		
		int resCode=httpCon.getResponseCode();//응답코드 
		System.out.println("resCode: "+resCode);
		BufferedReader br=null;
		if(resCode==200) {
			br=new BufferedReader(new InputStreamReader(httpCon.getInputStream(),"UTF-8"));
		}else {
			br=new BufferedReader(new InputStreamReader(httpCon.getErrorStream(),"UTF-8"));

		}
		String line="";
		StringBuilder buf=new StringBuilder();
		while((line=br.readLine())!=null) {
			buf.append(line);
		}
		br.close();
		System.out.println("response: "+buf.toString());
		JSONObject json=new JSONObject(buf.toString());
		result=json.getString("summary");
		return result;
	}
	
	
}
