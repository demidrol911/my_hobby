import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageOperation {
	
	public static BufferedImage loadImage(File file) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	
	public static void saveImage(File file, BufferedImage image) {
		String ext = file.getName().replaceFirst("^.*[.]", "");
		try {
			ImageIO.write(image, ext, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static BufferedImage convolution(BufferedImage src, float[][] kernel) {
		BufferedImage dst = null;
		if(src != null) {
			WritableRaster raster_src = src.getRaster();
			ColorModel model_src = src.getColorModel();
    	
			int image_weight = src.getWidth(), image_height = src.getHeight();
			int kernel_size = kernel.length;
			int radius_kernel = kernel.length/2;
    	
			dst = new BufferedImage(image_weight, image_height, BufferedImage.TYPE_INT_RGB);
			WritableRaster raster_dst = dst.getRaster();
			ColorModel model_dst = dst.getColorModel();	
			Object data;
		
			int k0i, k0j, k1i, k1j, imi, imj;
			int[] pixels = new int[3];
			for(int di = 0; di < image_height; di++) {
				for(int dj = 0; dj < image_weight; dj++){
					pixels[0]=pixels[1]=pixels[2]=0;
					if(radius_kernel > di){
						k0i = radius_kernel - di;
						imi = 0;
					}
					else {
						k0i = 0;
						imi = di - radius_kernel;
					}
					if(radius_kernel + di < image_height)k1i = kernel_size;
					else k1i = radius_kernel + image_height - di;
					if(radius_kernel > dj){
						k0j = radius_kernel - dj;
						imj = 0;
					}
					else {
						k0j = 0;
						imj = dj - radius_kernel;
					}
					if(radius_kernel + dj < image_weight)k1j = kernel_size;
					else k1j = radius_kernel + image_weight - dj;
					for(int i1 = k0i, i2 = imi; i1 < k1i; i1++, i2++){
						for(int j1 = k0j, j2 = imj ; j1 < k1j; j1++, j2++){
							data = raster_src.getDataElements(j2, i2, null);
							pixels[0]+=kernel[i1][j1]*model_src.getRed(data);
							pixels[1]+=kernel[i1][j1]*model_src.getGreen(data);
							pixels[2]+=kernel[i1][j1]*model_src.getBlue(data);
						}
					}
					if(pixels[0]<0)pixels[0]=0;
					if(pixels[1]<0)pixels[1]=0;
					if(pixels[2]<0)pixels[2]=0;
					if(pixels[0]>255)pixels[0]=255;
					if(pixels[1]>255)pixels[1]=255;
					if(pixels[2]>255)pixels[2]=255;
					Color color = new Color(pixels[0], pixels[1], pixels[2]);
					data = model_dst.getDataElements(color.getRGB(), null);
					raster_dst.setDataElements(dj, di, data);
				}
			}
		}
		return dst;
	}
}
