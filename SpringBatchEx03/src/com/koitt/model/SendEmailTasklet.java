package com.koitt.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class SendEmailTasklet implements Tasklet{

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private Configuration freeMarkerConfiguration;

	private void sendEmail() {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			
			private Random random = new Random();
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				// 10001 ~ 999999 범위에서 난수 생성
				String authCode = Integer.toString(this.random.nextInt(899999) + 100001);
				
				// FreeMarker 템플릿에 전달할 정보를 Map에 담기
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("authCode", authCode);
				
				// 이메일 메시지 작성
				Template template = freeMarkerConfiguration.getTemplate("fm-mail-template.html");
				String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
				
				// 이메일 메시지 작성 Helper 객체 생성
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
				helper.setFrom("was.redocto@gmail.com");	// 보내는사람 이메일 주소
				helper.setTo("jihyo_hello@naver.com");			// 받는사람 이메일 주소
				helper.setSubject("[KOITT] 이메일 인증 코드"); // 메일 제목
				helper.setText(text, true);					// 메일 내용, true는 HTML mail
				
				// 받는사람 이메일 값과 메일 내용 테스트위해 콘솔에 출력
				System.out.println("jihyo_hello@naver.com");
				System.out.println(text);
				
				/*
				 * 내장 리소스
				 * ( Freemarker 템플릿(HTML)에서 cid:img.jpg로 사용하면
				 * img/lights.jpg 경로에 해당하는 이미지를 사용하는 것이다.)
				 * 
				 */
				helper.addInline("image.png", new FileSystemResource("img/lights.png"));
				
				/*
				 * 첨부파일
				 */
				helper.addAttachment("document.csv", 
						new FileSystemResource("C:/sample/ex01/report.csv"));
				
			}
		};
		
		try {
			System.err.println("메일을 전송 중 입니다 ...");
			mailSender.send(preparator);
			System.err.println("메일 전송 완료 !!");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		}
	}

	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
