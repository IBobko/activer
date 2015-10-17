package ru.todo100.activer.controller;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.todo100.activer.strategy.PhotoStrategy;

/**
 * @author Igor Bobko
 */
@Controller
@RequestMapping("/test")
public class TestPageController
{
	@Autowired
	private PhotoStrategy photoStrategy;

	@RequestMapping("/")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		response.setContentType("image/jpeg");
		//String pathToWeb = getServletContext().getRealPath(File.separator);
		File f = new File("/home/igor/ptt6Qe80LVs111.jpg");
		BufferedImage bi = ImageIO.read(f);

		final BufferedImage resizedImage = new BufferedImage(bi.getWidth(), bi.getHeight(), bi.getType());
		final Graphics2D graphics = resizedImage.createGraphics();
		graphics.drawImage(bi, 0, 0, 200, 200, null);
		final Area area = new Area(new Rectangle(0, 0, bi.getWidth(), bi.getHeight()));
		area.subtract(new Area(new Ellipse2D.Double(75, 75, 60, 60)));
		graphics.fill(area);
		graphics.dispose();

		OutputStream out = response.getOutputStream();
		ImageIO.write(resizedImage, "jpg", out);
		out.close();

	}

	@RequestMapping("/i")
	public String i()
	{
		return "test/test";

	}

	@ResponseBody
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void post(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String image = request.getParameter("resizedImage");
//
//		String imageDataBytes = image.substring(image.indexOf(",") + 1);
//
//		InputStream stream = new ByteArrayInputStream(Base64.decode(imageDataBytes.getBytes()));
//
//		File f = new File("/home/igor/Desktop/1.png");
//		photoStrategy.copyImageToFile(stream, f);
//
//		stream.reset();
//
//		final OutputStream out = response.getOutputStream();
//		byte[] buf = new byte[1024];
//		int len;
//		while ((len = stream.read(buf)) > 0)
//		{
//			//out.write(buf, 0, len);
//		}
		response.getWriter().print(image);
	}

}
