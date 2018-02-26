package com.koitt.book.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.koitt.book.model.Book;
import com.koitt.book.model.FileException;

@Service
public class FileServiceImpl implements FileService {

	private static final String UPLOAD_FOLDER = "/upload";

	@Override
	public String add(HttpServletRequest request, MultipartFile attachment) throws FileException {

		try {
			String path = request.getServletContext().getRealPath(UPLOAD_FOLDER);

			String originalName = attachment.getOriginalFilename();

			File directory = new File(path);
			if (!directory.exists()) {
				directory.mkdir();
			}

			if (attachment != null && !attachment.isEmpty()) {
				Integer idx = originalName.lastIndexOf(".");
				String name = originalName.substring(0, idx);
				String ext = originalName.substring(idx, originalName.length());

				String uploadFilename = 
						name + Long.toHexString(System.currentTimeMillis()) + ext;

				attachment.transferTo(new File(path, uploadFilename));
				uploadFilename = URLEncoder.encode(uploadFilename, "UTF-8");

				return uploadFilename;

			}
		} catch (IllegalStateException | IOException e) {

			System.out.println(e.getMessage());
			throw new FileException(e.getMessage());
		}
		return null;
	}


	@Override
	public void download(HttpServletRequest request, HttpServletResponse response, String filename)
			throws FileException {
		String directory = request.getServletContext().getRealPath(UPLOAD_FOLDER);
		File file = new File(directory, filename);
		
		FileInputStream fis = null;
		BufferedOutputStream bos = null;
		
		try {
			
			fis = new FileInputStream(file);
			filename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
			
			// 브라우저에 보낼 헤더정보
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename="
			+ filename + ";");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Content-Length", Integer.toString(fis.available()));
			
			// 캐싱 방지
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Expires", "-1");
			
			// 브라우저에 파일 전송
			bos = new BufferedOutputStream(response.getOutputStream());
			
			int length = 0;
			byte[] buff = new byte[1024];
			
			while ((length = fis.read(buff)) > 0) {
				bos.write(buff, 0, length);
			}
			
			bos.flush();
			
			bos.close();
			fis.close();
			
		} catch (Exception e) {
			throw new FileException(e.getMessage());
		}

	}

	@Override
	public void remove(HttpServletRequest request, String filename) throws FileException {
		String path = request.getServletContext().getRealPath(UPLOAD_FOLDER);
		if (filename != null && !filename.trim().isEmpty()) {
			try {
				filename = URLDecoder.decode(filename, "UTF-8");
			} catch (Exception e) {
				throw new FileException(e.getMessage());
			}
			
			File file = new File(path, filename);
			
			if (file.exists()) {
				file.delete();
			}
			
		}

	}

	@Override
	public String getImgPath(HttpServletRequest request, String filename) {
		String contextPath = request.getContextPath();
		
		if (filename != null && !filename.trim().isEmpty()) {
			int idx = filename.lastIndexOf(".");
			String ext =filename.substring(idx, filename.length());
			
			switch(ext) {
			case ".jpg":
			case ".jpeg":
			case ".png":
				return contextPath + UPLOAD_FOLDER + "/" + filename;
			}
		}
		return null;
	}


	@Override
	public String getUploadPath(HttpServletRequest request) {
		return request.getContextPath() + UPLOAD_FOLDER;
	}

}
