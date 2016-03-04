package ru.todo100.activer.controller;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
//import java.util.Base64;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;

import ru.todo100.activer.model.PhotoItem;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.dao.PhotoDao;
import ru.todo100.activer.strategy.PhotoStrategy;

/**
 * @author Igor Bobko
 */
@Controller
@RequestMapping("/photo")
public class PhotoPageController
{

	@Autowired
	private PhotoStrategy photoStrategy;
	@Autowired
	private PhotoDao      photoService;
	@Autowired
	private AccountDao    accountService;

	@RequestMapping(value = "/crop", method = RequestMethod.POST)
	void crop(final HttpServletRequest request) throws /*Base64DecodingException,*/ IOException
	{

		final String image = request.getParameter("resizedImage");
		final String imageDataBytes = image.substring(image.indexOf(",") + 1);
		final InputStream stream = new ByteArrayInputStream(null/*Base64.getDecoder().decode(imageDataBytes.getBytes())*/);

		BufferedImage i = ImageIO.read(stream);
		//
		//		//String pathToWeb = getServletContext().getRealPath(File.separator);
		//		File f = new File("/home/igor/ptt6Qe80LVs111.jpg");
		//		BufferedImage bi = ImageIO.read(f);

		final BufferedImage resizedImage = new BufferedImage(60, 60, BufferedImage.TYPE_INT_ARGB);

		final Graphics2D graphics = resizedImage.createGraphics();
		graphics.drawImage(i, 0, 0, 60, 60, null);

		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));

		final Area area = new Area(new Rectangle(0, 0, 60, 60));
		area.subtract(new Area(new Ellipse2D.Double(0, 0, 60, 60)));
		graphics.setBackground(new Color(0x0000ffff));
		graphics.setPaint(new Color(254, 254, 254));
		graphics.fill(area);

		graphics.dispose();

		Image im = photoStrategy.TransformColorToTransparency(resizedImage, new Color(254, 254, 254), new Color(254, 254, 254));

		BufferedImage dest = new BufferedImage(
						60, 60, BufferedImage.TYPE_INT_ARGB
		);
		Graphics2D g2 = dest.createGraphics();
		g2.drawImage(im, 0, 0, null);
		g2.dispose();

		String rest = (String) request.getSession().getAttribute("originalFileName");

		String name = photoStrategy.getClass().getResource("/../../").getPath() + photoStrategy.getPathToSave() + "/60x60_" + rest;

		File f = new File(name);

		ImageIO.write(dest, "png", f);

		//photoStrategy.copyIm,ageToFile(stream, f);
		stream.close();

	}

	@ResponseBody
	@RequestMapping("/cancel")
	void cancel(final HttpServletRequest request)
	{
		final String originalFileName = (String) request.getSession().getAttribute("originalFileName");
		if (originalFileName != null)
		{
			request.getSession().removeAttribute("originalFileName");
			final String path = photoStrategy.getPathToSave() + "/" + originalFileName;
			final PhotoItem photo = (PhotoItem) photoService.getCriteria().add(Restrictions.eq("path", path)).uniqueResult();
			photoService.delete(photo.getId());
		}
	}

	@RequestMapping("/original")
	void original(final HttpServletRequest request, final HttpServletResponse response) throws /*Base64DecodingException,*/ IOException
	{
		final String image = request.getParameter("resizedImage");
		final String imageDataBytes = image.substring(image.indexOf(",") + 1);
		final InputStream stream = new ByteArrayInputStream(null/*Base64.getDecoder().decode(imageDataBytes.getBytes())*/);

		BufferedImage i = ImageIO.read(stream);

		//		stream.reset();
		//		BufferedImage i2 = ImageIO.read(stream);
		//		blur(i,i2);
		//		getGaussianBlurFilter(10,false).filter(i, i2);
		//		response.getOutputStream().print("data:image/jpeg;base64,");
		//		ImageIO.write(i2, "png", Base64.getEncoder().wrap(response.getOutputStream()));

		//
		//
		//
		//		Base64 s = new Base64();
		//		s.Base64.getEncoder().wrap(os))
		//
		//		OutputStream b64 = new Base64.OutputStream(os);
		//		ImageIO.write(bi, "png", b64);
		//		String result = os.toString("UTF-8");
		//
		//
		String originalName = photoStrategy.saveOriginal(i);
		request.getSession().setAttribute("originalFileName", originalName);
		response.getWriter().print(image);
	}

	public void blur(BufferedImage i, BufferedImage i2)
	{
		float data[] = {
						0.0625f, 0.125f, 0.0625f, 0.125f, 0.25f, 0.125f, 0.0625f, 0.125f, 0.0625f
		};
		Kernel kernel = new Kernel(3, 3, data);
		ConvolveOp convolve = new ConvolveOp(
						kernel, ConvolveOp.EDGE_NO_OP, null
		);
		convolve.filter(i, i2);

	}

	private void setBrightness(BufferedImage i, BufferedImage i2, float multiple)
	{
		float[] brightKernel = {multiple};
		RenderingHints hints = new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		BufferedImageOp bright = new ConvolveOp(new Kernel(1, 1, brightKernel), ConvolveOp.EDGE_NO_OP, hints);

	}

	public static ConvolveOp getGaussianBlurFilter(int radius, boolean horizontal)
	{
		if (radius < 1)
		{
			throw new IllegalArgumentException("Radius must be >= 1");
		}
		int size = radius * 2 + 1;
		float[] data = new float[size];
		float sigma = radius / 3.0f;
		float twoSigmaSquare = 2.0f * sigma * sigma;
		float sigmaRoot = (float) Math.sqrt(twoSigmaSquare * Math.PI);
		float total = 0.0f;
		for (int i = -radius; i <= radius; i++)
		{
			float distance = i * i;
			int index = i + radius;
			data[index] = (float) Math.exp(-distance / twoSigmaSquare) / sigmaRoot;
			total += data[index];
		}
		for (int i = 0; i < data.length; i++)
		{
			data[i] /= total;
		}
		Kernel kernel = null;
		if (horizontal)
		{
			kernel = new Kernel(size, 1, data);
		}
		else
		{
			kernel = new Kernel(1, size, data);
		}
		return new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
	}

}
