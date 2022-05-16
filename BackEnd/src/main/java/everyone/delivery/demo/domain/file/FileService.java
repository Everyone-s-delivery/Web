package everyone.delivery.demo.domain.file;

import everyone.delivery.demo.common.configuration.FileConfiguration;
import everyone.delivery.demo.common.exception.LogicalRuntimeException;
import everyone.delivery.demo.common.exception.error.CommonError;
import everyone.delivery.demo.common.exception.error.FileError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.AbstractMap;
import java.util.Locale;
import java.util.UUID;

/***
 * 원본 파일 처리를 위한 서비스
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class FileService {

    private final FileConfiguration fileConfiguration;

    public String saveMultipartFile(MultipartFile mpFiles) throws IOException {
        String fileExtension = FilenameUtils.getExtension( mpFiles.getOriginalFilename());
        if(!isImg(fileExtension)){
            log.error("It's not an image file. fileExtension: {}", fileExtension);
            throw new LogicalRuntimeException(FileError.NOT_IMAGE_EXTENSION);
        }

        String fileUuid = UUID.randomUUID().toString();
        String serverFileName = fileUuid + "." + fileExtension;

        Path fileDirectoryPath = Paths.get(fileConfiguration.getPath()).toAbsolutePath().normalize();
        if(Files.notExists(fileDirectoryPath))
            Files.createDirectories(fileDirectoryPath);
        Path filePath = fileDirectoryPath.resolve(serverFileName).normalize();

        //file uuid가 고유하기 때문에 사실상 덮어쓸 일이 없음(파일은 수정의 개념이 없고 추가 삭제에 대한 개념만 있음)
        Files.copy(mpFiles.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        return serverFileName;
    }

    public AbstractMap.SimpleEntry<Resource, String> getFile(String serverFileName){
        String fileExtension = FilenameUtils.getExtension(serverFileName);
        if(!isImg(fileExtension)){
            log.error("It's not an image file. fileExtension: {}", fileExtension);
            throw new LogicalRuntimeException(FileError.NOT_IMAGE_EXTENSION);
        }
        Path filePath = Paths.get(fileConfiguration.getPath()).toAbsolutePath().
                resolve(serverFileName).normalize();

        if(!Files.exists(filePath)){    // 없는 파일
            log.error("file does not exist! filePath: {}", filePath.toString());
            throw new LogicalRuntimeException(FileError.NOT_EXIST_FILE);
        }

        try{
            Resource resource = new UrlResource(filePath.toUri());
            String contentType = Files.probeContentType(Paths.get(resource.getFile().getAbsolutePath()));
            if(contentType == null) {
                contentType = "application/octet-stream;charset=UTF-8";
            }
            return new AbstractMap.SimpleEntry<Resource, String>(resource,contentType + ";charset=UTF-8");
        }catch (IOException ex){
            log.error("file display fail!. {}",ex);
            throw new LogicalRuntimeException(CommonError.INTERNAL_SERVER_ERROR);
        }
    }



    /***
     * Content-Desposition 헤더에 들어갈 파일 이름을 브라우저 호환되도록 변경하는 메소드
     * @param fileName, agent
     * @return
     */
    public String convertFileName(String fileName, String agent) {
        try {
            String orginalFileName = URLDecoder.decode(fileName, "UTF-8");

            if(agent.contains("Trident"))	//Internet explore
                orginalFileName = URLEncoder.encode(orginalFileName, "UTF-8").replaceAll("\\+", " ");
            else if(agent.contains("Edge"))	//micro edge
                orginalFileName = URLEncoder.encode(orginalFileName, "UTF-8");
            else							//chrome
                orginalFileName = new String(orginalFileName.getBytes("UTF-8"), "ISO-8859-1");
            return orginalFileName;
        }catch(UnsupportedEncodingException ex) {
            log.error("fail to file encoding to UTF-8", ex);
            return fileName;
        }
    }

    /**
     * fileExtension이 이미지 확장자인지 확인
     * 이미지 확장자면 true
     * @param fileExtension
     * @return
     */
    public boolean isImg(String fileExtension){
        fileExtension = fileExtension.toLowerCase(Locale.getDefault());
        return fileExtension.equals("jpg") || fileExtension.equals("png") ||fileExtension.equals("jpeg") ||fileExtension.equals("gif");
    }
}