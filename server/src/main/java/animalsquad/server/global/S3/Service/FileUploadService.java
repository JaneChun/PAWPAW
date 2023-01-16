package animalsquad.server.global.S3.Service;

import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileUploadService {
// 추가
    private final UploadService s3Service;

    //Multipart를 통해 전송된 파일을 업로드하는 메소드
    public String uploadImage(MultipartFile file, String folderName) throws IllegalAccessException {
        String fileName = createFileName(file.getOriginalFilename());
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());
        try (InputStream inputStream = file.getInputStream()) {
            s3Service.uploadFile(inputStream, objectMetadata, fileName, folderName);
        } catch(IOException e) {
            throw new IllegalAccessException(String.format("파일 변환 중에러가 발생하였습니다.(%s)", file.getOriginalFilename()));
        }
        return s3Service.getFileUrl(fileName, folderName);
    }

    //기존 확장자명을 유지한 채, 유니크한 파일의 이름을 생성하는 로직
    private String createFileName(String originalFileName) throws IllegalAccessException {
        return UUID.randomUUID() + "." + getFileExtension(originalFileName);
    }

    //파일의 확장자명을 가져오는 로직
    private String getFileExtension(String fileName) throws IllegalAccessException {
        try {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalAccessException(String.format("잘못된 형식의 파일($s) 입니다", fileName));
        }
    }
    public void deleteFile(String fileName) throws IllegalAccessException {
        s3Service.deleteFile(fileName);
    }
}