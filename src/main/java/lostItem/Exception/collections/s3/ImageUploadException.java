package lostItem.Exception.collections.s3;

public class ImageUploadException extends RuntimeException {
    public ImageUploadException() {
        super("Image upload failed.");
    }

    public ImageUploadException(String message) {
        super(message);
    }

    public ImageUploadException(String message, Throwable cause) {
        super(message, cause);
    }
}