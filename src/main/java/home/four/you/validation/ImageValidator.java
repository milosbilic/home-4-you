package home.four.you.validation;


import home.four.you.model.dto.FileBucket;
import home.four.you.validation.annotation.Image;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ImageValidator implements ConstraintValidator<Image, FileBucket> {

	@Override
	public void initialize(Image image) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(FileBucket bucket, ConstraintValidatorContext arg1) {
		File file = null;

		try {
			file = FileBucket.multipartToFile(bucket.getFile());
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}

		try {
			java.awt.Image image = ImageIO.read((file));
			if (image == null) {
//				errors.rejectValue("file", "file");
				return false;
			}
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			// errors.rejectValue("file", "not.image");
			return false;
		}
		return true;
	}

}
